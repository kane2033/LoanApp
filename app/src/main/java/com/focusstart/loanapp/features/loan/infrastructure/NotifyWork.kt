package com.focusstart.loanapp.features.loan.infrastructure

import android.content.Context
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters

class NotifyWork
@WorkerInject constructor(@Assisted appContext: Context, @Assisted params: WorkerParameters) :
    Worker(appContext, params) {

    companion object {
        const val WORK_NAME = "LOAN_REMINDER"
        const val MESSAGE_KEY = "MESSAGE"
    }

    override fun doWork(): Result {
        val message = inputData.getString(MESSAGE_KEY) ?: ""
        NotificationUtil.createNotification(applicationContext, message)
        return success()
    }
}