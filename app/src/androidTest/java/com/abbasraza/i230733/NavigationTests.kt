package com.abbasraza.i230733

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.NoActivityResumedException
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertThrows
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * UI tests for the main navigation flows. Every test starts on the login screen.
 * Run with: Run > 'NavigationTests' (needs an emulator or phone).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTests {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun login_opensHomeFeed() {
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.btnCreatePostField)).check(matches(isDisplayed()))
    }

    @Test
    fun homeToComments_andBackToHome() {
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.btnComment)).perform(click())
        onView(withText("Comments")).check(matches(isDisplayed()))

        onView(withId(R.id.btnBack)).perform(click())
        onView(withId(R.id.btnComment)).check(matches(isDisplayed()))
    }

    @Test
    fun homeToChat_showsConversation() {
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.btnChats)).perform(click())
        onView(withText("Aisha Khan")).perform(click())
        onView(withId(R.id.tvChatTitle)).check(matches(withText("Aisha Khan")))
    }

    @Test
    fun loginToMenu_logout_clearsBackStack() {
        onView(withId(R.id.btnLogin)).perform(click())
        onView(withId(R.id.tabMenu)).perform(click())
        onView(withId(R.id.btnLogout)).perform(click())

        // Back on the login screen...
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        // ...and nothing is left behind it, so Back closes the app
        assertThrows(NoActivityResumedException::class.java) { pressBack() }
    }
}
