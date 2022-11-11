package com.ahmetturk.definex.repository

import com.ahmetturk.definex.network.NetworkResult
import com.ahmetturk.definex.network.RetrofitBuilder.apiService
import com.ahmetturk.definex.network.main.ProductListResponse

class MainRepository {

    private val discoverFirstHorizontalListCache = Cache<NetworkResult<ProductListResponse>?>()

    private val discoverSecondHorizontalListCache = Cache<NetworkResult<ProductListResponse>?>()

    private val discoverThirdTwoColumnListCache = Cache<NetworkResult<ProductListResponse>?>()

    suspend fun getDiscoverFirstHorizontalList(): NetworkResult<ProductListResponse> {
        return checkCacheThenDownload(discoverFirstHorizontalListCache) {
            apiService.discoverFirstHorizontalList()
        }
    }

    suspend fun getDiscoverSecondHorizontalList(): NetworkResult<ProductListResponse> {
        return checkCacheThenDownload(discoverSecondHorizontalListCache) {
            apiService.discoverSecondHorizontalList()
        }
    }

    suspend fun getDiscoverThirdTwoColumnList(): NetworkResult<ProductListResponse> {
        return checkCacheThenDownload(discoverThirdTwoColumnListCache) {
            apiService.discoverThirthTwoColumnList()
        }
    }
}
