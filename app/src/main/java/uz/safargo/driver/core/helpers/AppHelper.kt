package uz.safargo.driver.core.helpers

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.ui.text.intl.Locale
import java.io.File

object AppHelper{
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
}