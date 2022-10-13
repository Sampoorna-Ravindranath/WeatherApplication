package com.example.weatherapplication.data.api

import com.example.weatherapplication.data.model.WeatherResponseModel
import retrofit2.Response

interface ApiHelper {
    suspend fun getWeatherData(city: String): Response<WeatherResponseModel>
}