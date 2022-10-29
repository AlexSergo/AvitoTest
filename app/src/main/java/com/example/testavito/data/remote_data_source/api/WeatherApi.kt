package com.example.testavito.data.remote_data_source.api

import com.example.testavito.data.remote_data_source.models.Weather.CurrentWeatherResponse
import com.example.testavito.data.remote_data_source.models.Weather.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/forecast?cnt=8&units=metric")
    suspend fun getWeatherToday(@Query("id") id: Int, @Query("appid") apiKey: String): WeatherResponse

    @GET("/data/2.5/forecast?units=metric")
    suspend fun getWeatherForWeek(@Query("id") id: Int, @Query("appid") apiKey: String): WeatherResponse

    @GET("/data/2.5/weather?units=metric")
    suspend fun getCurrentWeather(@Query("id") id: Int, @Query("appid") apiKey: String): CurrentWeatherResponse
}