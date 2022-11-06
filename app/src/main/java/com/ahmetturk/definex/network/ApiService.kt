package com.ahmetturk.definex.network

import com.ahmetturk.definex.network.login.LoginRequest
import com.ahmetturk.definex.network.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("login.json")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): LoginResponse

}