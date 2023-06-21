package com.jhonscmdev.weatherjhonsapp.data

import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import io.reactivex.Single

interface RemoteMockyDataSource {
    fun getAllApiinf(): Single<ResponseData>
    fun getAllApiweather(latitud: Double,longitud:Double): Single<WeatherResponse>
}