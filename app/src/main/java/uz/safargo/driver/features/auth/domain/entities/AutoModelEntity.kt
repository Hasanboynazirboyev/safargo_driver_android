package uz.safargo.driver.features.auth.domain.entities

data class AutoModelEntity(
    val id: String = "",
    val name: String = "",
    val brandId: String = "",
    val seats: List<AutoSeatEntity> = emptyList(),
    val type: String = ""
)

data class AutoSeatEntity(
    val id: String = "",
    val column: Int = 0,
    val row: Int = 0,
    val type: String = ""
) {
    // Method to convert to JSON-like map
    fun toJson(): Map<String, Any?> {
        return mapOf(
            "column" to column,
            "row" to row,
            "type" to type.ifEmpty { seatTypeRequest }
        )
    }

    // Function to determine seat type request
    private val seatTypeRequest: String
        get() = when {
            row == 1 && column == 1 -> "DRIVER"
            row == 1 && column == 2 -> "SPACE"
            else -> "EMPTY"
        }
}
