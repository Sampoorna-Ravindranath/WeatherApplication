package com.example.weatherapplication.utils

import java.io.IOException
import java.net.SocketTimeoutException

object ExceptionUtil {

    fun <T> handleException(throwable: Throwable): Result<T> {
        return when (throwable) {
            is SocketTimeoutException -> Result.Error("Request timed out")
            is IOException -> Result.Error("Check network connection")
            else -> Result.Error("Exception : ${throwable.message.toString()}")
        }
    }
}