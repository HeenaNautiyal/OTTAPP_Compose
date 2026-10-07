package com.shiki.ott_ai.data.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.util.JsonToken
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.android.datatransport.runtime.util.PriorityMapping.toInt
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.shiki.ott_ai.MainActivity
import com.shiki.ott_ai.R

class MyFirebaseMessagingService : FirebaseMessagingService(){
    override fun onNewToken(token: String){
        super.onNewToken(token)
        Log.d("FCM token","New Token $token")
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
       val title = message.notification?.title ?:"New Notification"
        val body = message.notification?.body?: ""
        val data = message.data

        showNotification(title,body,data)
    }

    private fun showNotification(
        title: String,
        body: String,
        data: MutableMap<String, String>
    ) {
        val channel_id= "defaultChannel"
        val notificationManager= getSystemService(NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channel_id,
                "GernalNotification",
                NotificationManager.IMPORTANCE_HIGH
            )
            notificationManager.createNotificationChannel(channel)
        }
        val intent= Intent(this, MainActivity::class.java).apply {
            flags= Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            data.forEach { (key,value) -> putExtra(key,value) }
        }

        val pendingIntent = PendingIntent.getActivity(this,
            0,
            intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        val notification= NotificationCompat.Builder(this,channel_id)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(R.drawable.image_icon_launcher)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(System.currentTimeMillis().toInt(),notification)
    }


}