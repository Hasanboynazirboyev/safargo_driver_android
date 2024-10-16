package uz.safargo.driver.core.ui_components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import uz.safargo.driver.core.text_transformations.PhoneVisualTransformation
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.theme.AppTypography

@Composable
fun CustomTextField(
    textState: TextFieldValue = remember {
        TextFieldValue()
    },
    hintText: String = "",
    prefixText: String = "+998 ",
    isPhoneNumber: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    onChanged: (String) -> Unit = {}
) {
    val hasFocus = remember { mutableStateOf(false) }

    OutlinedTextField(
        value = textState,
        onValueChange = { changedText ->
            if (isPhoneNumber && changedText.text.length > 9) {
                return@OutlinedTextField
            }

            onChanged(textState.text)

        },


        placeholder = {
            if (!hasFocus.value) {
                Text(
                    text = hintText,
                    style = AppTypography.font16W400Black.copy(color = AppColors.grey2)
                )
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .onFocusChanged {
                hasFocus.value = it.isFocused
            },
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = Color.White,
            unfocusedContainerColor = AppColors.backgroundGrey,
            focusedBorderColor = AppColors.primary,
            unfocusedBorderColor = AppColors.grey2,
        ),
        shape = RoundedCornerShape(16.dp),
        prefix = {
            if (textState.text.isNotEmpty()) {
                if (hasFocus.value || textState.text.isNotEmpty()) {
                    Text(
                        text = prefixText,
                        style = AppTypography.font16W400Black
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
        ),
        visualTransformation = if (isPhoneNumber) {
            PhoneVisualTransformation("## ### ## ##", '#')
        } else {
            VisualTransformation.None
        },
        textStyle = AppTypography.font16W400Black,
    )
}

