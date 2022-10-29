package com.example.testavito.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testavito.databinding.WeekItemBinding
import com.example.testavito.domain.models.Weather
import com.example.testavito.domain.models.WeatherWeek

class WeekAdapter: RecyclerView.Adapter<WeekAdapter.WeekItemViewHolder>() {

    private var weatherForWeek = mutableListOf<WeatherWeek>()

    fun set(weatherList: List<WeatherWeek>){
        weatherForWeek = weatherList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = WeekItemBinding.inflate(inflater)
        return WeekItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WeekItemViewHolder, position: Int) {
        weatherForWeek.getOrNull(position)?.let { weatherForDay->
            holder.init(weatherForDay)
        }
    }

    override fun getItemCount(): Int {
        return weatherForWeek.count()
    }

    class WeekItemViewHolder(private val binding: WeekItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun init(weather: WeatherWeek){
            val day = weather.day.toString()
            val name = weather.monthName
            binding.day.text = "$day $name"
            val weatherAdapter = WeatherAdapter()
            binding.nestedRecyclerview.adapter = weatherAdapter
            weatherAdapter.set(weather.weatherForHours)
        }
    }

}
