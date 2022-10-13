package com.example.weatherapplication.data.api

import com.example.weatherapplication.data.model.WeatherResponseModel
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {
    override suspend fun getWeatherData(city: String): Response<WeatherResponseModel> =
        apiService.getWeatherData(city = city)
}