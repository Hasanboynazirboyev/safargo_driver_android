package uz.safargo.driver.core.utils

import android.content.Context
import android.content.pm.PackageInfo
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatDelegate

import androidx.compose.ui.text.intl.Locale
import java.io.File


object AppFunctions {

    fun getImageDimensions(file: File): Pair<Int, Int> {
        // Rasmni dekodlash
        val options = BitmapFactory.Options().apply {
            inJustDecodeBounds = true // Faqat o'lchovlarni olish
        }

        BitmapFactory.decodeFile(file.path, options)

        // O'lchovlarni qaytarish
        return Pair(options.outWidth, options.outHeight)
    }


    fun getCurrentLanguage(): String {
        val appLanguage = AppCompatDelegate.getApplicationLocales()[0]?.language
        if (appLanguage != null) return appLanguage
        /// if app language is null, get system language as app language
        val systemLanguage = Locale.current.language
        return if (systemLanguage == "ru" || systemLanguage == "en" || systemLanguage == "uz") {
            systemLanguage
        } else {
            /// if system language not supported in our app, use ru as app language
            "ru"
        }
    }

    @RequiresApi(Build.VERSION_CODES.P)
    fun getAppSignature(context: Context): String? {
        return try {
            // O'z ilovangizning package nomini kiriting
            val packageName = "uz.safargo.driver"
            val packageInfo: PackageInfo = context.packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNING_CERTIFICATES
            )

            // Ilovaning imzosi
            val signatures = packageInfo.signingInfo.apkContentsSigners
            val signatureHash = StringBuilder()

            for (signature in signatures) {
                val hash = signature.toCharsString()
                signatureHash.append(hash)

            }

            signatureHash.toString()
        } catch (e: PackageManager.NameNotFoundException) {

            null
        }
    }

}
