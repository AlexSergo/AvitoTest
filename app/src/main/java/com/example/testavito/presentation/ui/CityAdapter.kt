package com.example.testavito.presentation.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testavito.databinding.CityItemBinding
import com.example.testavito.domain.models.City

class CityAdapter(private val cityCallback: CityCallback): RecyclerView.Adapter<CityAdapter.CityViewHolder>() {

    private var cities = mutableListOf<City>()

    fun set(cities: List<City>){
        this.cities = cities.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = CityItemBinding.inflate(inflater,parent, false)
        return CityViewHolder(binding, cityCallback)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        cities.getOrNull(position)?.let { city->
            holder.init(city)
        }
    }

    override fun getItemCount(): Int {
        return cities.count()
    }

    class CityViewHolder(var binding: CityItemBinding, private val cityCallback: CityCallback)
        : RecyclerView.ViewHolder(binding.root){

        fun init(city: City){
            binding.city.text = city.name + ", " + city.country

            binding.city.setOnClickListener {
                cityCallback.setCity(city)
            }
        }
    }
}
