package com.example.testavito.data.remote_data_source.models.Weather

import com.google.gson.annotations.SerializedName

data class MainData(
    val temp: Float,
    @SerializedName("feels_like")
    val feelsLike: Float,
    @SerializedName("temp_min")
    val tempMin: Float,
    @SerializedName("temp_max")
    val tempMax: Float,
    val pressure: Int,
    val seaLevel: Int,
    val grndLevel: Int,
    val humidity: Int,
    val tempKf: Float
)