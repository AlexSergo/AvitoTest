package com.example.testavito.domain.models

import java.util.*

data class Weather(
    val temperature: Float,
    val feelsLike: Float,
    val weather: String,
    val tempMin: Float,
    val tempMax: Float,
    val pressure: Int,
    val windSpeed: Float,
    val windDeg: Int,
    val time: String,
    val icon: String
)