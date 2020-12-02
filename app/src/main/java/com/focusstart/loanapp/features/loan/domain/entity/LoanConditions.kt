package com.focusstart.loanapp.features.loan.domain.entity

data class LoanConditions(
    val percent: Double,
    val period: Int,
    val maxAmount: Int
)