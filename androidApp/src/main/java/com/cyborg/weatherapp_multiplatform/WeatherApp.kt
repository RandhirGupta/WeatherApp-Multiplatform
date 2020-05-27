package com.cyborg.weatherapp_multiplatform

import android.app.Application
import com.cyborg.weatherapp_multiplatform.common.di.InjectorCommon
import com.cyborg.weatherapp_multiplatform.common.utils.ContextArgs

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        InjectorCommon.provideContextArgs(ContextArgs(this))
    }
}