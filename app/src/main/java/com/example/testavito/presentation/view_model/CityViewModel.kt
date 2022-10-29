package com.example.testavito.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testavito.domain.models.City
import com.example.testavito.domain.repository.CityRepository
import com.example.testavito.domain.use_cases.GetCitiesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(private val cityRepository: CityRepository): ViewModel() {
    private val searchedLiveData = MutableLiveData<List<City>>()

    private val getCitiesUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetCitiesUseCase(cityRepository)
    }

    fun getSearchedCitiesLiveData(): LiveData<List<City>>{
        return searchedLiveData
    }

    fun getSearchedCities(query: String) = viewModelScope.launch(Dispatchers.IO) {
        searchedLiveData.postValue(getCitiesUseCase.execute(query))
    }

}