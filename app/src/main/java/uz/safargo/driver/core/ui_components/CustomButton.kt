package uz.safargo.driver.core.ui_components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.theme.AppTypography

@Composable
fun CustomButton(onClick: () -> Unit = {}, title: String = "", isEnabled: Boolean = true) {
    Button(
        onClick = onClick,
        content = {
            Text(
                text = title, style = AppTypography.font18W500White.copy(
                    fontSize = 16.sp,
                )
            )
        },
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.primary,

            disabledContainerColor = AppColors.primary.copy(
                alpha = 0.5f
            ),
            disabledContentColor = AppColors.grey
        ),
        enabled = isEnabled
    )

}