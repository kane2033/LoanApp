package com.focusstart.loanapp.features.loan.domain.entity

data class LoanCreated(
        val amount: Int,
        val period: Int,
        val percent: Double,
        val firstName: String,
        val lastName: String,
        val phoneNumber: String,
)