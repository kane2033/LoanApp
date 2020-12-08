package com.focusstart.loanapp.features.loan.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoansList

class LoansViewModel
@ViewModelInject constructor(private val getLoansList: GetLoansList) : BaseViewModel() {

    val loans = MutableLiveData<List<Loan>>()

    var selectedLoan = MutableLiveData<Loan>()

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

}