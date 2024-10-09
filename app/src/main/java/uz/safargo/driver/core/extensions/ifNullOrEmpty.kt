package uz.safargo.driver.core.extensions

fun String?.ifNullOrEmpty(alternative: String?): String? {
    return if ((this ?: "").isEmpty()) {
        alternative
    } else {
        this
    }
}

fun String?.ifNullOrEmpty(alternative: () -> String?): String? {
    return if ((this ?: "").isEmpty()) {
        alternative.invoke()
    } else {
        this
    }
}
