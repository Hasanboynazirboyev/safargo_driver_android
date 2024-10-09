package uz.safargo.driver

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import uz.safargo.driver.core.local_storage.LocalStorage
import uz.safargo.driver.features.home.presentation.screens.HomeScreen
import uz.safargo.driver.features.others.splash.SplashScreen
import uz.safargo.driver.navigation.NavigationDispatcher
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var navigationDispatcher: NavigationDispatcher

    @Inject
    lateinit var localStorage: LocalStorage

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContent {

            Surface {

                Navigator(SplashScreen()) { navigation ->
                    LaunchedEffect(key1 = navigation) {

                    }
                    CurrentScreen()
                }
            }

        }
    }
}
