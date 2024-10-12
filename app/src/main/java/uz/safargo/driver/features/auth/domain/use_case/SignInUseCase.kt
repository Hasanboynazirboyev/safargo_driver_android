package uz.safargo.driver.features.auth.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.use_case.MainParams
import uz.safargo.driver.core.use_case.UseCase
import uz.safargo.driver.features.auth.domain.entities.UserEntity
import uz.safargo.driver.features.auth.domain.repository.AuthRepository
import javax.inject.Inject


class SignInUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<UserEntity, MainParams>() {

    override fun call(params: MainParams): Flow<Either<UserEntity>> {
        return repository.signIn(params.request)
    }
}

