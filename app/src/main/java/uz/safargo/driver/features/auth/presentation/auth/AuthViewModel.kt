package uz.safargo.driver.features.auth.presentation.auth


import android.content.Context
import android.util.Log
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

import uz.safargo.driver.core.domain.FormzStatus
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.helpers.sms_retriever.AppSignatureHelper
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
            is AuthScreenIntent.CheckPhoneNumber -> checkPhoneNumber(intent.context)
        }
    }

    private fun checkPhoneNumber(context: Context) {
        val signature = AppSignatureHelper(context).getAppSignature()
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
                        "phone" to uiState.value.phoneNumber()
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
                        Log.i("TAG", "checkPhoneNumber: ${result.errorMessage.message}")

                    }

                }
            }.launchIn(viewModelScope)
    }


    private suspend fun generateOtpForSignIn() {
        signInUseCase.call(
            MainParams(
                request = MainRequestModel(
                    body = mapOf(
                        "phone" to uiState.value.phoneNumber(),
                        "code" to 0,
                        "signature" to uiState.value.signature
                    )
                )
            )
        ).collect { result ->
            when (result) {
                is Either.Right -> {
                    navigate()
                    Log.i("TAG", "generateOtpForSignIn: ${result.data}")
                }

                is Either.Left -> {
                    Log.i("TAG", "generateOtpForSignIn: ${result.errorMessage.message}")

                }

            }
        }
    }

    private suspend fun generateOtpForSignUp() {
        signUpUseCase.call(
            MainParams(
                request = MainRequestModel(
                    body = mapOf(
                        "phone" to uiState.value.phoneNumber(),
                        "code" to 0,
                        "type" to "DRIVER",
                        "signature" to uiState.value.signature
                    )
                )
            )
        ).collect { result ->
            when (result) {
                is Either.Right -> {
                    navigate()
                }

                is Either.Left -> {

                }

            }
        }
    }

    private suspend fun navigate() {
        navigator.navigateTo(
            ConfirmCodeScreen(
                signature = uiState.value.signature,
                phoneNumber = uiState.value.phoneNumber(),
            )
        )
    }

}

data class AuthScreenUiState(
    val status: FormzStatus = FormzStatus.Initial,
    val phoneNumber: TextFieldValue = TextFieldValue(),
    val signature: String = "",


    ) {
    fun phoneNumber(): String {
        return "+998${phoneNumber.text}"
    }
}

sealed class AuthScreenIntent {
    data class CheckPhoneNumber(val context: Context) : AuthScreenIntent()

}