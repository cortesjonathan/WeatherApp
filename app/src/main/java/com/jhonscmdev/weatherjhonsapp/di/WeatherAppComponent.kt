package com.jhonscmdev.weatherjhonsapp.di

import android.app.Application
import com.jhonscmdev.weatherjhonsapp.data.RepositoryModule
import com.jhonscmdev.weatherjhonsapp.framework.request.ApiModule
import com.jhonscmdev.weatherjhonsapp.usecase.UseCaseModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules = [
    ApiModule::class,
    RepositoryModule::class,
    UseCaseModule::class
])
interface WeatherAppComponent {

    fun inject( module: HomeModule): HomeMockyComponent

    @Component.Factory
    interface Factory{
        fun create(@BindsInstance app: Application) : WeatherAppComponent
    }
}