package com.focusstart.loanapp.features.loan.data.network

import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class LoanApiImpl
    @Inject constructor(retrofit: Retrofit): LoanApi {

    private val api by lazy { retrofit.create(LoanApi::class.java) }

    override suspend fun getLoanConditions(): Response<LoanConditions> = api.getLoanConditions()

    override suspend fun createLoan(loan: LoanCreated): Response<Loan> =
            api.createLoan(loan)

    override suspend fun getLoanById(id: Int): Response<Loan> = api.getLoanById(id)

    override suspend fun getLoansList(): Response<List<Loan>> = api.getLoansList()


}