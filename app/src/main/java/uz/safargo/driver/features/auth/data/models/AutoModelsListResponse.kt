package uz.safargo.driver.features.auth.data.models

import uz.safargo.driver.features.auth.domain.entities.AutoModelEntity
import uz.safargo.driver.features.auth.domain.entities.AutoSeatEntity

data class AutoModelsListResponse(
    val autoModels: List<AutoModels>?,
    val totalCount: Int?
)

data class AutoModels(
    val id: String?,
    val name: String?,
    val brandId: String?,
    val type: String?,
    val tariffs: List<String>?,
    val seats: List<Seats>?,
    val icon: String?,
    val isDeleted: Boolean?,
    val createdAt: String?,
    val updatedAt: String?,
    val slug: String?
)

data class Seats(
    val column: Int?,
    val row: Int?,
    val id: String?,
    val type: String?
)

fun AutoModelsListResponse.toEntity(): List<AutoModelEntity> {
    return autoModels?.map { model ->
        AutoModelEntity(
            id = model.id ?: "",
            name = model.name ?: "",
            brandId = model.brandId ?: "",
            type = model.type ?: "",
            seats = model.seats?.map { seat ->
                AutoSeatEntity(
                    column = seat.column ?: 0,
                    row = seat.row ?: 0,
                    type = seat.type ?: ""
                )
            } ?: emptyList()
        )
    } ?: emptyList()

}
