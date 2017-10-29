package io.feelfree.estimotezabytki.ui

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import io.feelfree.estimotezabytki.R
import android.support.v4.app.NotificationCompat


class NotificationCreator {

    private val CHANNEL_ID = "ESTIMOTE_SCAN"
    private val CHANNEL_NAME = "Estimote bluetooth scan notifications"
    private val CHANNEL_DESCRIPTION = "Blah blah blah"
    private val NOTIFICATION_TITLE = "Szlak\u00AE"
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
        val description = CHANNEL_DESCRIPTION
        val importance = android.app.NotificationManager.IMPORTANCE_HIGH
        val mChannel = NotificationChannel(id, name, importance)
        mChannel.description = description
        val mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as android.app.NotificationManager
        mNotificationManager.createNotificationChannel(mChannel)
    }


}