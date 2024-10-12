package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.UserEntity

data class UserResponse(
    val token: String?,
    val driver: Driver?,
    val car: Car?
)

data class Driver(
    val fullName: String?,
    val phone: String?,
    val picture: String?,
    val birthday: String?,
    val regions: List<String>?,
    val licence: Licence?,
    val gender: String?,
    val status: String?,
    val isDeleted: Boolean?,
    val id: String?,
    val createdAt: String?,
    val updatedAt: String?,
    val balance: Double?,
    val iat: Double?,
    val v: Int?
)

data class Car(
    val driver: String?,
    val autoBrand: String?,
    val autoModel: String?,
    val type: String?,
    val fuelType: String?,
    val plateNumber: String?,
    val year: Int?,
    val seats: List<UserSeats>?,
    val status: String?,
    val isDeleted: Boolean?,
    val id: String?,
    val createdAt: String?,
    val updatedAt: String?
)

data class UserSeats(
    val column: Int?,
    val row: Int?,
    val type: String?
)

data class Licence(
    val frontImage: String?,
    val backImage: String?,
    val selfieImage: String?,
    val id: String?
)


fun UserResponse.toEntity(): UserEntity {
    return UserEntity(
        accessToken = token ?: "",
        fullName = driver?.fullName ?: "",
        userId = driver?.id ?: "",
        phoneNumber = driver?.phone ?: "",
        image = driver?.picture ?: "",
        birthDate = driver?.birthday ?: "",
        regionId = driver?.regions?.firstOrNull() ?: "",
        status = driver?.status ?: "",
        balance = driver?.balance ?: 0,
        iat = driver?.iat ?: 0
    )
}
