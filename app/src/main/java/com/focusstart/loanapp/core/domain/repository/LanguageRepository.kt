package com.focusstart.loanapp.core.domain.repository

/**
 * Абстрактный репозиторий
 * языка приложения.
 * */
interface LanguageRepository {

    fun getAppLanguage(): String

    fun setAppLanguage(language: String)

    fun clearAppLanguage()
}