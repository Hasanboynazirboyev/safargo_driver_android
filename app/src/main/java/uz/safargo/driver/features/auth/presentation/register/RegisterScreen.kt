package uz.safargo.driver.features.auth.presentation.register

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.hilt.getViewModel
import uz.safargo.driver.core.domain.isLoading
import uz.safargo.driver.core.ui_components.CustomButton
import uz.safargo.driver.core.ui_components.CustomHeightSpacer
import uz.safargo.driver.core.ui_components.CustomScaffold
import uz.safargo.driver.core.ui_components.CustomTextField
import uz.safargo.driver.core.ui_components.drop_down.CustomDropDownMenu
import uz.safargo.driver.core.ui_components.drop_down.DropDownItemData
import uz.safargo.driver.core.utils.AppScreen

class RegisterScreen : AppScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.P)
    @Composable
    override fun Content() {
        val viewModel = getViewModel<RegisterViewModel>()
        val uiState = viewModel.uiState.collectAsState().value


        val sheetState = rememberModalBottomSheetState(

        )
        val isSheetOpen = remember { mutableStateOf(false) }



        CustomScaffold(
            isHaveBackButton = true,
            appBarTitle = "Ro’yxatdan o’tish",
            paddingValues = PaddingValues(20.dp),
            isLoading = uiState.status.isLoading(),

            ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                CustomTextField(
                    hintText = "Afzal Pulatov",
                    keyboardType = KeyboardType.Number,
                    textState = uiState.name
                )
                CustomHeightSpacer(size = 20)
                CustomTextField(
                    hintText = "Afzal Pulatov",
                    keyboardType = KeyboardType.Number,
                    textState = uiState.name
                )
                CustomHeightSpacer(size = 20)

                CustomDropDownMenu(
                    items = movieFilter,
                    onValueChange = { items ->

                    },
                    title = "Erkak",


                    isSingleSelection = true,


                    )
                CustomHeightSpacer(size = 20)



                CustomButton(title = "Kodni olish", onClick = {
                    isSheetOpen.value = true


                })


                if (isSheetOpen.value) {
                    ModalBottomSheet(
                        sheetState = sheetState,
                        onDismissRequest = {
                            isSheetOpen.value = false
                        },


                        ) {
                        Column {
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .background(Color.Red)
                            )
                            Box(
                                modifier = Modifier
                                    .height(100.dp)
                                    .fillMaxWidth()
                                    .background(Color.Yellow)
                            )
                        }


                    }
                }
            }


        }

    }
}

val movieFilter = listOf(
    DropDownItemData(
        name = "Сначала новые",
        id = "new",
        index = 0,
    ),
    DropDownItemData(
        name = "Сначала старые ",
        id = "old",
        index = 1,
    ),
    DropDownItemData(
        name = "По полярности",
        id = "popular",
        index = 2,
    ),
    DropDownItemData(
        name = "По рейтингу",
        id = "rating",
        index = 3,
    )

)