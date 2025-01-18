package com.sristi.moodtracking

import android.content.Intent
import android.widget.Button
import org.junit.Assert.*

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows

@RunWith(RobolectricTestRunner::class)
class LoginActivityTest {

    @Test
    fun attachesFragmentsToProperTabs() {
        val systemUnderTest = Robolectric.buildActivity(LoginActivity::class.java)
            .create()
            .visible()
            .get()
        val signInButton = systemUnderTest.findViewById<Button>(R.id.sign_in_button)

        signInButton.performClick()

        val intent = Intent(systemUnderTest, MainActivity::class.java)
        assertEquals(
            Shadows.shadowOf(systemUnderTest).nextStartedActivity.component?.className,
            intent.component?.className
        )
    }
}