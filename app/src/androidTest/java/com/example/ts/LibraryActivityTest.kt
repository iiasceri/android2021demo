package com.example.ts

import android.content.Intent
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.ts.library.LibraryActivity
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
@LargeTest
class LibraryActivityTest {

    @Before
    fun setUp() {
        val intent = Intent(getApplicationContext(), LibraryActivity::class.java)
        launchActivity<LibraryActivity>(intent)
    }

    @After
    fun tearDown() {
    }

    //Just the basic demo
    //TODO add real test
    @Test
    fun testLibraryActivity() {
        onView(withId(R.id.library_list_view)).check(matches(isDisplayed()))
    }
}