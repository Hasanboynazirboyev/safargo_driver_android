package uz.safargo.driver.features.home.domain.repository
import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.features.home.data.models.request.GetProductsRequest
import uz.safargo.driver.features.home.domain.entities.ProductsEntity

interface HomeRepository {
    fun getProducts(request: GetProductsRequest): Flow<Either<List<ProductsEntity>>>
}