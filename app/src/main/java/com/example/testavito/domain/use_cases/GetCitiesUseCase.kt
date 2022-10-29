package com.example.testavito.domain.use_cases

import com.example.testavito.domain.models.City
import com.example.testavito.domain.repository.CityRepository

class GetCitiesUseCase(private val cityRepository: CityRepository) {

    suspend fun execute(query: String): List<City>{
        val cities = cityRepository.getCities(query)
        var result = mutableListOf<City>()
        for (city in cities.items){
            result.add(
                City(id = city.id, name = city.name, country = city.country)
            )
        }
        return result
    }
}