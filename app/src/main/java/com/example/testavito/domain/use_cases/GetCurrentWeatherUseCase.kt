package com.example.testavito.domain.use_cases

import com.example.testavito.domain.models.Weather
import com.example.testavito.domain.repository.WeatherRepository
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import kotlin.math.min

class GetCurrentWeatherUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(cityId: Int): Weather{
        val currentData = weatherRepository.getCurrentWeather(cityId)
        val time = LocalDateTime.now(TimeZone.getTimeZone("UTC").toZoneId())
        val hours = if (time.hour < 10)
            "0" + time.hour.toString()
        else
            time.hour.toString()
        val minutes = if (time.minute < 10)
            "0" + time.minute.toString()
        else
            time.minute.toString()
        return Weather(
            temperature = currentData.main.temp,
            feelsLike = currentData.main.feelsLike,
            weather = currentData.weather[0].main,
            tempMax = currentData.main.tempMax,
            tempMin = currentData.main.tempMin,
            icon = currentData.weather[0].icon,
            time = "$hours:$minutes",
            pressure = currentData.main.pressure,
            windSpeed = currentData.wind.speed,
            windDeg = currentData.wind.deg)
    }
}