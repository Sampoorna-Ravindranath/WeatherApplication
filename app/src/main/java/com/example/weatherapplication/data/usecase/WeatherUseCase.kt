package com.example.weatherapplication.data.usecase

import com.example.weatherapplication.data.model.WeatherResponseModel
import com.example.weatherapplication.data.repository.MainRepository
import com.example.weatherapplication.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(private val repository: MainRepository) {

    suspend operator fun invoke(city: String): Flow<Result<WeatherResponseModel>> {
       return repository.getWeatherData(city)
    }
}