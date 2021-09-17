package com.lemonz.weatherapp.common

import android.app.Application

class WeatherApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppPreferences.init(this)
    }
}