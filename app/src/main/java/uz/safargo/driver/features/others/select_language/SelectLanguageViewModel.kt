package uz.safargo.driver.features.others.select_language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.safargo.driver.core.local_storage.LocalStorage
import uz.safargo.driver.features.others.onboarding.OnboardingScreen

import uz.safargo.driver.navigation.CustomNavigator
import javax.inject.Inject

@HiltViewModel
class SelectLanguageViewModel @Inject constructor(
    private val navigator: CustomNavigator,
    private val localStorage: LocalStorage,
) : ViewModel() {


    fun onEventDispatch(intent: SelectLanguageScreenIntent) {
        when (intent) {
            is SelectLanguageScreenIntent.Navigate -> navigate(intent.lang)
        }
    }


    private fun navigate(lang: String) {

        viewModelScope.launch {
            navigator.popUntilRoot()
            navigator.replaceTo(OnboardingScreen())
            localStorage.language = lang
//            AppCompatDelegate.setApplicationLocales(
//                LocaleListCompat.forLanguageTags(lang)
//            )

        }

    }


}


sealed class SelectLanguageScreenIntent {
    data class Navigate(
        val lang: String
    ) : SelectLanguageScreenIntent()

}