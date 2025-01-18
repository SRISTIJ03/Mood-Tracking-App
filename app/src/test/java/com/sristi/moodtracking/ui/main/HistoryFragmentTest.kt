package com.sristi.moodtracking.ui.main

import org.junit.Assert.*
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sristi.moodtracking.data.MoodEntryAdapter
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HistoryFragmentTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun fragmentSetsUpRecyclerViewAdapter() {
        val scenario = launchFragment<HistoryFragment>()
        scenario.onFragment { fragment ->
            assertEquals(MoodEntryAdapter::class, fragment.recyclerView.adapter!!::class)
        }
    }
}
