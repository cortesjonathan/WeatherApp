package com.jhonscmdev.weatherjhonsapp

import android.app.Application
import com.jhonscmdev.weatherjhonsapp.di.DaggerWeatherAppComponent
import com.jhonscmdev.weatherjhonsapp.di.WeatherAppComponent

class WeatherApp: Application() {
    lateinit var  component: WeatherAppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        component = DaggerWeatherAppComponent.factory().create(this)
    }
}