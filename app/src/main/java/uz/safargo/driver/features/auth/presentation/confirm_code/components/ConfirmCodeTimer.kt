package uz.safargo.driver.features.auth.presentation.confirm_code.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import kotlinx.coroutines.delay
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.theme.AppTypography

@Composable
fun ConfirmCodeTimer(
    onSendAgainClick: () -> Unit = {},
) {
    val timeLeft = remember { mutableIntStateOf(5) }
    val isRunning = remember { mutableStateOf(false) }


    LaunchedEffect(Unit) {
        runTimer(timeLeft, mutableStateOf(true))

    }
    LaunchedEffect(isRunning.value) {
        if (isRunning.value) {
            runTimer(
                timeLeft,
                isRunning,
            )

        }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (timeLeft.intValue > 0) {
            Text(
                text = "Agar kod kelmasa, ${timeLeft.intValue} soniyada yangisini olishingiz mumkin",
                style = AppTypography.font16W400Black.copy(
                    color = AppColors.grey2,
                ),
                textAlign = TextAlign.Center,
            )
        } else {
            TextButton(onClick = {

                timeLeft.intValue = 5
                isRunning.value = true
                onSendAgainClick()
            }) {
                Text(
                    text = "Kodni qayta yuborish",
                    style = AppTypography.font16W400Black.copy(
                        color = AppColors.primary,
                    )
                )
            }
        }
    }
}

private suspend fun runTimer(timeLeft: MutableIntState, isRunning: MutableState<Boolean>) {
    if (isRunning.value) {
        while (timeLeft.intValue > 0) {
            delay(1000L)
            timeLeft.intValue--
        }
        isRunning.value = false
    }
}
