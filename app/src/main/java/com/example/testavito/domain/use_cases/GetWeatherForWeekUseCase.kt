package com.example.testavito.domain.use_cases

import com.example.testavito.domain.models.Weather
import com.example.testavito.domain.models.WeatherWeek
import com.example.testavito.domain.repository.WeatherRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class GetWeatherForWeekUseCase(private val weatherRepository: WeatherRepository) {

    suspend fun execute(cityId: Int): List<WeatherWeek>{
        val fullData = weatherRepository.getWeatherForWeek(cityId)
        val result = mutableListOf<WeatherWeek>()
        val weatherList = mutableListOf<Weather>()
        var prevDay = LocalDate.now().dayOfMonth
        var prevMonth = LocalDate.now().month

        for (data in fullData.list){
            val date = LocalDate.parse(data.dtTxt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            val time = data.dtTxt.substring(11,17)

            if (date.dayOfMonth != prevDay){
                val list = mutableListOf<Weather>()
                for (item in weatherList)
                    list.add(item)
                result.add(WeatherWeek(
                    day = prevDay,
                    monthName = prevMonth.name,
                    weatherForHours = list
                ))
                prevMonth = date.month
                prevDay = date.dayOfMonth
                weatherList.clear()
            }

            weatherList.add(Weather(
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