package com.sristi.moodtracking.ui.main


import android.content.Context
import android.view.View
import android.widget.TextView
import com.sristi.moodtracking.R
import com.github.mikephil.charting.components.MarkerView


class ScatterplotMarkerView(context: Context?, layoutResource: Int) :
    MarkerView(context, layoutResource) {
    val moodEntryLabel: TextView

    fun getXOffset(xpos: Float): Int = -(width / 2)
    fun getYOffset(ypos: Float): Int = -height

    init {
        moodEntryLabel = findViewById<View>(R.id.scatterplot_marker_label) as TextView
    }
}
