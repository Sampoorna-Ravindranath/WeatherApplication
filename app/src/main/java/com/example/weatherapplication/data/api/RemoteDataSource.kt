package com.example.weatherapplication.data.api

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getWeatherData(city: String) = apiService.getWeatherData(city = city)

}