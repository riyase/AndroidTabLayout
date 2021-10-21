package com.example.navigationcomponenttablet

import android.view.View
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.UiController
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.ViewAction
import org.hamcrest.Matcher


//@RunWith(Roboele)
@RunWith(AndroidJUnit4ClassRunner::class)
class SportsFragmentTest {

    var isTablet: Boolean = false

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        isTablet = appContext.resources.getBoolean(R.bool.isTablet)
    }

    @Test
    fun test_isSportsFragmentVisible() {
        val sportsScenario = launchFragmentInContainer<SportsFragment>()
        if (isTablet) {
            Espresso.onView(ViewMatchers.withId(R.id.layout_sports_land))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        } else {
            Espresso.onView(ViewMatchers.withId(R.id.layout_sports))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        }
    }

    @Test
    fun testNavigationToCricketScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the TitleScreen
        val sportsScenario = launchFragmentInContainer<SportsFragment>()

        sportsScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph_sports)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        Espresso.onView(ViewMatchers.withId(R.id.cricket_textview)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(2000))
        Assert.assertEquals(navController.currentDestination?.id, R.id.cricketFragment)
        Espresso.onView(ViewMatchers.withId(R.id.layout_cricket))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testNavigationToSoccerScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the TitleScreen
        val sportsScenario = launchFragmentInContainer<SportsFragment>()

        sportsScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph_sports)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        val currentDest = navController.currentDestination
        Espresso.onView(ViewMatchers.withId(R.id.soccer_textview)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(5000))
        Assert.assertEquals(navController.currentDestination?.id, R.id.soccerFragment)
        Espresso.onView(ViewMatchers.withId(R.id.layout_soccer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        //not working for mobile devices. working only for tablets
    }

    @Test
    fun testNavigationToVolleyScreen() {
        // Create a TestNavHostController
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())

        // Create a graphical FragmentScenario for the TitleScreen
        val sportsScenario = launchFragmentInContainer<SportsFragment>()

        sportsScenario.onFragment { fragment ->
            // Set the graph on the TestNavHostController
            navController.setGraph(R.navigation.nav_graph_sports)

            // Make the NavController available via the findNavController() APIs
            Navigation.setViewNavController(fragment.requireView(), navController)
        }

        // Verify that performing a click changes the NavController’s state
        Espresso.onView(ViewMatchers.withId(R.id.volley_textview)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.isRoot()).perform(waitFor(5000))
        //Assert.assertEquals(navController.currentDestination?.id, R.id.volleyBallFragment)
        Espresso.onView(ViewMatchers.withId(R.id.layout_volley))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    fun waitFor(delay: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String {
                return "wait for " + delay + "milliseconds"
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
    /*@Test
    fun test_checkCricketLaunch() {
        launchFragmentInContainer<SportsFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.cricket_textview)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.layout_cricket))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_checkSoccerLaunch() {
        launchFragmentInContainer<SportsFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.soccer_textview)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.layout_soccer))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun test_checkVolleyLaunch() {
        launchFragmentInContainer<SportsFragment>()
        Espresso.onView(ViewMatchers.withId(R.id.volley_textview)).perform(ViewActions.click())
        Espresso.onView(ViewMatchers.withId(R.id.layout_volley))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }*/
}