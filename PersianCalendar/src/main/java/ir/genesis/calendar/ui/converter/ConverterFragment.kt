package ir.genesis.calendar.ui.converter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ir.genesis.calendar.R
import ir.genesis.calendar.databinding.FragmentConverterBinding
import ir.genesis.calendar.ui.MainActivity
import ir.genesis.calendar.utils.getOrderedCalendarTypes
import ir.genesis.calendar.utils.getTodayJdn

class ConverterFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentConverterBinding.inflate(inflater, container, false).apply {
        (activity as? MainActivity)?.setTitleAndSubtitle(
            getString(R.string.date_converter), ""
        )

        calendarsView.expand(true)
        calendarsView.hideMoreIcon()

        val todayJdn = getTodayJdn()

        todayButton.setOnClickListener { dayPickerView.setDayJdnOnView(todayJdn) }

        dayPickerView.selectedDayListener = fun(jdn) {
            if (jdn == -1L) {
                calendarsView.visibility = View.GONE
            } else {
                if (jdn == todayJdn) todayButton.hide() else todayButton.show()

                calendarsView.visibility = View.VISIBLE
                val selectedCalendarType = dayPickerView.selectedCalendarType
                calendarsView.showCalendars(
                    jdn, selectedCalendarType,
                    getOrderedCalendarTypes() - selectedCalendarType
                )
            }
        }
        dayPickerView.setDayJdnOnView(getTodayJdn())
    }.root
}
