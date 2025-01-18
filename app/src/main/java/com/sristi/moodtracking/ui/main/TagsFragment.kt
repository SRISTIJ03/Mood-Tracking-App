import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sristi.moodtracking.R
import com.sristi.moodtracking.data.MoodEntry
import com.sristi.moodtracking.data.MoodEntryPieAnalysis
import com.sristi.moodtracking.data.MoodEntrySQLiteDBHelper
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class TagsFragment : Fragment() {
    lateinit var moodEntries: List<MoodEntry>
    lateinit var databaseHelper: MoodEntrySQLiteDBHelper

    lateinit var pieAnalysis: MoodEntryPieAnalysis

    lateinit var moodsPieChart: PieChart
    lateinit var activitiesPieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_tags, container, false)

        // Explicitly find PieChart views by their IDs
        moodsPieChart = root.findViewById(R.id.moodsPieChart)
        activitiesPieChart = root.findViewById(R.id.activitiesPieChart)

        return root
    }

    override fun onResume() {
        super.onResume()

        databaseHelper = MoodEntrySQLiteDBHelper(activity)

        moodEntries = ArrayList()
        (moodEntries as ArrayList<MoodEntry>).clear()
        (moodEntries as ArrayList<MoodEntry>).addAll(
            databaseHelper.fetchMoodData()
        )

        pieAnalysis = MoodEntryPieAnalysis()

        moodEntries = moodEntries.reversed().toList()

        assemblePieChart(
            moodsPieChart,
            getString(R.string.moods),
            pieAnalysis.getMoodsFrom(moodEntries),
            listColors()
        )

        assemblePieChart(
            pieChart = activitiesPieChart,
            name = getString(R.string.pastimes),
            pieSections = pieAnalysis.getActivitiesFrom(moodEntries),
            listColors = listColors()
        )
    }

    fun listColors(): ArrayList<Int> {
        val listColors = ArrayList<Int>()
        listColors.add(resources.getColor(R.color.colorAccent))
        listColors.add(resources.getColor(R.color.colorMagenta))
        listColors.add(resources.getColor(R.color.colorPrimary))
        listColors.add(resources.getColor(R.color.colorBlue))
        listColors.add(resources.getColor(R.color.colorRed))
        return listColors
    }

    fun assemblePieChart(
        pieChart: PieChart,
        name: String,
        pieSections: ArrayList<PieEntry>,
        listColors: ArrayList<Int>
    ) {
        pieChart.setUsePercentValues(true)
        pieChart.centerText = name
        pieChart.setCenterTextSize(20f)
        pieChart.invalidate()
        pieChart.description.text = ""
        pieChart.isDrawHoleEnabled = true

        val pieDataSet = PieDataSet(pieSections, "")
        pieDataSet.colors = listColors

        val pieData = PieData(pieDataSet)
        pieData.setValueTextSize(20f)
        pieChart.data = pieData

        pieChart.setUsePercentValues(true)
        pieChart.setEntryLabelColor(R.color.colorPrimaryDark)
        pieChart.legend.isEnabled = false

        pieChart.animateX(1400, Easing.EaseInOutQuad)
    }

    companion object {
        @JvmStatic
        fun newInstance(): TagsFragment {
            return TagsFragment()
        }
    }
}
