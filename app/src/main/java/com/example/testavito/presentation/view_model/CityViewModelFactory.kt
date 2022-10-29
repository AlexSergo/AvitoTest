package com.example.testavito.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testavito.domain.repository.CityRepository

class CityViewModelFactory(private val cityRepository: CityRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java))
            return CityViewModel(cityRepository) as T
        throw IllegalAccessException("ViewModel not found!")
    }
}