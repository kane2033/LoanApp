package com.focusstart.loanapp.features.loan.data.datasource

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions

interface LoanDataSource {

    suspend fun getLoanConditions(): Either<Failure, LoanConditions>

    suspend fun createLoan(conditions: LoanConditions): Either<Failure, Loan>

    suspend fun getLoanById(id: Int): Either<Failure, Loan>

    suspend fun getLoansList(): Either<Failure, List<Loan>>
}