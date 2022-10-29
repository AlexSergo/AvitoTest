package com.example.testavito.presentation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testavito.data.remote_data_source.repository.RepositoryInitializer
import com.example.testavito.databinding.FragmentWeatherForWeekBinding
import com.example.testavito.presentation.view_model.WeatherViewModel
import com.example.testavito.presentation.view_model.WeatherViewModelFactory


class WeatherForWeekFragment() : Fragment(), OnCityChosen {

    private lateinit var binding: FragmentWeatherForWeekBinding
    private lateinit var weekAdapter: WeekAdapter
    private lateinit var weatherViewModel: WeatherViewModel

    override fun onAttach(context: Context) {
        val weatherViewModelFactory = WeatherViewModelFactory(RepositoryInitializer.getWeatherRepository(requireContext()))
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)
            .get(WeatherViewModel::class.java)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentWeatherForWeekBinding.inflate(layoutInflater)

        weekAdapter = WeekAdapter()
        binding.weatherRecyclerview.adapter = weekAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val callback = requireActivity() as WeatherActivity
        callback.showWeatherInfo(this)
        super.onViewCreated(view, savedInstanceState)
    }


    override fun getWeather(cityId: Int) {
        weatherViewModel.getWeatherForWeek(cityId)
        weatherViewModel.getWeekWeatherLiveData().observe(requireActivity(), Observer {
            it?.let {
                weekAdapter.set(it)
            }
        })
    }
}