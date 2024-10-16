package uz.safargo.driver

import android.os.Build
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import uz.safargo.driver.core.local_storage.LocalStorage
import uz.safargo.driver.core.theme.AppTheme
import uz.safargo.driver.features.auth.presentation.register.RegisterScreen
import uz.safargo.driver.navigation.NavigationDispatcher
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigationDispatcher: NavigationDispatcher

    @Inject
    lateinit var localStorage: LocalStorage

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        setContent {
            AppTheme {

                Navigator(RegisterScreen()) { navigation ->
                    LaunchedEffect(key1 = navigation) {
                        navigationDispatcher.dispatcher.onEach {
                            it.invoke(navigation)
                        }.collect()
                    }
                    CurrentScreen()
                }

            }

        }
    }
}
