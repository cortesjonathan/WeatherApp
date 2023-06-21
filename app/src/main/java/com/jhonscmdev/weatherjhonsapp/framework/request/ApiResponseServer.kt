package com.jhonscmdev.weatherjhonsapp.framework.request

import com.google.gson.annotations.SerializedName

data class RemoteResponseData(
    @SerializedName("message") val message: String,
    @SerializedName("user_id") val userid: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("mobile") val mobile: Int,
    @SerializedName("profile_details") val profile_details: RemoteProfileDetails,
    @SerializedName("data_list") val data_list: ArrayList<RemoteDataListDetail>,
)
data class RemoteProfileDetails(
    @SerializedName("is_profile_complete") val is_profile_complete: Boolean,
    @SerializedName("rating") val rating: Double,
)
data class RemoteDataListDetail(
    @SerializedName("id") val id: Int,
    @SerializedName("value") val value: String
)

data class RemoteWeatherResponse(
    val base: String,
    val clouds: RemoteClouds,
    val cod: Int,
    val coord: RemoteCoord,
    val dt: Int,
    val id: Int,
    val main: RemoteMain,
    val name: String,
    val sys: RemoteSys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<RemoteWeather>,
    val wind: RemoteWind
)
data class RemoteClouds(
    val all: Int
)
data class RemoteCoord(
    val lat: Double,
    val lon: Double
)
data class RemoteMain(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)
data class RemoteSys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)
data class RemoteWeather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
data class RemoteWind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)