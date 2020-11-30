package com.focusstart.loanapp.features.auth.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.auth.domain.interactor.Login
import kotlinx.coroutines.Job

/**
* [ViewModel], оперирующая UseCase-ом [Login]
 * и хранящая [failure] для обработки ошибок со стороны интерфейса (фрагмента)
* */
class LoginViewModel
@ViewModelInject constructor(private val login: Login): ViewModel() {

    // Экземпляр работы, использующийся
    private val job = Job()

    var token: MutableLiveData<String> = MutableLiveData() // лайвдата токен только на время

    private var _failure = MutableLiveData<Event<Failure>>()

    val failure : LiveData<Event<Failure>>
        get() = _failure

    fun login(name: String, password: String) {
        login.invoke(
            params = User(name, password),
            onResult = { it.either(::handleFailure, ::handleLogin)},
            job = job
        )
    }

    private fun handleLogin(token: String) {
        this.token.value = token
    }

    private fun handleFailure(failure: Failure) {
        this._failure.value = Event(failure)
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}