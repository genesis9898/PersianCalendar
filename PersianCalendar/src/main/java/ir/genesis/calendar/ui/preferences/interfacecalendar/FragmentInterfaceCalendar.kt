package ir.genesis.calendar.ui.preferences.interfacecalendar

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat
import ir.genesis.calendar.R
import ir.genesis.calendar.ui.preferences.interfacecalendar.calendarsorder.CalendarPreferenceDialog
import ir.genesis.calendar.utils.askForCalendarPermission

class FragmentInterfaceCalendar : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences_interface_calendar)

        findPreference<ListPreference>("Theme")?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()
        findPreference<ListPreference>("WeekStart")?.summaryProvider =
            ListPreference.SimpleSummaryProvider.getInstance()

        val switchPreference = findPreference<SwitchPreferenceCompat>("showDeviceCalendarEvents")

        val activity = activity ?: return
        switchPreference?.setOnPreferenceChangeListener { _, _ ->
            if (ActivityCompat.checkSelfPermission(
                    activity, Manifest.permission.READ_CALENDAR
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                askForCalendarPermission(activity)
                switchPreference.isChecked = false
            } else {
                switchPreference.isChecked = !switchPreference.isChecked
            }
            false
        }
    }

    override fun onPreferenceTreeClick(preference: Preference?): Boolean =
        if (preference?.key == "calendars_priority") {
            parentFragmentManager.apply {
                CalendarPreferenceDialog().show(this, "CalendarPreferenceDialog")
            }
            true
        } else super.onPreferenceTreeClick(preference)
}
