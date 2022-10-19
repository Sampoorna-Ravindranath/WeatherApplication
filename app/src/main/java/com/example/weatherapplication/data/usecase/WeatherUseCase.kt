package com.example.weatherapplication.data.usecase

import com.example.weatherapplication.data.model.WeatherResponseModel
import com.example.weatherapplication.data.repository.MainRepository
import com.example.weatherapplication.utils.Result
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherUseCase @Inject constructor(
    private val repository: MainRepository,
) : FlowUseCase<WeatherUseCase.Params, WeatherResponseModel>() {

    data class Params constructor(val city: String) {
        companion object {
            fun create(city: String) = Params(city)
        }
    }

    override fun buildUseCase(parameters: Params?): Flow<Result<WeatherResponseModel>> {
        return repository.getWeatherData(city = parameters?.city.toString())
    }
}