package com.zedneypfe.loadenpfe

import android.app.Application
import android.content.res.Configuration
import com.zedneypfe.loadenpfe.utils.ExceptionHandler
import java.util.*

class LoadenPfeApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Thread.setDefaultUncaughtExceptionHandler(ExceptionHandler())
        val locale = Locale("ar")
        Locale.setDefault(locale)
        val config = Configuration()
        config.locale = locale
        baseContext.resources.updateConfiguration(
            config,
            baseContext.resources.displayMetrics
        )

    }
}