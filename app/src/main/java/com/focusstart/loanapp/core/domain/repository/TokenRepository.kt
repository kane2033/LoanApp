package com.focusstart.loanapp.core.domain.repository

/**
* Абстракция репозитория токенов.
* */
interface TokenRepository {

    fun saveToken(token: String)

    fun getToken(): String?

    fun deleteToken()

    fun hasToken(): Boolean
}