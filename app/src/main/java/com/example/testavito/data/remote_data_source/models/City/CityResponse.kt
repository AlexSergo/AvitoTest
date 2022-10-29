package com.example.testavito.data.remote_data_source.models.City

data class CityResponse(
    val errors: List<String>,
    val items: List<City>,
    val status: String,
    val totalItems: Int
)
