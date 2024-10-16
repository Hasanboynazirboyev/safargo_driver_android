package uz.safargo.driver.core.helpers

import android.annotation.SuppressLint
import android.content.Context
import android.provider.Settings
import com.google.firebase.messaging.FirebaseMessaging
import dagger.hilt.android.internal.Contexts.getApplication
import kotlinx.coroutines.tasks.await

object DeviceInfoHelper {



    @SuppressLint("HardwareIds")
    fun getDeviceId(context: Context): String {
        return Settings.Secure.getString(
            getApplication(context).contentResolver,
            Settings.Secure.ANDROID_ID
        )
    }

    suspend fun getFcmToken(): String {
        return try {
            FirebaseMessaging.getInstance().token.await()
        } catch (e: Exception) {
            "error ${e.message}"
        }
    }
}