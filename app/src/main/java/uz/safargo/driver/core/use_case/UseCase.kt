package uz.safargo.driver.core.use_case
import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either

abstract class UseCase<Type, Params> {
    abstract fun call(params: Params): Flow<Either<Type>>
}