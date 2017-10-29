package io.feelfree.estimotezabytki.utils

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import io.feelfree.estimotezabytki.R
import android.support.v4.app.NotificationCompat


class NotificationCreator {

    private val CHANNEL_ID = "ESTIMOTE_SZLAK"
    private val CHANNEL_NAME = "Estimote szlak skanowanie"
    private val NOTIFICATION_TITLE = "Szlak"
    private val NOTIFICATION_TEXT = "Szukanie punktu szlaku..."

    fun create(context: Context): Notification =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) createNotificationForOreo(context)
            else createNotificationForPreOreo(context)

    private fun createNotificationForPreOreo(context: Context): Notification {
        return NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_directions_walk)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(NOTIFICATION_TEXT)
                .setChannelId(CHANNEL_ID)
                .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationForOreo(context: Context): Notification {
        createNotificationChannel(context)
        return Notification.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_directions_walk)
                .setContentTitle(NOTIFICATION_TITLE)
                .setContentText(NOTIFICATION_TEXT)
                .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(context: Context) {
        val id = CHANNEL_ID
        val name = CHANNEL_NAME
        val importance = android.app.NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        mNotificationManager.createNotificationChannel(mChannel)
    }


}