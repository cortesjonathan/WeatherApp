package com.jhonscmdev.weatherjhonsapp.domain

data class ResponseData(
    val message: String,
    val user_id: Int,
    val name: String,
    val email: String,
    val mobile: Int,
    val profile_details: ProfileDetails,
    val data_list: List<DataListDetail>,
)
data class ProfileDetails(
    val is_profile_complete: Boolean,
    val rating: Double,
)
data class DataListDetail(
    val id: Int,
    val value: String,
)
data class WeatherResponse(
    val base: String,
    val clouds: Clouds,
    val cod: Int,
    val coord: Coord,
    val dt: Int,
    val id: Int,
    val main: Main,
    val name: String,
    val sys: Sys,
    val timezone: Int,
    val visibility: Int,
    val weather: List<Weather>,
    val wind: Wind
)
data class Clouds(
    val all: Int
)
data class Coord(
    val lat: Double,
    val lon: Double
)
data class Main(
    val feels_like: Double,
    val humidity: Int,
    val pressure: Int,
    val temp: Double,
    val temp_max: Double,
    val temp_min: Double
)
data class Sys(
    val country: String,
    val id: Int,
    val sunrise: Int,
    val sunset: Int,
    val type: Int
)
data class Weather(
    val description: String,
    val icon: String,
    val id: Int,
    val main: String
)
data class Wind(
    val deg: Int,
    val gust: Double,
    val speed: Double
)
