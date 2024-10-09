package uz.safargo.driver.features.others.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import uz.safargo.driver.R
import uz.safargo.driver.core.utils.AppScreen

class SplashScreen : AppScreen() {
    @Composable
    override fun Content() {
        Box (
            modifier = Modifier.fillMaxSize()
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.align(
                    Alignment.Center
                ).size(170.dp).clip(RoundedCornerShape(50.dp))
            )
        }

    }
}