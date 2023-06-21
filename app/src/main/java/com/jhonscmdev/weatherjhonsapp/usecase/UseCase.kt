package com.jhonscmdev.weatherjhonsapp.usecase

import com.jhonscmdev.weatherjhonsapp.data.MockyRepository
import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import io.reactivex.Single

class getAllMockyUseCase(
    private val mockyRepository: MockyRepository,
) {
    fun invoke(): Single<ResponseData> = mockyRepository.getAllmocky()
    fun invoke(latitud: Double,longitud:Double): Single<WeatherResponse> = mockyRepository.getAllWeather(latitud, longitud)
}