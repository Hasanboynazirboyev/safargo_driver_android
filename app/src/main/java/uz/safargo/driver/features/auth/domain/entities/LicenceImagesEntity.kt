package uz.safargo.driver.features.auth.domain.entities

import java.io.File

data class LicenceImagesEntity(
    val imageUrl: String = "",
    val image: File? = null,
    val imageTye: LicenceImageType = LicenceImageType.FRONT
)
enum class LicenceImageType {
    FRONT, BACK, SELFIE,
}