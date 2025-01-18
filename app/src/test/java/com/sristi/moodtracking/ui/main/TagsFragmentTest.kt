package com.sristi.moodtracking.ui.main

import org.junit.Assert.*
import android.widget.TextView
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sristi.moodtracking.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TagsFragmentTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fragmentLaunches() {
        val scenario = launchFragment<TagsFragment>()
        scenario.onFragment { fragment ->
            val tagsLabel = fragment.view!!.findViewById<TextView>(R.id.tags_label)
            assertEquals("Mood and Activity Breakdown", tagsLabel.text)
        }
    }
}
