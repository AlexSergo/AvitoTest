package com.example.testavito.data.remote_data_source.repository

import android.content.Context
import com.example.testavito.data.remote_data_source.api.CityApi
import com.example.testavito.data.remote_data_source.api.WeatherApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryInitializer {
    private var weatherApi: WeatherApi? = null
    private lateinit var weatherRepository: WeatherRepositoryImpl
    private var cityApi: CityApi? = null
    private lateinit var cityRepository: CityRepositoryImpl

    fun getWeatherRepository(context: Context): WeatherRepositoryImpl {
        if (weatherApi == null){
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("http://api.openweathermap.org")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            weatherApi = retrofit.create(WeatherApi::class.java)
        }
        if (weatherApi != null)
            weatherRepository = WeatherRepositoryImpl(weatherApi!!,
                apiKey = "450421f48c5e4a18a7c9b25726407d82")
        return weatherRepository
    }

    fun getCityRepository(context: Context): CityRepositoryImpl {
        if (cityApi == null){
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("https://cities.vldsx.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            cityApi = retrofit.create(CityApi::class.java)
        }
        if (cityApi != null)
            cityRepository = CityRepositoryImpl(cityApi!!)
        return cityRepository
    }
}