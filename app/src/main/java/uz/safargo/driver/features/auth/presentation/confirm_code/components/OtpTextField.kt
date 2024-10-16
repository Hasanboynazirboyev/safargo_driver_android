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
import androidx.compose.runtime.LaunchedEffect
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
    onOtpChange: (String) -> Unit,
    otpFieldCount: Int = 5,
    isError: Boolean,
) {

    val focusRequesters = List(5) { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val otpMinusOne = otpFieldCount - 1
    LaunchedEffect(Unit) {
        focusRequesters.first().requestFocus()
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {

        for (i in 0 until otpFieldCount) {


            OutlinedTextField(
                value = otpCode.value.getOrNull(i)?.toString() ?: "",

                onValueChange = { value ->
                    if (otpCode.value.length < otpFieldCount) {
                        otpCode.value += value


                        if (otpCode.value.length == otpFieldCount) {
                            onOtpChange(otpCode.value)
                            focusManager.clearFocus()
                        } else {

                            if (i != otpMinusOne) {
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
                        if (i < otpCode.value.length && !isError) BorderStroke(
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
                            if (i != 0 && i != otpMinusOne) {
                                otpCode.value = otpCodeValue.substring(0, otpCodeValueLength - 1)
                                focusRequesters[i - 1].requestFocus()
                            }
                            if (i == otpMinusOne) {
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
                    focusedBorderColor = getBorderColor(true, isError),
                    unfocusedBorderColor = getBorderColor(i < otpCode.value.length, isError),
                ),

                shape = RoundedCornerShape(12.dp)
            )
        }
    }
}

private fun getBorderColor(
    isPrimaryBorder: Boolean,
    isError: Boolean
): Color {

    return if (isError) {
        Color.Red
    } else if (isPrimaryBorder) {
        AppColors.primary
    } else {
        AppColors.grey2
    }


}


