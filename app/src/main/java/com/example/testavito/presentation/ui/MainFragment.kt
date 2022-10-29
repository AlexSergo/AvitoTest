package com.example.testavito.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testavito.R
import com.example.testavito.data.remote_data_source.repository.RepositoryInitializer
import com.example.testavito.databinding.FragmentMainBinding
import com.example.testavito.domain.models.City
import com.example.testavito.domain.models.Weather
import com.example.testavito.presentation.view_model.CityViewModel
import com.example.testavito.presentation.view_model.CityViewModelFactory
import com.example.testavito.presentation.view_model.WeatherViewModel
import com.example.testavito.presentation.view_model.WeatherViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MainFragment() : Fragment(),  OnCityChosen {

    private lateinit var binding: FragmentMainBinding
    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var cityViewModel: CityViewModel
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater)
        weatherAdapter = WeatherAdapter()
        binding.weatherRecyclerview.adapter = weatherAdapter
        binding.weatherRecyclerview.layoutManager = GridLayoutManager(requireActivity(), 1)
        viewModelInit()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = requireActivity() as WeatherActivity
        callback.showWeatherInfo(this)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun viewModelInit(){
        val cityViewModelFactory = CityViewModelFactory(RepositoryInitializer.getCityRepository(requireContext()))
        cityViewModel = ViewModelProvider(this, cityViewModelFactory)
            .get(CityViewModel::class.java)
        val weatherViewModelFactory = WeatherViewModelFactory(RepositoryInitializer.getWeatherRepository(requireContext()))
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)
            .get(WeatherViewModel::class.java)
    }

    override fun getWeather(cityId: Int) {
        weatherViewModel.getCurrentWeather(cityId)
        weatherViewModel.getCurrentWeatherLiveData().observe(requireActivity(), Observer {
            it?.let {
                binding.currentTemperature.text = it.temperature.toInt().toString() + "°"
                binding.currentTime.text = it.time
                Picasso.get().load("https://openweathermap.org/img/w/" + it.icon + ".png").into(binding.weatherImage)
                binding.feelsLike.text = "Feels like: " + it.feelsLike.toInt().toString() + "°"
                binding.windSpeed.text = "Wind speed: " + it.windSpeed.toInt().toString()
                binding.windDegree.text = "Wind degree: " + it.windDeg.toString() + "°"
            }
        })
        weatherViewModel.getTodayWeather(cityId)
        weatherViewModel.getTodayWeatherLiveData().observe(requireActivity(), Observer{
            it?.let {
                weatherAdapter.set(it)
            }
        })
    }

}