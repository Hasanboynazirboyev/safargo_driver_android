package uz.safargo.driver.features.others.splash

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import uz.safargo.driver.core.domain.FormZStatus
import uz.safargo.driver.features.home.domain.use_case.GetProductsUseCase
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val getWeatherUseCase: GetProductsUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(SplashScreenUiState())


}

data class SplashScreenUiState(
    val status: FormZStatus = FormZStatus.Initial,

    )

sealed class SplashScreenIntent {
    data object SendCode : SplashScreenIntent()

}