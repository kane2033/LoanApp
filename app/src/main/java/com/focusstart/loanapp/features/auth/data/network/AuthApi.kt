package com.focusstart.loanapp.features.auth.data.network

import com.focusstart.loanapp.features.auth.data.model.RegisteredUserModel
import com.focusstart.loanapp.features.auth.domain.entity.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("login")
    suspend fun loginAsync(@Body user: User): Response<String>

    @POST("registration")
    suspend fun registerAsync(@Body user: User): Response<RegisteredUserModel>
}