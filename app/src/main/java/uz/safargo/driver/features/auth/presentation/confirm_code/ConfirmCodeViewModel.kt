package uz.safargo.driver.features.auth.presentation.confirm_code


import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

import uz.safargo.driver.core.domain.FormzStatus
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.helpers.DeviceInfoHelper
import uz.safargo.driver.core.local_storage.LocalStorage
import uz.safargo.driver.core.models.MainRequestModel
import uz.safargo.driver.core.use_case.MainParams
import uz.safargo.driver.features.auth.domain.use_case.GenerateOtpForSignUpUseCase
import uz.safargo.driver.features.auth.domain.use_case.SignInUseCase
import uz.safargo.driver.features.home.presentation.screens.HomeScreen
import uz.safargo.driver.navigation.CustomNavigator

import javax.inject.Inject


@HiltViewModel
class ConfirmCodeViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val localStorage: LocalStorage,
    private val navigator: CustomNavigator,
    private val generateOtpForSignUpUseCase: GenerateOtpForSignUpUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(ConfirmCodeUiState())

    @RequiresApi(Build.VERSION_CODES.P)
    fun onEventDispatch(intent: ConfirmCodeScreenIntent) {
        when (intent) {
            is ConfirmCodeScreenIntent.ConfirmCode -> confirmCode(
                intent.context,
                intent.code,
                intent.signature,
                intent.phoneNumber,
            )
        }
    }


    private fun confirmCode(
        context: Context,
        code: Int = 0,
        signature: String = "",
        phoneNumber: String = ""
    ) {
        uiState.update {
            it.copy(
                status = FormzStatus.Loading,
            )
        }
        var fcmToken = ""

        val deviceId = DeviceInfoHelper.getDeviceId(context)
        viewModelScope.launch {
            fcmToken = DeviceInfoHelper.getFcmToken()

        }


        signInUseCase.call(
            MainParams(
                request = MainRequestModel(
                    body = mapOf(
                        "phone" to phoneNumber,
                        "code" to code,
                        "signature" to signature,
                        "deviceOS" to "android",
                        "deviceId" to deviceId,
                        "fcmToken" to fcmToken,
                        "language" to localStorage.language!!.uppercase()
                    )
                )
            )
        )
            .onEach { result ->
                when (result) {
                    is Either.Right -> {
                        navigator.navigateTo(HomeScreen())
                    }

                    is Either.Left -> {

                    }

                }
            }.launchIn(viewModelScope)
    }


}

data class ConfirmCodeUiState(
    val status: FormzStatus = FormzStatus.Initial,


    )

sealed class ConfirmCodeScreenIntent {
    data class ConfirmCode(
        val context: Context,
        val code: Int,
        val phoneNumber: String,
        val signature: String
    ) : ConfirmCodeScreenIntent()

}