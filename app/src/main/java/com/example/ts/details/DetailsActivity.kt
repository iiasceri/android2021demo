package com.example.ts.details

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ts.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var viewModel: DetailsViewModel

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //adjust back image margin
        val param = binding.imgBack.layoutParams as ViewGroup.MarginLayoutParams
        param.setMargins(40, -getStatusBarHeight() - 40, 20, 20)
        binding.imgBack.layoutParams = param

        binding.imgBack.setOnClickListener {
            finish()
        }

        // Get the Intent from main activity and extract content's id
        val libraryId = intent.getIntExtra("LIBRARY_ID", 0)


        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)

        //get content details
        viewModel.getContentDetails(libraryId.toString())

        viewModel.errorMessage.observe(this, {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        })

        viewModel.content.observe(this, Observer {
            binding.txtContentTitle.text = it.title
            binding.txtContentSubTitle.text = it.subtitle
            binding.txtContentBody.text = it.body

            binding.txtContentDate.text = it.date.split(" ")[0]
            binding.txtContentHour.text = it.date.split(" ")[1]
        })

    }

    /**
     * get status bar height
     */
    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }
}