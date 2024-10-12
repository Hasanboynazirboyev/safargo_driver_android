package uz.safargo.driver.features.auth.presentation.confirm_code

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import uz.safargo.driver.core.domain.isLoading
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.theme.AppTypography
import uz.safargo.driver.core.ui_components.CustomHeightSpacer
import uz.safargo.driver.core.ui_components.CustomScaffold
import uz.safargo.driver.core.utils.AppScreen
import uz.safargo.driver.features.auth.presentation.confirm_code.components.OtpTextField

class ConfirmCodeScreen(
    val phoneNumber: String = "",
    val signature: String = "",
) : AppScreen() {
    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    override fun Content() {
        val viewModel = getViewModel<AuthViewModel>()
        val uiState = viewModel.uiState.collectAsState().value
        val otpCode = remember { mutableStateOf("") }





        CustomScaffold(
            isHaveBackButton = true,
            appBarTitle = "Kirish",
            paddingValues = PaddingValues(20.dp),
            isLoading = uiState.status.isLoading(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .imePadding(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(text = "Kodni kiriting", style = AppTypography.font22W600Black)
                CustomHeightSpacer(size = 20)
                Text(
                    text = "Telefon raqamingizga 6-raqamli kod yuborildi",
                    style = AppTypography.font14W400Black
                )
                CustomHeightSpacer(size = 24)
                OtpTextField(
                    onOtpChange = { _ -> },
                    otpCode = otpCode
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Agar kod kelmasa, 60 soniyada yangisini olishingiz mumkin",
                    style = AppTypography.font16W400Black.copy(
                        color = AppColors.grey2,
                    ),
                    modifier = Modifier

                )


            }


        }

    }
}