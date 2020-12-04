package com.focusstart.loanapp.features.loan.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import com.focusstart.loanapp.features.loan.domain.interactor.CreateLoan
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoansConditions
import kotlinx.coroutines.Job

class LoanCreateViewModel
@ViewModelInject constructor(private val createLoan: CreateLoan,
                             private val getLoansConditions: GetLoansConditions) : ViewModel() {

    private val job = Job()

    val loanConditions = MutableLiveData<LoanConditions>()

    val createdLoan = MutableLiveData<Loan>()

    private val _failure = MutableLiveData<Event<Failure>>()

    val failure: LiveData<Event<Failure>>
        get() = _failure

    init {
        // Получаем условия для займа
        // при открытии окна создания займа
        getLoanConditions()
    }

    private fun getLoanConditions() {
        getLoansConditions.invoke(
                params = None(),
                onResult = { it.either(::handleFailure, ::handleLoanConditions) },
                job = job
        )
    }

    private fun handleLoanConditions(conditions: LoanConditions) {
        loanConditions.value = conditions
    }

    fun createLoan(loan: LoanCreated) {
        createLoan.invoke(
                params = loan,
                onResult = { it.either(::handleFailure, ::handleLoanCreation) },
                job = job
        )
    }

    private fun handleLoanCreation(loans: Loan) {
        this.createdLoan.value = loans
    }

    private fun handleFailure(failure: Failure) {
        this._failure.value = Event(failure)
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}