package com.focusstart.loanapp.features.loan.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import com.focusstart.loanapp.features.loan.domain.interactor.CreateLoan
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoansConditions

class LoanCreateViewModel
@ViewModelInject constructor(private val createLoan: CreateLoan,
                             private val getLoansConditions: GetLoansConditions) : BaseViewModel() {

    val loanConditions = MutableLiveData<LoanConditions>()

    val createdLoan = MutableLiveData<Loan>()

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
}