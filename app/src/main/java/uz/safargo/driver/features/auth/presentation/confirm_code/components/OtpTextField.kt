package uz.safargo.driver.features.auth.presentation.confirm_code.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.safargo.driver.core.theme.AppColors

@Composable
fun OtpTextField(
    otpCode: MutableState<String>,
    onOtpChange: (String) -> Unit
) {
    val focusRequesters = List(5) { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        for (i in 0 until 5) {


            OutlinedTextField(
                value = otpCode.value.getOrNull(i)?.toString() ?: "",
                onValueChange = { value ->
                    if (otpCode.value.length < 5) {
                        otpCode.value += value


                        if (otpCode.value.length == 5) {
                            onOtpChange(otpCode.value)
                            focusManager.clearFocus()
                        } else {

                            if (i != 4) {
                                focusRequesters[i + 1].requestFocus()
                            }
                            onOtpChange(otpCode.value)
                        }
                    }

                },

                modifier = Modifier
                    .focusRequester(focusRequesters[i])
                    .size(60.dp)
                    .border(
                        if (i < otpCode.value.length) BorderStroke(
                            2.dp,
                            AppColors.primary,

                        ) else BorderStroke(0.dp, Color.Transparent),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .onKeyEvent { keyEvent ->
                        Log.i("TAG", "indexxxx $i")
                        if (keyEvent.key == Key.Backspace) {
                            val otpCodeValue = otpCode.value
                            val otpCodeValueLength = otpCodeValue.length
                            val otpCodeIndexedValue = otpCodeValue
                                .getOrNull(i)
                                ?.toString() ?: ""
                            if (i != 0 && i != 4) {
                                otpCode.value = otpCodeValue.substring(0, otpCodeValueLength - 1)
                                focusRequesters[i - 1].requestFocus()
                            }
                            if (i == 4) {
                                otpCode.value = otpCodeValue.substring(0, otpCodeValueLength - 1)
                                if (otpCodeIndexedValue.isEmpty()) {
                                    focusRequesters[i - 1].requestFocus()
                                }
                                onOtpChange(otpCodeValue)
                            }
                            return@onKeyEvent true
                        } else {


                            false
                        }

                    },
                textStyle = TextStyle(
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = AppColors.primary,
                    unfocusedBorderColor = if (i < otpCode.value.length) AppColors.primary else AppColors.grey2,
                ),

                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}


