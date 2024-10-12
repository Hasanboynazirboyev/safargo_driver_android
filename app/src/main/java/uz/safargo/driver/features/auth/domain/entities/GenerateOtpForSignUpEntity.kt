package uz.safargo.driver.features.auth.domain.entities

data class GenerateOtpForSignUpEntity(
    val success: Boolean = false,
    val token: String = "",
    val createdAt: String = "",
    val message: String = "",
    val code: Int = 0
)
