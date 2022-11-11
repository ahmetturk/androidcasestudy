package com.ahmetturk.definex.repository

import com.ahmetturk.definex.network.NetworkResult
import com.ahmetturk.definex.network.apiCall

class Cache<T>(var value: T? = null)

suspend fun <T> checkCacheThenDownload(
    cache: Cache<NetworkResult<T>?>,
    service: suspend () -> T
): NetworkResult<T> {
    return cache.value ?: apiCall {
        service()
    }.also { result ->
        cache.value = result
    }
}
