package com.focusstart.loanapp.features.settings.presentation

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.features.settings.domain.interactor.Logout
import com.focusstart.loanapp.features.settings.domain.interactor.SetLanguage

class SettingsViewModel
@ViewModelInject constructor(private val logout: Logout, private val setLanguage: SetLanguage) : BaseViewModel() {

    fun logout() {
        logout.invoke(
                params = None(),
                onResult = { it.either(::handleFailure) {} },
                job = job
        )
    }

    fun setLanguage(context: Context, languageCode: String) {
        setLanguage.invoke(
                params = Pair(context, languageCode),
                onResult = { it.either(::handleFailure) { } },
                job = job
        )
    }

}