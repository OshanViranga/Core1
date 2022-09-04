package com.example.core1


import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private var score = 0
    private var steal = 0
    private var reset = 0
    private var count = 0

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Please set your id names here.
        score = R.id.score
        steal = R.id.steal
        reset = R.id.reset
        count = R.id.show_score
    }

    @Test
    fun clickScoreButton3Times() {
        // given: open app
        // when: click score three times
        // then: score is 3
        val scoreButton = onView(withId(score))

        for (i in 1..3) {
            scoreButton.perform(click())
        }

        val textView = onView(withId(count))
        textView.check(matches(withText("3")))
    }

    @Test
    fun clickScoreAndStealButtons() {
        // given: open app
        // when: click score 3 times and steal 1 time
        // then: score is 2
        val scoreButton = onView(withId(score))
        val stealButton = onView(withId(steal))

        for (i in 1..3) {
            scoreButton.perform(click())
        }

        stealButton.perform(click())

        val textView = onView(withId(count))
        textView.check(matches(withText("2")))
    }

    @Test
    fun testLowerLimitsOfScore() {
        // given: open app
        // when: click score 3 times and steal 5 times
        // then: score is 0
        val scoreButton = onView(withId(score))
        val stealButton = onView(withId(steal))

        for (i in 1..3) {
            scoreButton.perform(click())
        }

        for (i in 1..5) {
            stealButton.perform(click())
        }

        val textView = onView(withId(count))
        textView.check(matches(withText("0")))
    }

    @Test
    fun testUpperLimitsOfScore() {
        // given: open app
        // when: click score 15 times and again 2 more times
        // then: score is 15
        val scoreButton = onView(withId(score))

        for (i in 1..15) {
            scoreButton.perform(click())
        }

        for (i in 1..2) {
            scoreButton.perform(click())
        }

        val textView = onView(withId(count))
        textView.check(matches(withText("15")))
    }

    @Test
    fun testResetButton() {
        // given: open app
        // when: click score 3 times and reset 1 time
        // then: score is 0
        val scoreButton = onView(withId(score))
        val resetButton = onView(withId(reset))

        for (i in 1..3) {
            scoreButton.perform(click())
        }

        resetButton.perform(click())

        val textView = onView(withId(count))
        textView.check(matches(withText("0")))
    }


    @Test
    fun testScoreOnRotation() {
        // given: open app
        // when: click score 3 times and rotate device
        // then: score is 3
        val scoreButton = onView(withId(score))

        for (i in 1..3) {
            scoreButton.perform(click())
        }

        val textView = onView(withId(count))
        textView.check(matches(withText("3")))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        textView.check(matches(withText("3")))

    }

    @Test
    fun testScoreOnRotationWithClick() {
        // given: open app
        // when: click score 3 times and rotate device, click score 1 time
        // then: score is 4
        val scoreButton = onView(withId(score))

        for (i in 1..3) {
            scoreButton.perform(click())
        }

        val textView = onView(withId(count))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        scoreButton.perform(click())
        textView.check(matches(withText("4")))

    }

}

