package com.example.ts.details

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.TestCase
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DetailsViewModelTest : TestCase() {

    private lateinit var viewModel: DetailsViewModel

    public override fun setUp() {
        super.setUp()

        val context = ApplicationProvider.getApplicationContext<Context>()
//        val db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
//            .allowMainThreadQueries().build()
//        val dataSource = DetailsRepository()

//        viewModel = ViewModelProvider(context).get(DetailsViewModel::class.java)
    }
}