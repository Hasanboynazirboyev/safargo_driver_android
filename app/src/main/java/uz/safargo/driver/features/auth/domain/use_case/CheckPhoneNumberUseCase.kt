package uz.safargo.driver.features.auth.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.use_case.MainParams
import uz.safargo.driver.core.use_case.UseCase
import uz.safargo.driver.features.auth.domain.entities.CheckPhoneNumberEntity
import uz.safargo.driver.features.auth.domain.repository.AuthRepository
import javax.inject.Inject


class CheckPhoneNumberUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<CheckPhoneNumberEntity, MainParams>() {

    override fun call(params: MainParams): Flow<Either<CheckPhoneNumberEntity>> {
        return repository.checkPhone(params.request)
    }
}

