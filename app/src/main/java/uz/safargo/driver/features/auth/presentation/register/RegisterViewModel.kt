package uz.safargo.driver.features.auth.presentation.register


import android.content.Context
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

import uz.safargo.driver.core.domain.FormzStatus
import uz.safargo.driver.navigation.CustomNavigator

import javax.inject.Inject


@HiltViewModel
class RegisterViewModel @Inject constructor(

    private val navigator: CustomNavigator
) : ViewModel() {
    val uiState = MutableStateFlow(AuthScreenUiState())
    fun onEventDispatch(intent: AuthScreenIntent) {
        when (intent) {
            is AuthScreenIntent.RegisterUser -> registerUser()
        }
    }

    private fun registerUser() {

        uiState.update {
            it.copy(
                status = FormzStatus.Loading,

                )
        }


//        checkPhoneNumberUseCase.call(
//            MainParams(
//                request = MainRequestModel(
//                    body = mapOf(
//                        "phone" to uiState.value.phoneNumber()
//                    )
//                )
//            )
//        )
//            .onEach { result ->
//                when (result) {
//                    is Either.Right -> {
//
//                        if (result.data.success) {
//                            generateOtpForSignIn()
//                        } else {
//                            generateOtpForSignUp()
//                        }
//                    }
//
//                    is Either.Left -> {
//                        Log.i("TAG", "checkPhoneNumber: ${result.errorMessage.message}")
//
//                    }
//
//                }
//            }.launchIn(viewModelScope)
    }


}

data class AuthScreenUiState(
    val status: FormzStatus = FormzStatus.Initial,
    val name: TextFieldValue = TextFieldValue(),
    val dateOfBirth: TextFieldValue = TextFieldValue(),
    val phoneNumber: String = "",

    ) {

}

sealed class AuthScreenIntent {
    data class RegisterUser(val context: Context) : AuthScreenIntent()

}