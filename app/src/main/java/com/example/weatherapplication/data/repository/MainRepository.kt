package com.example.weatherapplication.data.repository

import com.example.weatherapplication.data.api.RemoteDataSource
import com.example.weatherapplication.data.model.BaseApiResponse
import com.example.weatherapplication.data.model.WeatherResponseModel
import com.example.weatherapplication.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : BaseApiResponse() {
    suspend fun getWeatherData(city: String): Flow<Result<WeatherResponseModel>> {
        return flow {
            emit(safeApiCall { remoteDataSource.getWeatherData(city = city) })
        }.flowOn(Dispatchers.IO)
    }
}

