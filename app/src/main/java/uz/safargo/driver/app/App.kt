package uz.safargo.driver.app

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    private lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        instance =this
        container = AppContainer(this)
    }
    companion object {
        lateinit var instance: App
    }
}

