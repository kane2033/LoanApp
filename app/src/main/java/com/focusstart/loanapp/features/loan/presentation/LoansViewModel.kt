package com.focusstart.loanapp.features.loan.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.Event
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoansList
import kotlinx.coroutines.Job

class LoansViewModel
@ViewModelInject constructor(private val getLoansList: GetLoansList) : ViewModel() {

    private val job = Job()

    val loans = MutableLiveData<List<Loan>>()

    var selectedLoan = MutableLiveData<Loan>()

    private val _failure = MutableLiveData<Event<Failure>>()

    val failure: LiveData<Event<Failure>>
        get() = _failure

    init {
        getLoansList()
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

    private fun handleFailure(failure: Failure) {
        this._failure.value = Event(failure)
    }

    fun setSelectedLoan(position: Int) {
        loans.value?.get(position).let { selectedLoan.value = it }
    }

    override fun onCleared() {
        job.cancel()
        super.onCleared()
    }
}