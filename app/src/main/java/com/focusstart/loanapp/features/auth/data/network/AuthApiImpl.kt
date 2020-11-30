package com.focusstart.loanapp.features.auth.data.network

import com.focusstart.loanapp.features.auth.domain.entity.User
import retrofit2.Retrofit
import javax.inject.Inject

class AuthApiImpl
@Inject constructor(retrofit: Retrofit) : AuthApi {

    private val api by lazy { retrofit.create(AuthApi::class.java) }

    override suspend fun loginAsync(user: User) = api.loginAsync(user)

    override suspend fun registerAsync(newUser: User) = api.registerAsync(newUser)
}