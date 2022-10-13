package com.example.weatherapplication.data.api

import com.example.weatherapplication.data.model.WeatherResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/weather?")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("appid") api_key: String = "d418b560899fa9f2311b6b1c76926af8"
    ): Response<WeatherResponseModel>
}