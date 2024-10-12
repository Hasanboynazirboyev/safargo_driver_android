package uz.safargo.driver.core.local_storage

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val pref: SharedPreferences =
        context.getSharedPreferences("LocaleStorage", Context.MODE_PRIVATE)

    fun clearData() {
        pref.edit().clear().apply()
    }

    var language: String?
        get() {
            return pref.getString("language", null)
        }
        set(value) {
            pref.edit().putString("language", value).apply()
        }

    var hasProfile: Boolean
        get() {
            return pref.getBoolean("hasProfile", false)
        }
        set(value) {
            pref.edit().putBoolean("hasProfile", value).apply()
        }

    var accessToken: String?
        get() {
            return pref.getString("accessToken", null)
        }
        set(value) {
            pref.edit().putString("accessToken", value).apply()
        }


}