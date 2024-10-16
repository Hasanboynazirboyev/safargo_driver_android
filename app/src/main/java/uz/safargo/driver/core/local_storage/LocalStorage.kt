package uz.safargo.driver.core.local_storage

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import uz.safargo.driver.features.auth.domain.entities.UserEntity
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalStorage @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val gson = Gson()
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

    var user: UserEntity?
        get() {
            val json = pref.getString("user", null)
            return gson.fromJson(json, UserEntity::class.java)
        }
        set(value) {
            val json = gson.toJson(value)
            pref.edit().putString("user", json).apply()
        }


}