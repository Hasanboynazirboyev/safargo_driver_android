package uz.safargo.driver.features.auth.domain.entities

data class RegionEntity(
    val id: String = "",
    val nameRu: String = "",
    val nameUz: String = "",
    val nameEn: String = "",
    val parentId: String = "",
    val lat: Number = 0.0,
    val long: Number = 0.0
) {
    fun getName(language: String): String {
        return when (language) {
            "uz" -> nameUz
            "ru" -> nameRu
            "en" -> nameEn
            else -> nameUz
        }
    }
}
