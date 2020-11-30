package com.focusstart.loanapp.features.auth.presentation

import android.widget.Toast
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.focusstart.loanapp.features.auth.domain.entity.User
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.features.auth.domain.interactor.Register
import kotlinx.coroutines.Job

class RegisterViewModel
@ViewModelInject constructor(private val register: Register): ViewModel() {

    private val job = Job()

    var failure: MutableLiveData<Failure> = MutableLiveData()

    fun register(name: String, password: String) {
        register.invoke(
            params = User(name, password),
            onResult = { it.either(::handleFailure, ::handleRegister)},
            job = job
        )
    }

    private fun handleRegister(nothing: Unit) {
        //Toast.makeText(context, "Регистрация прошла успешно", Toast.LENGTH_SHORT).show()
    }

    private fun handleFailure(failure: Failure) {
        this.failure.value = failure
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}