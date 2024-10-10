package uz.safargo.driver.core.ui_components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import uz.safargo.driver.core.theme.AppTypography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    appBarTitle: String = "",
    isHaveBackButton: Boolean = false,
    onBackPressed: () -> Unit = {},
    paddingValues: PaddingValues = PaddingValues(),
    content: @Composable () -> Unit,

    ) {

    Scaffold(
        topBar = {
            if (appBarTitle.isNotEmpty()) {
                TopAppBar(
                    title = {
                        Text(
                            text = appBarTitle, style = AppTypography.font22W600Black,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center,

                            )
                    },

                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
                    navigationIcon = {
                        if (isHaveBackButton) {
                            IconButton(onClick = onBackPressed) {
                                Icon(
                                    Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Back"
                                )
                            }
                        }
                    },
                )
            }
        },
        containerColor = Color.White,
        content = {
            Surface(
                modifier = Modifier
                    .padding(it)
                    .padding(paddingValues),
            ) {
                content()

            }
        }
    )
}