package com.ahmetturk.definex.network

import com.ahmetturk.definex.network.login.LoginRequest
import com.ahmetturk.definex.network.login.LoginResponse
import com.ahmetturk.definex.network.main.ProductListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {

    @POST("login.json")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

    @GET("discover_first_horizontal_list.json")
    suspend fun discoverFirstHorizontalList(): ProductListResponse

    @GET("discover_second_horizontal_list.json")
    suspend fun discoverSecondHorizontalList(): ProductListResponse

    @GET("discover_thirth_two_column_list.json")
    suspend fun discoverThirthTwoColumnList(): ProductListResponse

}
