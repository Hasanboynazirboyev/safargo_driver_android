package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.AutoBrandEntity

data class AutoBrandsListResponse(
    val autoBrands: List<AutoBrands>?,
    val totalCount: Int?
)

data class AutoBrands(
    val id: String?,
    val name: String?,
    val isDeleted: Boolean?,
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?
)

fun AutoBrandsListResponse.toEntity(): List<AutoBrandEntity> {
    return autoBrands?.map {
        AutoBrandEntity(
            id = it.id ?: "",
            name = it.name ?: "",

            )
    } ?: emptyList()
}
