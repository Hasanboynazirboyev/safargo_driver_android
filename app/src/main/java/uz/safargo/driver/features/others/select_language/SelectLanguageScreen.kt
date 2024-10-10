package uz.safargo.driver.features.others.select_language


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import uz.safargo.driver.R
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.theme.AppTypography
import uz.safargo.driver.core.ui_components.CustomHeightSpacer

import uz.safargo.driver.core.ui_components.CustomScaffold
import uz.safargo.driver.core.utils.AppScreen

class SelectLanguageScreen : AppScreen() {
    @Composable
    override fun Content() {
        val viewModel = getViewModel<SelectLanguageViewModel>()
        val onEventDispatch = viewModel::onEventDispatch
        CustomScaffold(
            appBarTitle = "Tilni tanlash",

            ) {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxSize()
                    .padding(16.dp),

                horizontalAlignment = Alignment.CenterHorizontally

                ) {
                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = null,
                    modifier = Modifier
                        .width(180.dp)
                        .height(130.dp)
                )

                CustomHeightSpacer(size = 50)

                SelectLanguageItem("O'zbekcha", onClick = {
                    onEventDispatch(SelectLanguageIntent.Navigate("uz"))
                })
                    CustomHeightSpacer(size = 20)
                SelectLanguageItem("Русский", onClick = {
                    onEventDispatch(SelectLanguageIntent.Navigate("ru"))
                })
                    CustomHeightSpacer(size = 20)
                SelectLanguageItem(
                    "English",
                    onClick = {
                        onEventDispatch(SelectLanguageIntent.Navigate("en"))
                    },
                )
                CustomHeightSpacer(size = 0)
            }

        }
    }


}

@Composable
fun SelectLanguageItem(title: String = "", onClick: () -> Unit = {}) {
    Text(
        text = title,
        style = AppTypography.font22W600Black.copy(
            fontSize = 20.sp,
        ),
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clip(RoundedCornerShape(100.dp))
            .background(AppColors.backgroundGrey)
            .clickable {
                onClick()
            }
            .padding(
                vertical = 16.dp,
            )
            .fillMaxWidth()


    )
}