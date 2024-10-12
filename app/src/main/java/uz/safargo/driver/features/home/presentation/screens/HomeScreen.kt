package uz.safargo.driver.features.home.presentation.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.hilt.getViewModel
import uz.safargo.driver.core.utils.AppScreen
import uz.safargo.driver.core.domain.FormzStatus
import uz.safargo.driver.core.ui_components.MainProgressIndicator
import uz.safargo.driver.features.home.presentation.viewmodel.HomeScreenUiState
import uz.safargo.driver.features.home.presentation.viewmodel.HomeViewModel


class HomeScreen : AppScreen() {

    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel()
        val uiState = viewModel.uiState.collectAsState().value


        when (uiState.status) {
            FormzStatus.Initial -> {
                // if there is an argument, the initial event can be added here. if not it can be added in init function in viewModel
                // eg:  viewModel.onEventDispatch(uz.safargo.driver.features.home.presentation.viewmodel.HomeScreenIntent.GetWeatherData)
            }

            FormzStatus.Loading -> {
                MainProgressIndicator()
            }

            FormzStatus.Success -> {
                HomeSuccessContent(
                    uiState,
                )
            }

            FormzStatus.Error -> {
                //  ui in the state where an error is returned from the backend
            }

        }
    }


    @SuppressLint("CoroutineCreationDuringComposition")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun HomeSuccessContent(
        uiState: HomeScreenUiState,
    ) {


        MaterialTheme {
            Scaffold(
                content = { padding ->
                    Surface(
                        modifier = Modifier.padding(padding)
                    ) {
                        LazyColumn(
                            verticalArrangement = Arrangement.spacedBy(12.dp),
                        ) {
                            items(uiState.products) { product ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(
                                            vertical = 12.dp
                                        )
                                ) {
                                    Text(text = "${product.id}")
                                    Spacer(modifier = Modifier.width(12.dp))
                                    Text(text = product.title)

                                }
                            }
                        }


                    }

                },
                containerColor = Color.White,

                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = "Weather app ", style = TextStyle(
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.W400,
                                )
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.Blue,
                        ),
                    )
                }
            )

        }
    }
}

