package ir.genesis.calendar.ui.preferences

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ir.genesis.calendar.R
import ir.genesis.calendar.databinding.FragmentSettingsBinding
import ir.genesis.calendar.ui.MainActivity
import ir.genesis.calendar.ui.preferences.interfacecalendar.FragmentInterfaceCalendar
import ir.genesis.calendar.ui.preferences.locationathan.FragmentLocationAthan
import ir.genesis.calendar.ui.preferences.widgetnotification.FragmentWidgetNotification
import ir.genesis.calendar.utils.layoutInflater
import com.google.android.material.tabs.TabLayoutMediator

/**
 * @author MEHDI DIMYADI
 * MEHDIMYADI
 */
class PreferencesFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ) = FragmentSettingsBinding.inflate(inflater, container, false).apply {
        val mainActivity = activity as MainActivity
        mainActivity.setTitleAndSubtitle(getString(R.string.settings), "")
        val tabs = listOf(
            R.string.pref_header_interface_calendar to FragmentInterfaceCalendar::class.java,
            R.string.pref_header_widget_location to FragmentWidgetNotification::class.java,
            R.string.pref_header_location_athan to FragmentLocationAthan::class.java
        )
        viewPager.adapter = object : FragmentStateAdapter(this@PreferencesFragment) {
            override fun getItemCount() = tabs.size
            override fun createFragment(position: Int) = tabs[position].second.newInstance()
        }
        TabLayoutMediator(tabLayout, viewPager) { tab, i -> tab.setText(tabs[i].first) }.attach()
    }.root
}
