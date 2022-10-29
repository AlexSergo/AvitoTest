package com.example.testavito.domain.repository

import com.example.testavito.data.remote_data_source.models.City.CityResponse

interface CityRepository {
    suspend fun getCities(query: String): CityResponse
}