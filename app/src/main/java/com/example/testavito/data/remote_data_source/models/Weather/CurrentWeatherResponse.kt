package com.example.testavito.data.remote_data_source.models.Weather

data class CurrentWeatherResponse(
    val coord: CityCoord,
    val weather: List<Weather>,
    val base: String,
    val main: MainData,
    val visibility: Int,
    val wind: Wind,
    val clouds: Clouds,
    val dt: Long,
    val sys: Sys,
    val timeZone: Int,
    val id: Int,
    val name: String,
    val cod: Int
)