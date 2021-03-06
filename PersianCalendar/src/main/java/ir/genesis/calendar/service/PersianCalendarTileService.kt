package ir.genesis.calendar.service

import android.annotation.TargetApi
import android.content.Intent
import android.graphics.drawable.Icon
import android.os.Build
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import ir.genesis.calendar.ui.MainActivity
import ir.genesis.calendar.utils.*

/**
 * Created by Alireza Afkar on 19/6/2018AD.
 */
@TargetApi(Build.VERSION_CODES.N)
class PersianCalendarTileService : TileService() {

    override fun onClick() = try {
        startActivityAndCollapse(
            Intent(this, MainActivity::class.java)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }

    override fun onStartListening() = try {
        val today = getTodayOfCalendar(mainCalendar)
        qsTile?.also {
            it.icon = Icon.createWithResource(this, getDayIconResource(today.dayOfMonth))
            it.label = getWeekDayName(today)
            it.contentDescription = getMonthName(today)
            // explicitly set Tile state to Active, fixes tile not being lit on some Samsung devices
            it.state = Tile.STATE_ACTIVE
        }?.updateTile() ?: Unit
    } catch (e: Exception) {
        e.printStackTrace()
    }
}
