package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.GenerateOtpForSignUpEntity

data class GenerateOtpForSignUpResponse(
    val success: Boolean?,
    val data: Data?,
    val message: String?,
    val status: String?
)

data class Data(
    val token: String?,
    val createdAt: String?,
    val code: Int?
)

fun GenerateOtpForSignUpResponse.toEntity(): GenerateOtpForSignUpEntity {
    return GenerateOtpForSignUpEntity(
        success = success ?: false,
        token = data?.token ?: "",
        createdAt = data?.createdAt ?: "",
        message = message ?: "",
        code = data?.code ?: 0
    )
}