package com.example.testavito.data.remote_data_source.models.Weather

import com.google.gson.annotations.SerializedName
import java.util.*

data class WeatherData(
    val dt: Long,
    val main: MainData,
    val weather: List<Weather>,
    val clouds: Clouds,
    val wind: Wind,
    val visibility: Int,
    val pop: Float,
    val sys: Sys,
    @SerializedName("dt_txt")
    val dtTxt: String
)