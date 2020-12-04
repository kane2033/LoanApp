package com.focusstart.loanapp.features.loan.data.network

import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoanApi {

    @GET("loans/conditions")
    suspend fun getLoanConditions(): Response<LoanConditions>

    @POST("loans")
    suspend fun createLoan(@Body loan: LoanCreated): Response<Loan>

    @GET("loans/{id}")
    suspend fun getLoanById(@Path("id") id: Int): Response<Loan>

    @GET("loans/all")
    suspend fun getLoansList(): Response<List<Loan>>
}