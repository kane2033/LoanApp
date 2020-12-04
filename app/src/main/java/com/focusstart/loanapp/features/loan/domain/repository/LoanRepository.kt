package com.focusstart.loanapp.features.loan.domain.repository

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated

interface LoanRepository {

    suspend fun getLoanConditions(): Either<Failure, LoanConditions>

    suspend fun createLoan(loan: LoanCreated): Either<Failure, Loan>

    suspend fun getLoanById(id: Int): Either<Failure, Loan>

    suspend fun getLoansList(): Either<Failure, List<Loan>>
}