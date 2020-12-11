package com.focusstart.loanapp.features.loan.infrastructure

import android.content.Context
import android.os.Bundle
import androidx.hilt.Assisted
import androidx.hilt.work.WorkerInject
import androidx.navigation.NavDeepLinkBuilder
import androidx.work.ListenableWorker.Result.success
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.focusstart.loanapp.R

class NotifyWork
@WorkerInject constructor(@Assisted appContext: Context, @Assisted params: WorkerParameters) :
    Worker(appContext, params) {

    companion object {
        const val WORK_NAME = "LOAN_REMINDER"
        const val MESSAGE_KEY = "MESSAGE"
        const val ID_KEY = "ID_KEY"
        const val ID_DEFAULT = 0
    }

    override fun doWork(): Result {
        val message = inputData.getString(MESSAGE_KEY) ?: ""
        val id = inputData.getInt(ID_KEY, ID_DEFAULT)

        // Создание явной ссылки (explicit deep link) в виде pendingIntent
        val pendingIntent = NavDeepLinkBuilder(applicationContext)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.loanDetailsFragment)
            .setArguments(Bundle().apply { putInt(ID_KEY, id) })
            .createPendingIntent()

        NotificationUtil.createNotification(applicationContext, pendingIntent, message)
        return success()
    }
}