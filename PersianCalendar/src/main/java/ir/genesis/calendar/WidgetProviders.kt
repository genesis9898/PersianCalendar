package ir.genesis.calendar

import android.appwidget.AppWidgetProvider
import android.content.Context
import android.content.Intent
import ir.genesis.calendar.utils.startEitherServiceOrWorker
import ir.genesis.calendar.utils.update

abstract class WidgetProvider : AppWidgetProvider() {
    override fun onReceive(context: Context?, intent: Intent?) {
        context ?: return
        startEitherServiceOrWorker(context)
        update(context, false)
    }
}

class Widget1x1 : WidgetProvider()
class Widget2x2 : WidgetProvider()
class Widget4x1 : WidgetProvider()
class Widget4x2 : WidgetProvider()
