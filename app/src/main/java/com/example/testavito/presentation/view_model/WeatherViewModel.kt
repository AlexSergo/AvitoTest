package com.example.testavito.presentation.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testavito.domain.models.Weather
import com.example.testavito.domain.models.WeatherWeek
import com.example.testavito.domain.repository.WeatherRepository
import com.example.testavito.domain.use_cases.GetCurrentWeatherUseCase
import com.example.testavito.domain.use_cases.GetWeatherForWeekUseCase
import com.example.testavito.domain.use_cases.GetWeatherTodayUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel() {

    private val currentWeatherLiveData = MutableLiveData<Weather>()
    private val todayWeatherLiveData = MutableLiveData<List<Weather>>()
    private val weekWeatherLiveData = MutableLiveData<List<WeatherWeek>>()
    private val getCurrentWeatherUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetCurrentWeatherUseCase(weatherRepository)
    }
    private val getWeatherForPeriodUseCase by lazy(LazyThreadSafetyMode.NONE){
        GetWeatherTodayUseCase(weatherRepository)
    }
    private val getWeatherForWeekUseCase by  lazy(LazyThreadSafetyMode.NONE){
        GetWeatherForWeekUseCase(weatherRepository)
    }

    fun getCurrentWeatherLiveData(): LiveData<Weather>{
        return currentWeatherLiveData
    }

    fun getTodayWeatherLiveData(): LiveData<List<Weather>>{
        return todayWeatherLiveData
    }

    fun getWeekWeatherLiveData(): LiveData<List<WeatherWeek>>{
        return weekWeatherLiveData
    }

    fun getCurrentWeather(cityId: Int) = viewModelScope.launch(Dispatchers.IO){
        currentWeatherLiveData.postValue(getCurrentWeatherUseCase.execute(cityId))
    }

    fun getTodayWeather(cityId: Int) = viewModelScope.launch(Dispatchers.IO){
        todayWeatherLiveData.postValue(getWeatherForPeriodUseCase.execute(cityId))
    }

    fun getWeatherForWeek(cityId: Int) = viewModelScope.launch(Dispatchers.IO){
        weekWeatherLiveData.postValue(getWeatherForWeekUseCase.execute(cityId))
    }

}