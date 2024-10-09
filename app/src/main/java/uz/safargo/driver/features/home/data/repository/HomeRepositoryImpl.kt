package uz.safargo.driver.features.home.data.repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.models.Message
import uz.safargo.driver.features.home.data.models.request.GetProductsRequest
import uz.safargo.driver.features.home.data.models.response.toEntity
import uz.safargo.driver.features.home.data.remote.HomeApi
import uz.safargo.driver.features.home.domain.entities.ProductsEntity
import uz.safargo.driver.features.home.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi,
) : HomeRepository {
    override fun getProducts(
        request: GetProductsRequest
    ): Flow<Either<List<ProductsEntity>>> {
        return flow {
            val response = api.getProducts()
            if (response.isSuccessful) {
                emit(Either.Right(response.body()!!.toEntity()))
            } else {
                val error = response.errorBody()?.string() ?: ""
                emit(Either.Left(Message(error)))
            }
        }.catch {
            emit(Either.Left(Message(it.message!!)))
        }.flowOn(Dispatchers.IO)
    }

}
