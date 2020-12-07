package com.focusstart.loanapp.core.data.repository

import android.content.SharedPreferences
import com.focusstart.loanapp.core.domain.repository.LanguageRepository
import java.util.*
import javax.inject.Inject

class LanguageRepositoryImpl
@Inject constructor(private val sharedPreferences: SharedPreferences) : LanguageRepository {

    companion object {
        private const val LANGUAGE_KEY = "LANGUAGE"
    }

    // Если первый запуск, возвращаем язык системы
    override fun getAppLanguage(): String =
            sharedPreferences.getString(LANGUAGE_KEY, "en") ?: Locale.getDefault().language

    override fun setAppLanguage(language: String) {
        with(sharedPreferences.edit()) {
            putString(LANGUAGE_KEY, language)
            apply()
        }
    }

    override fun clearAppLanguage() {
        with(sharedPreferences.edit()) {
            remove(LANGUAGE_KEY)
            apply()
        }
    }
}