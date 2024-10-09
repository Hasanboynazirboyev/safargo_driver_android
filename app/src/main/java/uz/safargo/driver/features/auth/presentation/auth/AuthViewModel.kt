package uz.safargo.driver.features.auth.presentation.auth


import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow

import uz.safargo.driver.core.domain.FormZStatus

import uz.safargo.driver.features.home.domain.use_case.GetProductsUseCase
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getWeatherUseCase: GetProductsUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(AuthScreenUiState())


}

data class AuthScreenUiState(
    val status: FormZStatus = FormZStatus.Initial,

    )

sealed class AuthScreenIntent {
    data object SendCode : AuthScreenIntent()

}