package com.jhonscmdev.weatherjhonsapp.framework.request

import com.jhonscmdev.weatherjhonsapp.data.RemoteMockyDataSource
import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MockyRetrofitDataSource(
    private val serverRequest: ServerRequest,
) : RemoteMockyDataSource {
    override fun getAllApiinf(): Single<ResponseData> {
        return serverRequest
            .getService<ServerService>()
            .getAllmockyInfo()
            .map { remoteResponseData(it)}
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getAllApiweather(latitud: Double,longitud:Double): Single<WeatherResponse> {
        return serverRequest
            .getService<ServerService>()
            .getAllWeather(latitud, longitud)
            .map { remoteResponseWeater(it) }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }
}