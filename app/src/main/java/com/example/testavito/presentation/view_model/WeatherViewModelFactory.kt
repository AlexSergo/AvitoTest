package com.example.testavito.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testavito.domain.repository.WeatherRepository

class WeatherViewModelFactory(private val weatherRepository: WeatherRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WeatherViewModel::class.java))
            return WeatherViewModel(weatherRepository) as T
        throw IllegalAccessException("ViewModel not found!")
    }
}