package com.focusstart.loanapp.features.loan.presentation

import com.focusstart.loanapp.R
import com.focusstart.loanapp.features.loan.domain.entity.LoanState
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

/**
 * Вспомогательный класс для установки полей займа
 * */
object LoanFieldsSetter {

    // Установка иконки в соответствие с enum займа
    fun setImageStatus(state: LoanState): Int {
        return when (state) {
            LoanState.APPROVED -> R.drawable.ic_approved
            LoanState.REGISTERED -> R.drawable.ic_registered
            LoanState.REJECTED -> R.drawable.ic_rejected
        }
    }

    // Установка текста статуса займа в соответсвтие с enum займа
    fun setTextStatus(state: LoanState): Int {
        return when (state) {
            LoanState.APPROVED -> R.string.loan_approved
            LoanState.REGISTERED -> R.string.loan_registered
            LoanState.REJECTED -> R.string.loan_rejected
        }
    }

    // Установка даты займа в формате dd.mm.yy
    fun setDate(date: String): String {
        val parsedDate = LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        return parsedDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    }
}