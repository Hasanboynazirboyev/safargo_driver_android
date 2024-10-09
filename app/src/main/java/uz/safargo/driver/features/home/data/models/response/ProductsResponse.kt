package uz.safargo.driver.features.home.data.models.response

import uz.safargo.driver.features.home.domain.entities.ProductsEntity

data class ProductsResponse(
    val limit: Int,
    val products: List<Product>? = null,
    val skip: Int,
    val total: Int,


    )


fun ProductsResponse.toEntity(): List<ProductsEntity> {
    return products?.map { it.toEntity() } ?: emptyList()
}