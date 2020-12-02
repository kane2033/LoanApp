package com.focusstart.loanapp.features.loan.data.repository

import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.loan.data.datasource.LoanDataSource
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.repository.LoanRepository
import javax.inject.Inject

class LoanRepositoryImpl
@Inject constructor(private val loanDataSource: LoanDataSource) : LoanRepository {

    override suspend fun getLoanConditions(): Either<Failure, LoanConditions> =
        loanDataSource.getLoanConditions()

    override suspend fun createLoan(conditions: LoanConditions): Either<Failure, Loan> =
        loanDataSource.createLoan(conditions)

    override suspend fun getLoanById(id: Int): Either<Failure, Loan> =
        loanDataSource.getLoanById(id)

    override suspend fun getLoansList(): Either<Failure, List<Loan>> =
        loanDataSource.getLoansList()
}