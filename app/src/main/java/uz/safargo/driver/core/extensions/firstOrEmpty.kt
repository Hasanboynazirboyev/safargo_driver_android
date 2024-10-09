package uz.safargo.driver.core.extensions
fun String.firstOrEmpty(): String {
    return if (this.isEmpty()) ""
    else this.first().toString()
}
