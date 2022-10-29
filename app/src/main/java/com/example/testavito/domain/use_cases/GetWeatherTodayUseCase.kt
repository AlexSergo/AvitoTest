package com.example.testavito.domain.use_cases

import com.example.testavito.domain.models.Weather
import com.example.testavito.domain.repository.WeatherRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetWeatherTodayUseCase(private val weatherRepository: WeatherRepository){

    suspend fun execute(cityId: Int): List<Weather>{
        val fullData = weatherRepository.getWeatherToday(cityId)
        val result = mutableListOf<Weather>()
        for (data in fullData.list){
            val date = LocalDate.parse(data.dtTxt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val time = data.dtTxt.substring(11,17)
            if (date.dayOfMonth != LocalDate.now().dayOfMonth)
                continue
            result.add(Weather(
                temperature = data.main.temp,
                feelsLike = data.main.feelsLike,
                weather = data.weather[0].main,
                icon = data.weather[0].icon,
                tempMin = data.main.tempMin,
                tempMax = data.main.tempMax,
                pressure = data.main.pressure,
                windSpeed = data.wind.speed,
                windDeg = data.wind.deg,
                time = "$date $time UTC"))
        }
        return result
    }
}