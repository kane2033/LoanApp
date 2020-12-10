package com.focusstart.loanapp.features.loan.domain.entity

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Loan(
    val amount: Int,
    val date: String,
    val firstName: String,
    val id: Int,
    val lastName: String,
    val percent: Double,
    val period: Int,
    val phoneNumber: String,
    val state: LoanState
) {

    val repaymentAmount
        get() = calcRepaymentAmount(amount, percent)

    val repaymentDate
        get() = calcRepaymentDate(date, period)


    companion object {

        // Расчет суммы, необходимой для возврата займа
        fun calcRepaymentAmount(amount: Int, percent: Double) =
            amount + calcPercent(amount, percent)

        fun calcPercent(amount: Int, percent: Double) = amount * percent / 100.0

        // Отсчет от сегодняшней даты
        fun calcRepaymentDate(period: Int) = calcRepaymentDate(
            LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME),
            period
        )

        // Расчет даты возврата займа
        fun calcRepaymentDate(date: String, period: Int): String {
            val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
            return parsedDate.plusDays(period.toLong())
                .format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        }
    }
}