@file:Suppress("MatchingDeclarationName")

package com.ahmetturk.definex.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

sealed class NetworkResult<out T> {
    data class Success<out T>(val value: T) : NetworkResult<T>()
    data class Error(val error: String) : NetworkResult<Nothing>()
}

@Suppress("TooGenericExceptionCaught")
suspend fun <T> apiCall(apiCall: suspend () -> T): NetworkResult<T> = withContext(Dispatchers.IO) {
    try {
        NetworkResult.Success(apiCall.invoke())
    } catch (exception: Exception) {
        when (exception) {
            is IOException -> {
                NetworkResult.Error(exception.message ?: "Internet error")
            }
            is HttpException -> {
                NetworkResult.Error(exception.message ?: "Http Error")
            }
            else -> {
                NetworkResult.Error(exception.message ?: "Error")
            }
        }
    }
}
