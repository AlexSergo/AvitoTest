package com.example.testavito.presentation.ui

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testavito.R
import com.example.testavito.data.remote_data_source.repository.RepositoryInitializer
import com.example.testavito.databinding.ActivityMainBinding
import com.example.testavito.domain.models.City
import com.example.testavito.presentation.view_model.CityViewModel
import com.example.testavito.presentation.view_model.CityViewModelFactory
import com.example.testavito.presentation.view_model.WeatherViewModel
import com.example.testavito.presentation.view_model.WeatherViewModelFactory


enum class WeatherSwitch {
    DAY,
    WEEK
}

interface OnCityChanged{
    fun setCity(city: City)
}

interface WeatherActivity{
    fun showWeatherInfo(fragment: Fragment)
    fun viewModelInit()
    fun setOnClickListeners()
}

class MainActivity : AppCompatActivity(), OnCityChanged, WeatherActivity{

    private lateinit var binding: ActivityMainBinding
    private var dialog: SearchFragment? = null
    private var weatherSwitch = WeatherSwitch.DAY
    private lateinit var cityViewModel: CityViewModel
    private lateinit var weatherViewModel: WeatherViewModel
    private lateinit var onCityChosen: OnCityChosen
    private var currentCity: City = City(524901, "Moscow", "RU")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, MainFragment())
            .commit()
        setContentView(binding.root)
        dialog = SearchFragment()
        setOnClickListeners()
        viewModelInit()
    }

    override fun showWeatherInfo(fragment: Fragment){
        onCityChosen = fragment as OnCityChosen
        binding.city.text = currentCity.name
        onCityChosen.getWeather(currentCity.id)
    }

    override fun setCity(city: City){
        binding.city.text = city.name
        currentCity = city
        dialog?.dismiss()
        onCityChosen.getWeather(city.id)
    }

    override fun viewModelInit(){
        val cityViewModelFactory = CityViewModelFactory(RepositoryInitializer.getCityRepository(this))
        cityViewModel = ViewModelProvider(this, cityViewModelFactory)
            .get(CityViewModel::class.java)
        val weatherViewModelFactory = WeatherViewModelFactory(RepositoryInitializer.getWeatherRepository(this))
        weatherViewModel = ViewModelProvider(this, weatherViewModelFactory)
            .get(WeatherViewModel::class.java)
    }

    override fun setOnClickListeners() {
        binding.city.setOnClickListener {
            dialog?.show(supportFragmentManager, "ActionBottomDialog")
        }

        binding.day.setOnClickListener {
            if (weatherSwitch == WeatherSwitch.WEEK) {
                binding.day.setTextColor(Color.BLACK)
                binding.week.setTextColor(Color.GRAY)
                weatherSwitch = WeatherSwitch.DAY

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, MainFragment())
                    .commit()
            }
        }

        binding.week.setOnClickListener {
            if (weatherSwitch == WeatherSwitch.DAY) {
                binding.week.setTextColor(Color.BLACK)
                binding.day.setTextColor(Color.GRAY)
                weatherSwitch = WeatherSwitch.WEEK

                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView, WeatherForWeekFragment())
                    .commit()
            }
        }
    }
}