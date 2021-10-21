package com.example.navigationcomponenttablet

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    companion object {
        val TAG = "MainActvityTest"
    }

    @Test
    fun onCreateCorrectUIDisplayed() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        val isTablet = appContext.resources.getBoolean(R.bool.isTablet)
        if (isTablet) {
            Espresso.onView(ViewMatchers.withId(R.id.list_tablet))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } else {
            Espresso.onView(ViewMatchers.withId(R.id.list))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun onOptionsItemSelected() {
    }
}