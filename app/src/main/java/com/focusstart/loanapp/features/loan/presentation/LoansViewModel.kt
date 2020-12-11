package com.focusstart.loanapp.features.loan.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoanById
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoansList
import com.focusstart.loanapp.features.loan.domain.interactor.UserLoggedIn

class LoansViewModel
@ViewModelInject constructor(
    private val getLoansList: GetLoansList,
    private val getLoanById: GetLoanById,
    private val userLoggedIn: UserLoggedIn
) : BaseViewModel() {

    val loans = MutableLiveData<List<Loan>>()

    var selectedLoan = MutableLiveData<Loan>()

    private var _isLoggedIn = MutableLiveData<Event<Boolean>>()

    val isLoggedIn: LiveData<Event<Boolean>>
        get() = _isLoggedIn

    init {
        // Проверка на пройденность авторизации
        // при запуске приложения
        checkLoggedIn()
    }

    fun getLoansList() {
        getLoansList.invoke(
            params = None(),
            onResult = { it.either(::handleFailure, ::handleLoans) },
            job = job
        )
    }

    private fun handleLoans(loans: List<Loan>) {
        this.loans.value = loans
    }

    fun setSelectedLoan(position: Int) {
        loans.value?.get(position).let { selectedLoan.value = it }
    }

    fun getLoanById(id: Int) {
        getLoanById.invoke(
            params = id,
            onResult = { it.either(::handleFailure, ::handleLoanById) },
            job = job
        )
    }

    private fun handleLoanById(loan: Loan) {
        selectedLoan.value = loan
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

}