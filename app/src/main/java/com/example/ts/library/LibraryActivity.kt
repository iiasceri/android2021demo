package com.example.ts.library

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.ts.InternetCheck
import com.example.ts.MyListAdapter
import com.example.ts.R
import com.example.ts.databinding.ActivityLibraryBinding
import com.example.ts.db.LibraryDataDao
import com.example.ts.db.RoomLibraryRepository
import com.example.ts.db.TblLibrary
import com.example.ts.details.DetailsActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


private val TAG: String = LibraryActivity::class.java.simpleName

@AndroidEntryPoint
class LibraryActivity : AppCompatActivity() {
    private lateinit var viewModel: LibraryViewModel
    private lateinit var binding: ActivityLibraryBinding
    private lateinit var tblLibrary: TblLibrary

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //TODO Implement factory because constructor is custom
//        val factory = LibraryViewModelFactory(RoomLibraryRepository(LibraryDataDao()))
//        viewModel = ViewModelProvider(this, factory).get(LibraryViewModel::class.java)

        if (!InternetCheck.check(this@LibraryActivity)) {
            showToastForConnectivity()
        } else {
            viewModel.getAllContents()
        }

        tblLibrary = TblLibrary.getInstance(this)

        viewModel.libraryList.observe(this, {
            Log.i(TAG, it.toString())

            //Don't judge it :), it's just some dust in the eyes to showcase usage of Room
            val myListAdapter : MyListAdapter
            val listLibraryData = tblLibrary.getAll()
            myListAdapter = if (listLibraryData != null && listLibraryData.isNotEmpty()) {
                //After data is loaded from first time, all articles will be loaded from DB
                MyListAdapter(this@LibraryActivity, listLibraryData)
            } else {
                //This will be called only on first run
                synchronized(TblLibrary::class) {
                    for (libraryData in it)
                        tblLibrary.insert(libraryData)
                }
                MyListAdapter(this@LibraryActivity, it)
            }

            binding.libraryListView.adapter = myListAdapter
            myListAdapter.notifyDataSetChanged()
            //adapter.setMovies(it)

            binding.libraryListView.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    val selectedContent = myListAdapter.getItem(position)
                    Log.i(TAG, "selected : $selectedContent")

                    if (!InternetCheck.check(this@LibraryActivity)) {
                        showToastForConnectivity()
                    } else {
                        val intent = Intent(this, DetailsActivity::class.java).apply {
                            putExtra("LIBRARY_ID", selectedContent.id)
                        }
                        startActivity(intent)
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
                    }
                }
        })

        viewModel.errorMessage.observe(this, {
            Log.i(TAG, "error : $it")
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        val bottomNavigationView =
            findViewById<View>(R.id.bottom_navigation_view) as BottomNavigationView

        val menu = bottomNavigationView.menu
        val menuItem = menu.getItem(1)
        menuItem.isChecked = true

        bottomNavigationView.setOnItemSelectedListener { item ->
            val id = item.itemId
            var menuItemNr = -1
            when (id) {
                R.id.homePage -> menuItemNr = 0
                R.id.libraryPage -> menuItemNr = 1
                R.id.profilePage -> menuItemNr = 2
            }
            if (menuItemNr != -1) {
                val menuItemTmp = menu.getItem(menuItemNr)
                menuItemTmp.isChecked = true
            }
            true
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        TblLibrary.destroyInstance()
    }

    private fun showToastForConnectivity() {
        Toast.makeText(this@LibraryActivity, getString(R.string.no_internet), Toast.LENGTH_LONG)
            .show()

    }
}