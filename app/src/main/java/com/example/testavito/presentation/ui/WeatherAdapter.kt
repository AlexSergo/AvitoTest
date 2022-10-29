package com.example.testavito.presentation.ui

import android.R
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testavito.databinding.WeatherItemBinding
import com.example.testavito.domain.models.Weather
import com.squareup.picasso.Picasso


class WeatherAdapter: RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder>() {

    private var weatherForHours = mutableListOf<Weather>()

    fun set(weatherList: List<Weather>){
        weatherForHours = weatherList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeatherItemBinding.inflate(inflater)
        return WeatherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        weatherForHours.getOrNull(position)?.let { weather->
            holder.init(weather)
        }
    }

    override fun getItemCount(): Int {
        return weatherForHours.count()
    }

    class WeatherViewHolder(private val binding: WeatherItemBinding): RecyclerView.ViewHolder(binding.root){
        fun init(weather: Weather){
            binding.time.text = weather.time.substring(11, 16)
            binding.temperature.text = weather.temperature.toInt().toString() + "°"
            Picasso.get().load("http://openweathermap.org/img/w/" + weather.icon + ".png").into(binding.weatherImage)
            binding.feelsLike.text = "Feels like: " + weather.feelsLike.toInt().toString() + "°"
            binding.windSpeed.text = "Wind speed: " + weather.windSpeed.toInt().toString()
            binding.windDegree.text = "Wind degree: " + weather.windDeg.toString() + "°"
        }
    }
}