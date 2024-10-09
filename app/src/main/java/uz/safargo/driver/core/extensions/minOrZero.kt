package uz.safargo.driver.core.extensions

fun List<Int>.minOrZero(): Int {
    if (this.isEmpty()) return 0
    return this.min()
}