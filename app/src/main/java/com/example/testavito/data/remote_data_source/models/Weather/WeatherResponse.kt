package com.example.testavito.data.remote_data_source.models.Weather


data class WeatherResponse(
    val cod: Int,
    val message: Int,
    val cnt: Int,
    val list: List<WeatherData>,
    val city: City
)
