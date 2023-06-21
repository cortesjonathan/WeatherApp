package com.jhonscmdev.weatherjhonsapp.framework.request

import com.jhonscmdev.weatherjhonsapp.framework.request.ApiConstants.API_URL_MOCKY
import com.jhonscmdev.weatherjhonsapp.framework.request.ApiConstants.API_URL_WEATHER
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ServerService {
    @GET(API_URL_MOCKY)
   fun getAllmockyInfo(): Single<RemoteResponseData>

   @GET(API_URL_WEATHER)
   fun getAllWeather(
       @Query("lat") latitud: Double,
       @Query("lon") longitud: Double,
   ): Single<RemoteWeatherResponse>

}