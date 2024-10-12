package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.RegionEntity

data class RegionsListResponse(
    val regions: List<Regions>? = null,
    val totalCount: Int? = null
) {

}

data class Regions(
    val id: String? = null,
    val name: String? = null,
    val location: Location? = null,
    val ancestors: List<Ancestors>? = null
) {

}

data class Location(
    val lat: Number? = null,
    val long: Number? = null
)

data class Ancestors(
    val id: String? = null
)

fun RegionsListResponse.toEntity(): List<RegionEntity> {
    return regions?.map { region ->
        RegionEntity(
            id = region.id ?: "",
            nameUz = region.name ?: "",
            nameRu = region.name ?: "",
            nameEn = region.name ?: "",
            lat = region.location?.lat ?: 0,
            long = region.location?.long ?: 0
        )
    } ?: emptyList()
}
