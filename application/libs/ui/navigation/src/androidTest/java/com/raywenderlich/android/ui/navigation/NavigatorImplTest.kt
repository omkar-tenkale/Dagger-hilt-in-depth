package com.raywenderlich.android.ui.navigation

import android.content.Intent
import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import com.raywenderlich.android.ui.navigation.util.ActivityTest
import com.raywenderlich.android.ui.navigation.util.FragmentTest
import com.raywenderlich.android.ui.navigation.util.FragmentTest.Companion.TEXTVIEW_ID
import com.raywenderlich.android.ui.navigation.util.SecondFragmentTest
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

/**
 * Test class for the ActivityNavigator implementation
 */
class NavigatorImplTest {

    @get:Rule
    val intentsTestRule = IntentsTestRule(ActivityTest::class.java)

    @Test
    fun navigateTo_usingActivityIntentDestination_intentForDestinationLaunched() {
        val activityNavigator = NavigatorImpl(intentsTestRule.activity)
        val testIntent = Intent(intentsTestRule.activity, ActivityTest::class.java).apply {
            putExtra("ExtraKey", "ExtraValue")
            addCategory("TEST_CATEGORY")
        }
        val destination = ActivityIntentDestination(testIntent)
        activityNavigator.navigateTo(destination)
        intended(IntentMatchers.hasCategories(setOf("TEST_CATEGORY")))
        intended(IntentMatchers.hasExtra("ExtraKey", "ExtraValue"))
    }

    @Test
    fun navigateTo_usingFragmentDestination_fragmentIsDisplayed() {
        val activityNavigator = NavigatorImpl(intentsTestRule.activity)
        val fragmentTest = FragmentTest()
        val fragmentDestination = FragmentDestination(fragmentTest, ActivityTest.ANCHOR_POINT_ID)
        activityNavigator.navigateTo(fragmentDestination)
        onView(withId(TEXTVIEW_ID)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateTo_usingFragmentFactoryDestination_fragmentIsDisplayed() {
        val activityNavigator = NavigatorImpl(intentsTestRule.activity)
        val fragmentTestFactory = { bundle: Bundle? -> FragmentTest() }
        val fragmentDestination =
            FragmentFactoryDestination(fragmentTestFactory, ActivityTest.ANCHOR_POINT_ID)
        activityNavigator.navigateTo(fragmentDestination)
        onView(withId(TEXTVIEW_ID)).check(matches(isDisplayed()))
    }

    @Test
    fun navigateTo_usingActivityBackDestination_activityIsFinishing() {
        NavigatorImpl(intentsTestRule.activity)
            .navigateTo(ActivityBackDestination)
        assertTrue(intentsTestRule.activity.isFinishing)
    }

    @Test
    fun navigateTo_usingFragmentBackDestination_fragmentIsNotThere() {
        val activityNavigator = NavigatorImpl(intentsTestRule.activity)
        val fragmentTestFactory = { bundle: Bundle? -> FragmentTest() }
        val fragmentDestination =
            FragmentFactoryDestination(fragmentTestFactory, ActivityTest.ANCHOR_POINT_ID)
        activityNavigator.navigateTo(fragmentDestination)
        onView(withId(TEXTVIEW_ID)).check(matches(isDisplayed()))
        val fragmentDestination2 =
            FragmentDestination(
                SecondFragmentTest(), ActivityTest.ANCHOR_POINT_ID,
                "BackStack"
            )
        activityNavigator.navigateTo(fragmentDestination2)
        onView(withText("SecondTestFragment")).check(matches(isDisplayed()))
        activityNavigator.navigateTo(FragmentBackDestination)
        onView(withText("TestFragment")).check(matches(isDisplayed()))
    }
}