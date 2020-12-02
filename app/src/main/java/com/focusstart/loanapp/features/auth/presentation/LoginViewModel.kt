package com.focusstart.loanapp.features.auth.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.features.auth.domain.interactor.Login
import com.focusstart.loanapp.features.auth.domain.interactor.UserLoggedIn
import kotlinx.coroutines.Job

/**
 * [ViewModel], оперирующая UseCase-ом [Login]
 * и хранящая [failure] для обработки ошибок со стороны интерфейса (фрагмента)
 * */
class LoginViewModel
@ViewModelInject constructor(private val login: Login,
                             private val userLoggedIn: UserLoggedIn) : ViewModel() {

    // Экземпляр работы, использующийся
    private val job = Job()

    private var _isLoggedIn = MutableLiveData<Event<Boolean>>()

    val isLoggedIn: LiveData<Event<Boolean>>
        get() = _isLoggedIn

    private var _failure = MutableLiveData<Event<Failure>>()

    val failure: LiveData<Event<Failure>>
        get() = _failure

    init {
        // Проверка на пройденность авторизации
        // при запуске приложения
        checkLoggedIn()
    }

    private fun checkLoggedIn() {
        userLoggedIn.invoke(
                params = None(),
                onResult = { it.either(::handleFailure, ::handleCheckLoggedIn) },
                job = job
        )
    }

    private fun handleCheckLoggedIn(isLoggedIn: Boolean) {
        this._isLoggedIn.value = Event(isLoggedIn)
    }

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

    private fun handleFailure(failure: Failure) {
        this._failure.value = Event(failure)
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}