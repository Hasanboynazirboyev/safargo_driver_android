package uz.safargo.driver.features.home.domain.use_case
import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.use_case.UseCase
import uz.safargo.driver.features.home.data.models.request.GetProductsRequest
import uz.safargo.driver.features.home.domain.repository.HomeRepository
import uz.safargo.driver.features.home.domain.entities.ProductsEntity
import javax.inject.Inject


class GetProductsUseCase @Inject constructor(
    private val repository: HomeRepository
) : UseCase<List<ProductsEntity>, GetProductsParams>() {

    override  fun  call(params: GetProductsParams): Flow<Either<List<ProductsEntity>>> {
        return repository.getProducts(params.request)
    }
}

data class GetProductsParams(
    val request: GetProductsRequest,
)