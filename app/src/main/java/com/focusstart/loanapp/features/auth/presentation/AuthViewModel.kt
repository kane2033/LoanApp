package com.focusstart.loanapp.features.auth.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.features.auth.domain.interactor.Login
import com.focusstart.loanapp.features.auth.domain.interactor.Register

/**
 * [ViewModel], оперирующая UseCase-ом [Login]
 * и хранящая [failure] для обработки ошибок со стороны интерфейса (фрагмента)
 * */
class AuthViewModel
@ViewModelInject constructor(private val login: Login,
                             private val register: Register) : BaseViewModel() {

    private var _isLoggedIn = MutableLiveData<Event<Boolean>>()

    val isLoggedIn: LiveData<Event<Boolean>>
        get() = _isLoggedIn

    private var _isRegistered = MutableLiveData<Event<Boolean>>()

    val isRegistered: LiveData<Event<Boolean>>
        get() = _isRegistered

    fun login(name: String, password: String) {
        login.invoke(
                params = User(name, password),
                onResult = { it.either(::handleFailure, ::handleLogin) },
                job = job
        )
    }

    private fun handleLogin(token: String) {
        this._isLoggedIn.value = Event(true)
    }

    fun register(name: String, password: String) {
        register.invoke(
                params = User(name, password),
                onResult = { it.either(::handleFailure, ::handleRegister) },
                job = job
        )
    }

    private fun handleRegister(nothing: Any) {
        _isRegistered.value = Event(true)
    }
}