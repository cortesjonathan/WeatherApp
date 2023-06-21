package com.jhonscmdev.weatherjhonsapp.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import com.jhonscmdev.weatherjhonsapp.usecase.getAllMockyUseCase
import io.reactivex.disposables.CompositeDisposable

class HomeViewModel(
    private val getAllMockyUseCase: getAllMockyUseCase
) : ViewModel() {
    private val disposable = CompositeDisposable()

    private val _mockyValues  = MutableLiveData<ResponseData>()
    val mockyvalues: LiveData<ResponseData> get() = _mockyValues

    private val _weatherValues = MutableLiveData<WeatherResponse>()
    val weatherValues: LiveData<WeatherResponse> get() = _weatherValues

    private val _mockyInf = MutableLiveData<Boolean>()
    val mockyInf: LiveData<Boolean> get() = _mockyInf

    private val  _events = MutableLiveData<Event<MockyInformation>>()
    val events: LiveData<Event<MockyInformation>> get() = _events


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
    fun getAllMocky() {
        disposable
            .add(
                getAllMockyUseCase
                    .invoke()
                    .doOnSubscribe{}
                    .subscribe({ mockyInfo ->
                        System.out.println(mockyInfo)
                        _events.value = Event(MockyInformation.ShowGeneralInfo(mockyInfo))
                    }, { error ->
                        _events.value = Event(MockyInformation.ShowErrorGeneralInfo(error))
                    })
            )
    }
    fun getAllWeatherInfo(latitud: Double,longitud:Double){
        disposable
            .add(
                getAllMockyUseCase
                    .invoke(latitud, longitud)
                    .doOnSubscribe { }
                    .subscribe({ weather ->
                        System.out.println(weather)
                        _events.value = Event(MockyInformation.ShowWeatherInfo(weather))
                    },{ error ->
                        _events.value = Event(MockyInformation.ShowErrorWeatherInfo(error))
                    })
            )
    }

    sealed class  MockyInformation {
        data class ShowGeneralInfo(val responseData: ResponseData): MockyInformation()
        data class ShowErrorGeneralInfo(val  error: Throwable): MockyInformation()
        data class ShowWeatherInfo(val weatherResponse: WeatherResponse): MockyInformation()
        data class ShowErrorWeatherInfo(val error: Throwable): MockyInformation()
    }
}