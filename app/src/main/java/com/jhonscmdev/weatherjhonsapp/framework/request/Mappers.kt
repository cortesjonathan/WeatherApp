package com.jhonscmdev.weatherjhonsapp.framework.request
import com.jhonscmdev.weatherjhonsapp.domain.Clouds
import com.jhonscmdev.weatherjhonsapp.domain.Coord
import com.jhonscmdev.weatherjhonsapp.domain.DataListDetail
import com.jhonscmdev.weatherjhonsapp.domain.Main
import com.jhonscmdev.weatherjhonsapp.domain.ProfileDetails
import com.jhonscmdev.weatherjhonsapp.domain.ResponseData
import com.jhonscmdev.weatherjhonsapp.domain.Sys
import com.jhonscmdev.weatherjhonsapp.domain.Weather
import com.jhonscmdev.weatherjhonsapp.domain.WeatherResponse
import com.jhonscmdev.weatherjhonsapp.domain.Wind

fun remoteResponseData(remoteResponseData: RemoteResponseData): ResponseData {
    return ResponseData(
        remoteResponseData.message,
        remoteResponseData.userid,
        remoteResponseData.name,
        remoteResponseData.email,
        remoteResponseData.mobile,
        remoteResponseData.profile_details.let { profileinfo -> ProfileDetails(profileinfo.is_profile_complete,profileinfo.rating) },
        remoteResponseData.data_list.map {datalist -> DataListDetail(datalist.id,datalist.value)
        }
    )
}

fun remoteResponseWeater(remoteWeather: RemoteWeatherResponse): WeatherResponse {
    return WeatherResponse(
        remoteWeather.base,
        remoteWeather.clouds.let { remoteClouds -> Clouds(remoteClouds.all) },
        remoteWeather.cod,
        remoteWeather.coord.let { remoteCoord -> Coord(remoteCoord.lat,remoteCoord.lon) },
        remoteWeather.dt,
        remoteWeather.id,
        remoteWeather.main.let { remoteMain -> Main(remoteMain.feels_like,remoteMain.humidity,remoteMain.pressure,remoteMain.temp,remoteMain.temp_max,remoteMain.temp_min) },
        remoteWeather.name,
        remoteWeather.sys.let { remoteSys -> Sys(remoteSys.country,remoteSys.id,remoteSys.sunrise,remoteSys.sunset,remoteSys.type) },
        remoteWeather.timezone,
        remoteWeather.visibility,
        remoteWeather.weather.map { remoteWeather -> Weather(
            remoteWeather.description,
            remoteWeather.icon,
            remoteWeather.id,
            remoteWeather.main
        ) },
        remoteWeather.wind.let { remoteWint-> Wind(
            remoteWint.deg,
            remoteWint.gust,
            remoteWint.speed
        ) }
    )
}