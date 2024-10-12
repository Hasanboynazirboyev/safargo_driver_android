package uz.safargo.driver.features.auth.domain.entities

data class UserEntity(
    val phoneNumber: String = "",
    val fullName: String = "",
    val userId: String = "",
    val accessToken: String = "",
    val image: String = "",
    val birthDate: String = "",
    val regionId: String = "",
    val licence: List<LicenceImagesEntity> = emptyList(),
    val status: String = "",
    val balance: Number = 0.0,
    val iat: Number = 0
) {
    val isDriverActive: Boolean
        get() = status == "ACTIVE"
}
