package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.FacilityEntity

data class FacilitiesListResponse(
    val facilities: List<Facilities>?,
    val totalCount: Int?
)

data class Facilities(
    val id: String?,
    val name: String?,
    val isDeleted: Boolean?,
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?
)

fun FacilitiesListResponse.toEntity(): List<FacilityEntity> {
    return facilities?.map {
        FacilityEntity(
            id = it.id ?: "",
            name = it.name ?: "",
            slug = it.slug ?: ""
        )
    } ?: emptyList()
}

