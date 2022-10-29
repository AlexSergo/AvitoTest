package com.example.testavito.domain.repository

import com.example.testavito.data.remote_data_source.models.Weather.CurrentWeatherResponse
import com.example.testavito.data.remote_data_source.models.Weather.WeatherResponse

interface WeatherRepository {

    suspend fun getWeatherToday(id: Int): WeatherResponse

    suspend fun getWeatherForWeek(id: Int): WeatherResponse

    suspend fun getCurrentWeather(id: Int): CurrentWeatherResponse
}