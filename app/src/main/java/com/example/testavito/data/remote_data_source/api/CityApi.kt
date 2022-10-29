package com.example.testavito.data.remote_data_source.api

import com.example.testavito.data.remote_data_source.models.City.CityResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CityApi {
    @GET("/api?mode=0&fields=id,name,country")
    suspend fun getCities(@Query("q") query: String): CityResponse
}