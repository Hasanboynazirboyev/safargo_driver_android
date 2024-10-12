package uz.safargo.driver.features.others.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.safargo.driver.core.local_storage.LocalStorage

import uz.safargo.driver.features.home.presentation.screens.HomeScreen
import uz.safargo.driver.features.others.select_language.SelectLanguageScreen
import uz.safargo.driver.navigation.CustomNavigator
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val navigator: CustomNavigator,
    private val localStorage: LocalStorage,
) : ViewModel() {


    fun onEventDispatch(intent: SplashScreenIntent) {
        when (intent) {
            is SplashScreenIntent.Navigate -> navigateToHome()
        }
    }


    private fun navigateToHome() {

        viewModelScope.launch {
            delay(500)
            if (localStorage.hasProfile) {
                navigator.replaceTo(HomeScreen())
            } else {
                navigator.replaceTo(SelectLanguageScreen())
            }
        }

    }


}


sealed class SplashScreenIntent {
    data object Navigate : SplashScreenIntent()

}