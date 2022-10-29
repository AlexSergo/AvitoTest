package com.example.testavito.domain.models

data class WeatherWeek(
    val monthName: String,
    val day: Int,
    val weatherForHours: List<Weather>
)
