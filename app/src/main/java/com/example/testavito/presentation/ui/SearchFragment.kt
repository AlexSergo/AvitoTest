package com.example.testavito.presentation.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.testavito.data.remote_data_source.repository.RepositoryInitializer
import com.example.testavito.databinding.FragmentSearchBinding
import com.example.testavito.domain.models.City
import com.example.testavito.presentation.view_model.CityViewModel
import com.example.testavito.presentation.view_model.CityViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


interface CityCallback{
    fun setCity(city: City)
}

interface OnCityChosen{
    fun getWeather(cityId: Int)
}

class SearchFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var cityAdapter: CityAdapter
    private lateinit var cityViewModel: CityViewModel
    private var compl = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)

        cityAdapter = CityAdapter(object : CityCallback{
            override fun setCity(city: City) {
                val onCityChanged = requireActivity() as OnCityChanged
                onCityChanged.setCity(city)
            }

        })
        binding.cityRecyclerview.adapter = cityAdapter

        val cityViewModelFactory = CityViewModelFactory(RepositoryInitializer.getCityRepository(requireContext()))
        cityViewModel = ViewModelProvider(this, cityViewModelFactory)
            .get(CityViewModel::class.java)

        binding.search.clearFocus()
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val newList = mutableListOf<City>()
                newText?.let {
                    if (newText.isEmpty())
                        return true
                    compl = cityViewModel.getSearchedCities(newText).isCompleted
                    cityViewModel.getSearchedCitiesLiveData().observe(requireActivity(), Observer { cities->
                        for (item in cities) {
                            if (item.name.lowercase().contains(it.lowercase()))
                                newList.add(item)
                        }
                        cityAdapter.set(newList)
                        newList.clear()
                    })
                    return true
                }
                return false
            }

        })
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}