package com.focusstart.loanapp.features.loan.presentation

import android.content.Context
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.work.Data
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.domain.interactor.None
import com.focusstart.loanapp.core.presentation.BaseViewModel
import com.focusstart.loanapp.features.loan.domain.entity.Loan
import com.focusstart.loanapp.features.loan.domain.entity.LoanConditions
import com.focusstart.loanapp.features.loan.domain.entity.LoanCreated
import com.focusstart.loanapp.features.loan.domain.interactor.CreateLoan
import com.focusstart.loanapp.features.loan.domain.interactor.GetLoansConditions
import com.focusstart.loanapp.features.loan.infrastructure.NotifyWork
import java.util.concurrent.TimeUnit

class LoanCreateViewModel
@ViewModelInject constructor(
    private val createLoan: CreateLoan,
    private val getLoansConditions: GetLoansConditions
) : BaseViewModel() {

    val loanConditions = MutableLiveData<LoanConditions>()

    val createdLoan = MutableLiveData<Loan>()

    init {
        // Получаем условия для займа
        // при открытии окна создания займа
        getLoanConditions()
    }

    private fun getLoanConditions() {
        getLoansConditions.invoke(
            params = None(),
            onResult = { it.either(::handleFailure, ::handleLoanConditions) },
            job = job
        )
    }

    private fun handleLoanConditions(conditions: LoanConditions) {
        loanConditions.value = conditions
    }

    fun createLoan(loan: LoanCreated) {
        createLoan.invoke(
            params = loan,
            onResult = { it.either(::handleFailure, ::handleLoanCreation) },
            job = job
        )
    }

    private fun handleLoanCreation(loans: Loan) {
        this.createdLoan.value = loans
    }

    // Создание напоминания о необходимости погашения займа
    // с помощью уведомления
    fun scheduleReminder(context: Context, loan: Loan) {
        val message = context.getString(
            R.string.notification_message,
            loan.lastName,
            loan.firstName,
            loan.repaymentDate,
            loan.repaymentAmount
        )
        val data = Data.Builder().putString(NotifyWork.MESSAGE_KEY, message).build()
        val notificationWork =
            OneTimeWorkRequestBuilder<NotifyWork>()
                // Создаем напоминание за два дня до даты возврата
                .setInitialDelay((loan.period - 2).toLong(), TimeUnit.DAYS)
                .setInputData(data)
                .build()

        WorkManager.getInstance(context).beginUniqueWork(
            loan.id.toString(),
            ExistingWorkPolicy.REPLACE,
            notificationWork
        ).enqueue()
    }
}