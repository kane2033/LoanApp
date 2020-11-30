package com.focusstart.loanapp.features.loan.data.network

import retrofit2.Retrofit
import javax.inject.Inject

class LoanApiImpl
    @Inject constructor(retrofit: Retrofit): LoanApi {

    private val api by lazy { retrofit.create(LoanApi::class.java) }
}