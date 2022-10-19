package com.example.weatherapplication.utils

import com.example.weatherapplication.utils.ExceptionUtil.handleException
import retrofit2.Response

abstract class NetworkRequest {

    suspend fun <T> apiRequest(apiCall: suspend () -> Response<T>): Result<T> {
        try {
            val response = apiCall()
            if (response.isSuccessful) {
                val body = response.body()
                body?.let {
                    return Result.Success(body)
                }
            }
            return Result.Error("Api Response : ${response.code()} ${response.message()}")
        } catch (throwable: Throwable) {
            return handleException(throwable)
        }
    }

}
