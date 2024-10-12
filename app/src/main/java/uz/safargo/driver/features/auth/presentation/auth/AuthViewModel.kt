package uz.safargo.driver.features.auth.presentation.auth


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

import uz.safargo.driver.core.domain.FormzStatus
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.models.MainRequestModel
import uz.safargo.driver.core.use_case.MainParams
import uz.safargo.driver.features.auth.domain.use_case.CheckPhoneNumberUseCase
import uz.safargo.driver.features.auth.domain.use_case.GenerateOtpForSignUpUseCase
import uz.safargo.driver.features.auth.domain.use_case.SignInUseCase
import uz.safargo.driver.features.auth.presentation.confirm_code.ConfirmCodeScreen
import uz.safargo.driver.navigation.CustomNavigator

import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(
    private val checkPhoneNumberUseCase: CheckPhoneNumberUseCase,
    private val signInUseCase: SignInUseCase,
    private val signUpUseCase: GenerateOtpForSignUpUseCase,
    private val navigator: CustomNavigator
) : ViewModel() {
    val uiState = MutableStateFlow(AuthScreenUiState())
    fun onEventDispatch(intent: AuthScreenIntent) {
        when (intent) {
            is AuthScreenIntent.CheckPhoneNumber -> checkPhoneNumber(intent.signature)
        }
    }

    private fun checkPhoneNumber(signature: String) {
        uiState.update {
            it.copy(
                status = FormzStatus.Loading,
                signature = signature,
            )
        }


        checkPhoneNumberUseCase.call(
            MainParams(
                request = MainRequestModel(
                    body = mapOf(
                        "phone" to uiState.value.phoneNumber.value
                    )
                )
            )
        )
            .onEach { result ->
                when (result) {
                    is Either.Right -> {
                        if (result.data.success) {
                            generateOtpForSignIn()
                        } else {
                            generateOtpForSignUp()
                        }
                    }

                    is Either.Left -> {

                    }

                }
            }.launchIn(viewModelScope)
    }


    private fun generateOtpForSignIn() {
        signInUseCase.call(
            MainParams(
                request = MainRequestModel(
                    body = mapOf(
                        "phone" to uiState.value.phoneNumber.value,
                        "code" to 0,
                        "signature" to uiState.value.signature
                    )
                )
            )
        ).onEach { result ->
            when (result) {
                is Either.Right -> {
                    navigate()
                }

                is Either.Left -> {

                }

            }
        }.launchIn(viewModelScope)
    }

    private fun generateOtpForSignUp() {
        signUpUseCase.call(
            MainParams(
                request = MainRequestModel(
                    body = mapOf(
                        "phone" to uiState.value.phoneNumber.value,
                        "code" to 0,
                        "type" to "DRIVER",
                        "signature" to uiState.value.signature
                    )
                )
            )
        ).onEach { result ->
            when (result) {
                is Either.Right -> {
                    navigate()
                }

                is Either.Left -> {

                }

            }
        }.launchIn(viewModelScope)
    }

     suspend fun navigate() {
        navigator.navigateTo(
            ConfirmCodeScreen(
                signature = uiState.value.signature,
                phoneNumber = uiState.value.phoneNumber.value,
            )
        )
    }

}

data class AuthScreenUiState(
    val status: FormzStatus = FormzStatus.Initial,
    val phoneNumber: MutableState<String> = mutableStateOf(""),
    val signature: String = "",

    )

sealed class AuthScreenIntent {
    data class CheckPhoneNumber(val signature: String) : AuthScreenIntent()

}