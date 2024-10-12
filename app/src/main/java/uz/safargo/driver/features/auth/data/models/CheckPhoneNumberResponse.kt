package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.CheckPhoneNumberEntity

data class CheckPhoneNumberResponse(
    val success: Boolean? = null
)

fun CheckPhoneNumberResponse.toEntity(): CheckPhoneNumberEntity {
    return CheckPhoneNumberEntity(
        success = success ?: false,
    )
}