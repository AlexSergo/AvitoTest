package com.example.testavito.data.remote_data_source.repository

import com.example.testavito.data.remote_data_source.api.CityApi
import com.example.testavito.data.remote_data_source.models.City.CityResponse
import com.example.testavito.domain.repository.CityRepository

class CityRepositoryImpl(private val cityApi: CityApi): CityRepository {
    override suspend fun getCities(query: String): CityResponse {
        return cityApi.getCities(query)
    }
}