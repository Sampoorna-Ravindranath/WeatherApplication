package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.api.ApiHelper
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiHelper: ApiHelper) {

    suspend fun getWeatherData(city:String) =  apiHelper.getWeatherData(city =city)

}