package com.focusstart.loanapp.core.data.network.interceptor

import com.focusstart.loanapp.core.domain.repository.TokenRepository
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
* Добавление bearer токена в каждый запрос.
 * @property tokenRepository - репозиторий, хранящий токен (shared pref. или др.)
* */
class AuthInterceptor
    @Inject constructor(private val tokenRepository: TokenRepository): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val token = tokenRepository.getToken() ?: ""
        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", token)
            .build()

        return chain.proceed(newRequest)
    }
}