package uz.safargo.driver.core.theme
import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

//val sfProFont = FontFamily(
//    Font(R.font.bold, FontWeight.Bold),
//    Font(R.font.heavy, FontWeight.ExtraBold),
//    Font(R.font.medium, FontWeight.Medium),
//    Font(R.font.regular, FontWeight.Normal),
//    Font(R.font.semibold, FontWeight.SemiBold),
//)

// Set of Material typography styles to start with
val Typography = Typography(

    labelMedium = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W600,
        fontSize = 18.sp,
    ),


    headlineLarge = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W700,
        fontSize = 34.sp,
    ),

    labelSmall = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W500,
        fontSize = 20.sp,
    ),

    bodySmall = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W400,
        fontSize = 20.sp,
    ),


    titleMedium = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W600,
        fontSize = 28.sp,
    ),


    titleLarge = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W600,
        fontSize = 32.sp,
    ),

    titleSmall = TextStyle(
        color = Color.White,
        fontWeight = FontWeight.W400,
        fontSize = 18.sp,
    )

)