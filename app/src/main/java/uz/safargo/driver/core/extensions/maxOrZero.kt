package uz.safargo.driver.core.extensions

fun List<Int>.maxOrZero(): Int {
    if (this.isEmpty()) return 0
    return this.max()
}