package com.jhonscmdev.weatherjhonsapp.data

import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import io.reactivex.Single

class MockyRepository(private  val remoteMockyDataSource: RemoteMockyDataSource) {
    fun getAllmocky(): Single<ResponseData> = remoteMockyDataSource.getAllApiinf()
    fun getAllWeather(latitud: Double,longitud:Double): Single<WeatherResponse> = remoteMockyDataSource.getAllApiweather(latitud,longitud)
}