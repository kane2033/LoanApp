package com.focusstart.loanapp.features.settings.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.settings.domain.interactor.Logout

class SettingsViewModel
@ViewModelInject constructor(private val logout: Logout) : BaseViewModel() {

    private var _isLoggedOut = MutableLiveData<Event<Boolean>>()

    val isLoggedOut: LiveData<Event<Boolean>>
        get() = _isLoggedOut

    fun logout() {
        logout.invoke(
                params = None(),
                onResult = { it.either(::handleFailure, ::handleLogout) },
                job = job
        )
    }

    private fun handleLogout(nothing: Unit) {
        _isLoggedOut.value = Event(true)
    }
}