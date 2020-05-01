package ir.genesis.calendar

import android.content.Context
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import ir.genesis.calendar.utils.initUtils
import me.cheshmak.android.sdk.core.Cheshmak

class MainApplication : MultiDexApplication() {

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        ReleaseDebugDifference.mainApplication(this)
        initUtils(applicationContext)

        Cheshmak.with(this@MainApplication)
        Cheshmak.initTracker("mePIqnysCkR7nbOGat9Tzw==")
    }
}
