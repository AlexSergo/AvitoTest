package com.example.testavito.data.remote_data_source.repository

import com.example.testavito.data.remote_data_source.api.WeatherApi
import com.example.testavito.data.remote_data_source.models.Weather.CurrentWeatherResponse
import com.example.testavito.data.remote_data_source.models.Weather.WeatherResponse
import com.example.testavito.domain.repository.WeatherRepository

class WeatherRepositoryImpl(private val weatherApi: WeatherApi, private val apiKey: String): WeatherRepository {
    override suspend fun getWeatherToday(id: Int): WeatherResponse {
        return weatherApi.getWeatherToday(id, apiKey)
    }

    override suspend fun getWeatherForWeek(id: Int): WeatherResponse {
        return weatherApi.getWeatherForWeek(id, apiKey)
    }

    override suspend fun getCurrentWeather(id: Int): CurrentWeatherResponse {
        return weatherApi.getCurrentWeather(id, apiKey)
    }
}