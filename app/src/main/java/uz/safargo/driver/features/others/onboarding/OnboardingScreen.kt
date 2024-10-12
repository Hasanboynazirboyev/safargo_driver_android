package uz.safargo.driver.features.others.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import uz.safargo.driver.R
import uz.safargo.driver.core.theme.AppColors
import uz.safargo.driver.core.theme.AppTypography
import uz.safargo.driver.core.ui_components.CustomButton
import uz.safargo.driver.core.ui_components.CustomHeightSpacer
import uz.safargo.driver.core.ui_components.CustomScaffold
import uz.safargo.driver.core.utils.AppScreen
import uz.safargo.driver.features.auth.presentation.auth.AuthScreen

class OnboardingScreen : AppScreen() {
    @Composable
    override fun Content() {
        val state = rememberPagerState(pageCount = { onboardingItems.size })
        val coroutineScope = rememberCoroutineScope()
        CustomScaffold { navigator ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White),

                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {

                if (state.currentPage != 2) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        TextButton(
                            onClick = {
                                coroutineScope.launch {
                                    state.animateScrollToPage(2)
                                }
                            },
                            content = {
                                Text(
                                    text = "O'tkazib yuborish",
                                    style = AppTypography.font18W500White.copy(
                                        fontSize = 16.sp,
                                        color = AppColors.primary,
                                    )
                                )
                            },
                            modifier = Modifier.align(Alignment.TopEnd)


                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))
                HorizontalPager(
                    state = state,
                    modifier = Modifier


                ) { page ->
                    val pageItem = onboardingItems[page]
                    Column(
                        modifier = Modifier
                            .padding(
                                horizontal = 40.dp
                            ),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            painter = painterResource(id = pageItem.icon),
                            contentDescription = null
                        )
                        CustomHeightSpacer(size = 67)
                        Text(
                            text = pageItem.title, style = AppTypography.font22W600Black.copy(
                                fontSize = 32.sp
                            )
                        )
                        CustomHeightSpacer(size = 30)
                        Text(
                            text = pageItem.description,
                            style = AppTypography.font16W400Black,
                            textAlign = TextAlign.Center
                        )


                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            16.dp
                        )
                ) {
                    if (state.currentPage != onboardingItems.lastIndex) {
                        Spacer(modifier = Modifier.weight(1f))
                        FloatingActionButton(
                            onClick = {
                                coroutineScope.launch {
                                    state.animateScrollToPage(
                                        state.currentPage + 1
                                    )
                                }
                            },
                            elevation = FloatingActionButtonDefaults.elevation(2.dp),
                            containerColor = AppColors.primary
                        ) {
                            Icon(
                                Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = null,
                                tint = Color.White,
                            )
                        }
                    } else {
                        AnimatedVisibility(
                            visible = state.currentPage == onboardingItems.lastIndex,
                            enter = fadeIn(),
                            exit = fadeOut(),
                        ) {
                            CustomButton(title = "Boshlash", onClick = {
                                coroutineScope.launch {
                                    navigator.navigateTo(AuthScreen())
                                }
                            })
                        }


                    }
                }
            }

        }
    }


}


data class OnboardingItem(
    val title: String,
    val description: String,
    val icon: Int
)

private val onboardingItems = listOf(
    OnboardingItem(
        title = "Xush kelibsiz!",
        description = "Request a ride get picked up by a nearby community driver",
        icon = R.drawable.page1
    ),
    OnboardingItem(
        title = "Tez va oson!",
        description = "Request a ride get picked up by a nearby community driver",
        icon = R.drawable.page2
    ),
    OnboardingItem(
        title = "Istalgan joyga",
        description = "Request a ride get picked up by a nearby community driver",
        icon = R.drawable.page3
    ),

    )