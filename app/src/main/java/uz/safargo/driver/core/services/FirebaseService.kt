package uz.safargo.driver.core.services

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService

import com.google.firebase.messaging.RemoteMessage
import uz.safargo.driver.MainActivity
import uz.safargo.driver.R

//import timber.log.Timber


class FirebaseService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }


    override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        val notificationManager =
            (getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)

        val data = p0.data
        val title = data["title"] ?: ""
        val body = data["body"] ?: ""

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(getChannel())
        }
        Log.i("", "auto_start: show notification")
        notificationManager.notify(100, getNotification(1000, title, body))
        val launch = Intent(Intent.ACTION_MAIN)
        launch.setClass(this, MainActivity::class.java)
        launch.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        Log.i("", "auto_start: start app")
        this.startActivity(launch)
        Log.i("", "auto_start: started app")
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun getChannel(): NotificationChannel {

        val soundAttributes: AudioAttributes = AudioAttributes.Builder()
            .setUsage(AudioAttributes.USAGE_NOTIFICATION)
            .build()

        val channel = NotificationChannel(
            "wave_notification_channel",
            "Wave Notification Channel",
            NotificationManager.IMPORTANCE_HIGH,

            )
//
        channel.enableLights(true)
        channel.enableVibration(true)

        channel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        channel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
        channel.enableVibration(true)
        channel.setSound(
            Uri.parse("android.resource://" + packageName + "/" + R.raw.wave),
            soundAttributes
        )
        return channel
    }


    private fun getNotification(requestId: Long, title: String, subtitle: String): Notification {

        val open: PendingIntent = PendingIntent.getActivity(
            this,
            requestId.toInt(),
            Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                putExtra("launch", "PROXY")
                putExtra("id", requestId)
            },
            PendingIntent.FLAG_IMMUTABLE
        )

        val builder = NotificationCompat.Builder(this, requestId.toString())
            .setChannelId("wave_notification_channel")
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentTitle(title)
            .setContentText(subtitle)
            .setContentIntent(open)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setShowWhen(false)
            .setOngoing(false)

        return builder.build()
    }
}