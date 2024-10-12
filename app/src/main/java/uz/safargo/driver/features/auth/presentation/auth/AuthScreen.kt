package uz.safargo.driver.features.auth.presentation.auth

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import kotlinx.coroutines.launch
import uz.safargo.driver.R
import uz.safargo.driver.core.domain.isLoading
import uz.safargo.driver.core.theme.AppTypography
import uz.safargo.driver.core.ui_components.CustomButton
import uz.safargo.driver.core.ui_components.CustomHeightSpacer
import uz.safargo.driver.core.ui_components.CustomScaffold
import uz.safargo.driver.core.ui_components.CustomTextField
import uz.safargo.driver.core.utils.AppScreen

class AuthScreen : AppScreen() {
    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    override fun Content() {
        val viewModel = getViewModel<AuthViewModel>()
        val uiState = viewModel.uiState.collectAsState().value
        val coroutineScope = rememberCoroutineScope()
        val localContext = LocalContext.current


        CustomScaffold(
            isHaveBackButton = true,
            appBarTitle = "Kirish",
            paddingValues = PaddingValues(20.dp),
            isLoading = uiState.status.isLoading(),
        ) { navigator ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = null,
                    modifier = Modifier
                        .height(145.dp)
                        .width(91.dp)
                )

                Text(text = "Telefon raqamingizni kiriting", style = AppTypography.font22W600Black)
                CustomHeightSpacer(size = 20)
                Text(
                    text = "Tasdiqlash kodi ko’rsatilgan SMS yuboramiz",
                    style = AppTypography.font14W400Black
                )
                CustomHeightSpacer(size = 24)
                CustomTextField(
                    hintText = "+998 123 45 67",
                    prefixText = "+998",
                    isPhoneNumber = true,
                    keyboardType = KeyboardType.Number,
                    textState = uiState.phoneNumber
                )
                CustomHeightSpacer(size = 20)
                CustomButton(title = "Kodni olish", onClick = {
                    coroutineScope.launch {
                        viewModel.navigate()
                    }
                    /// to do

//                    val signature =
//                        AppFunctions.getAppSignature(localContext.applicationContext) ?: "undefined"
//                    viewModel.onEventDispatch(AuthScreenIntent.CheckPhoneNumber(signature))

                })
            }


        }

    }
}