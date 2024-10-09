package uz.safargo.driver.core.extensions
import java.util.concurrent.TimeUnit

fun Long.formatMinSec(): String {
    return if (this == 0L) {
        "00:00"
    } else if (this < 3600000) {
        String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(this),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(this)
                    )
        )
    } else {
        String.format(
            "%01d:%02d:%02d",
            TimeUnit.MILLISECONDS.toHours(this),
            TimeUnit.MILLISECONDS.toMinutes(this) - TimeUnit.HOURS.toMinutes(
                TimeUnit.MILLISECONDS.toHours(this)
            ),
            TimeUnit.MILLISECONDS.toSeconds(this) -
                    TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(this)
                    )
        )
    }
}