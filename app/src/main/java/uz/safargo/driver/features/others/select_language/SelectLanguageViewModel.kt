package uz.safargo.driver.features.others.select_language

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import uz.safargo.driver.core.local_storage.LocalStorage
import uz.safargo.driver.features.auth.presentation.auth.AuthScreen

import uz.safargo.driver.navigation.CustomNavigator
import javax.inject.Inject

@HiltViewModel
class SelectLanguageViewModel @Inject constructor(
    private val navigator: CustomNavigator,
    private val localStorage: LocalStorage,
) : ViewModel() {


    fun onEventDispatch(intent: SelectLanguageIntent) {
        when (intent) {
            is SelectLanguageIntent.Navigate -> navigate(intent.lang)
        }
    }


    private fun navigate(lang: String) {
        localStorage.language = lang
        AppCompatDelegate.setApplicationLocales(
            LocaleListCompat.forLanguageTags(lang)
        )
        viewModelScope.launch {
            navigator.navigateTo(AuthScreen())
        }

    }


}


sealed class SelectLanguageIntent {
    data class Navigate(
        val lang: String
    ) : SelectLanguageIntent()

}