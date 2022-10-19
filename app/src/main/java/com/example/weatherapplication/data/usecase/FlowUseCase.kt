package com.example.weatherapplication.data.usecase

import com.example.weatherapplication.utils.ExceptionUtil
import com.example.weatherapplication.utils.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<in P, T> {

    suspend fun execute(parameters: P? = null): Flow<Result<T>> {

        return buildUseCase(parameters)
            .catch { e ->
                val errorResult = ExceptionUtil.handleException<T>(e)
                emit(
                    errorResult
                )
            }.flowOn(Dispatchers.IO)
    }

    abstract fun buildUseCase(parameters: P?): Flow<Result<T>>

}