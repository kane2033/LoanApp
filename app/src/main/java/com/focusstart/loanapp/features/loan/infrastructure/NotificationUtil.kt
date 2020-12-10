package com.focusstart.loanapp.features.loan.infrastructure

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.focusstart.loanapp.R
import com.focusstart.loanapp.core.ui.MainActivity

/*
* Класс, отвечающий за создание и отсылку уведомлений
* */
object NotificationUtil {

    private const val DEFAULT_CHANNEL_ID = "0"
    private const val NOTIFICATION_ID = 111

    fun createNotification(context: Context, message: String) {
        // Получаем менеджер уведомлений
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        // Создаем канал уведомлений
        createNotificationChannel(context)?.let { notificationManager.createNotificationChannel(it) }
        // Создаем уведомление и отображаем его
        notificationManager.notify(
            NOTIFICATION_ID,
            buildNotification(context, NOTIFICATION_ID, message)
        )
        Log.e("LOAN_NOTIF", "NotificationUtil.createNotification() - notification sent!")
    }

    // Канал уведомлений создается перед отправкой
    private fun createNotificationChannel(context: Context): NotificationChannel? {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                DEFAULT_CHANNEL_ID,
                context.getString(R.string.notification_channel_name),
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = context.getString(R.string.notification_channel_description)
            }
        } else null
    }

    // Базовый метод создания уведомления
    private fun buildNotification(
        context: Context,
        notificationId: Int,
        msg: String
    ): Notification {
        val pendingIntent = PendingIntent.getActivity(
            context,
            notificationId,
            Intent(context, MainActivity::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Builder(context, DEFAULT_CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(context.getString(R.string.notification_title))
            .setContentText(msg)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(msg)
            )
            .build()
    }

    // Отмена уведомления
    fun cancelNotification(context: Context) {
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancel(NOTIFICATION_ID)
    }
}