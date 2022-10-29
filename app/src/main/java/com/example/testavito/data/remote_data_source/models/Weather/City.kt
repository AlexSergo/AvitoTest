package com.example.testavito.data.remote_data_source.models.Weather

data class City(
    val id: Int,
    val name: String,
    val coord: CityCoord,
    val country: String,
    val population: Int,
    val timeZone: Int,
    val sunrise: Long,
    val sunSet: Long
)