package uz.safargo.driver.core.utils
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.util.Calendar
import android.graphics.Color as graphics

object AppFunctions {


    fun capitalizeFirstLetter(input: String?): String? {
        if (input == null) return null
        if (input.isEmpty()) {
            return input
        }
        val firstLetter = input.substring(0, 1).uppercase()
        val restOfString = input.substring(1).lowercase()
        return "$firstLetter$restOfString"
    }

    fun getCurrentYear(): Int {
        val c = Calendar.getInstance()
        return c.get(Calendar.YEAR)
    }

    fun addNewLines(input: String): String {
        val maxLength = 50
        val stringBuilder = StringBuilder()

        var currentIndex = 0
        while (currentIndex < input.length) {
            val endIndex = minOf(currentIndex + maxLength, input.length)
            val wordBoundaryIndex = input.substring(currentIndex, endIndex).lastIndexOf(' ')

            currentIndex += if (wordBoundaryIndex != -1 && endIndex != input.length) {
                stringBuilder.append(
                    input.substring(
                        currentIndex, currentIndex + wordBoundaryIndex
                    )
                )
                stringBuilder.append("\n")
                wordBoundaryIndex + 1
            } else {
                stringBuilder.append(input.substring(currentIndex, endIndex))
                maxLength
            }
        }
        return stringBuilder.toString()
    }


    fun getCurrentLanguage(): String {
//        val appLanguage = AppCompatDelegate.getApplicationLocales()[0]?.language
//        if (appLanguage != null) return appLanguage
//        /// if app language is null, get system language as app language
//        val systemLanguage = Locale.current.language
//        return if (systemLanguage == "ru" || systemLanguage == "en" || systemLanguage == "uz") {
//            systemLanguage
//        } else {
//            /// if system language not supported in our app, use ru as app language
//            "ru"
//        }
        return ""
    }


    fun getGradientForProfile(name: String): Brush {
        val colors = generateGradientColors(name)

        return Brush.linearGradient(
            colors = colors
        )
    }

    private fun generateGradientColors(name: String): List<Color> {
        val hash = name.hashCode()
        val color1 = Color(hash ushr 16 and 0xFF, hash ushr 8 and 0xFF, hash and 0xFF)
        val color2 =
            Color(hash ushr 16 and 0xFF, hash ushr 8 and 0xFF, (hash ushr 16 and 0xFF) + 100)
        return listOf(color1, color2)
    }

    fun getColorByRating(value: Double): Int {
        if (value < 0 || value > 10) {
            throw IllegalArgumentException("Input value must be between 0 and 10")
        }

        val greenIntensity = (255 * (value / 10)).toInt()
        return graphics.rgb(0, greenIntensity, 0)
    }

    fun convertToHttps(inputString: String): String {
        return inputString.replaceFirst("http://", "https://")
    }

    fun insertSpace(input: String): String {
        if (input.length > 3) {
            val firstPart = input.substring(0, 3)
            val secondPart = input.substring(3)
            return "$firstPart $secondPart"
        }
        return input // Return the input string as is if it's shorter than 5 characters
    }

    fun removeCurlyBraces(input: String): String {
        return input.replace(Regex("[{}]", RegexOption.IGNORE_CASE), "")
    }

    fun joinStringsWithCommas(strings: List<String>): String {
        return strings.joinToString(", ")
    }


//    fun  getScreenWidth() {
//        return Resources.getSystem().getDisplayMetrics().widthPixels;
//    }
//
//    fun getScreenHeight() {
//        return Resources.getSystem().getDisplayMetrics().heightPixels;
//    }
}
