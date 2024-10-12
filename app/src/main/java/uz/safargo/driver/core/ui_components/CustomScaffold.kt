package uz.safargo.driver.core.ui_components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.launch
import uz.safargo.driver.core.theme.AppTypography
import uz.safargo.driver.di.MyNavigatorEntryPoint
import uz.safargo.driver.navigation.CustomNavigator

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomScaffold(
    appBarTitle: String = "",
    isHaveBackButton: Boolean = false,
    onBackPressed: (() -> Unit?)? = null,
    onFloatingActionButtonPressed: (() -> Unit?)? = null,
    paddingValues: PaddingValues = PaddingValues(),
    isLoading: Boolean = false,
    content: @Composable (CustomNavigator) -> Unit,
) {
    val context = LocalContext.current
    val navigator = EntryPointAccessors.fromApplication(
        context.applicationContext,
        MyNavigatorEntryPoint::class.java
    ).getNavigator()
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = Color.White,
        topBar = {
            if (appBarTitle.isNotEmpty()) {
                TopAppBar(
                    title = {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = appBarTitle,
                                style = AppTypography.font22W600Black,
                                textAlign = TextAlign.Center,
                            )

                            if (isHaveBackButton) {
                                IconButton(
                                    onClick = {

                                        if (onBackPressed != null) {
                                            onBackPressed()
                                        } else {

                                            coroutineScope.launch {
                                                navigator.back()
                                            }
                                        }

                                    },
                                    modifier = Modifier.align(Alignment.CenterStart)
                                ) {
                                    Icon(
                                        Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = null
                                    )
                                }
                            }
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = Color.White,
                    ),
                )
            }
        },

        floatingActionButton = {

            if (onFloatingActionButtonPressed != null) {
                FloatingActionButton(
                    onClick = {
                        onFloatingActionButtonPressed()
                    },
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = null
                    )
                }
            }
        },
        content = {
            Surface(
                modifier = Modifier.imePadding()
                    .pointerInput(Unit) {
                        detectTapGestures(onTap = {
                            focusManager.clearFocus()
                        })
                    }
                    .padding(it)
                    .padding(paddingValues)
                    .background(Color.White),
            ) {
                content(navigator)
                if (isLoading) {
                    MainProgressIndicator()
                }
            }
        }
    )
}