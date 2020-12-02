package com.focusstart.loanapp.core.data.repository

import android.content.SharedPreferences
import com.focusstart.loanapp.core.domain.repository.TokenRepository
import javax.inject.Inject

/**
* Репозиторий Bearer токена, реализованный с помощью
 * [SharedPreferences].
* */
class TokenRepositoryImpl
    @Inject constructor(private val sharedPreferences: SharedPreferences): TokenRepository {

    companion object {
        private const val TOKEN_KEY = "TOKEN"
    }

    override fun saveToken(token: String) {
        with (sharedPreferences.edit()) {
            putString(TOKEN_KEY, token)
            apply()
        }
    }

    override fun getToken(): String? = sharedPreferences.getString(TOKEN_KEY, "")

    override fun deleteToken() {
        with(sharedPreferences.edit()) {
            remove(TOKEN_KEY)
            apply()
        }
    }

    override fun hasToken() = getToken()!!.isNotEmpty()

}