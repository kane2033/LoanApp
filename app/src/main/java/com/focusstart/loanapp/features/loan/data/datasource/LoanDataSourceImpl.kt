package com.focusstart.loanapp.features.loan.data.datasource

import com.focusstart.loanapp.core.data.network.SafeApiCall
import com.focusstart.loanapp.core.domain.exception.Failure
import com.focusstart.loanapp.core.domain.functional.Either
import com.focusstart.loanapp.features.loan.data.network.LoanApi
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import javax.inject.Inject

class LoanDataSourceImpl
@Inject constructor(private val loanApi: LoanApi) : LoanDataSource {

    override suspend fun getLoanConditions(): Either<Failure, LoanConditions> =
            SafeApiCall.safeApiResult { loanApi.getLoanConditions() }

    override suspend fun createLoan(loan: LoanCreated): Either<Failure, Loan> =
            SafeApiCall.safeApiResult { loanApi.createLoan(loan) }

    override suspend fun getLoanById(id: Int): Either<Failure, Loan> =
            SafeApiCall.safeApiResult { loanApi.getLoanById(id) }

    override suspend fun getLoansList(): Either<Failure, List<Loan>> =
            SafeApiCall.safeApiResult { loanApi.getLoansList() }

}