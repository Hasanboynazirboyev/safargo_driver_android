package uz.safargo.driver.features.auth.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.use_case.MainParams
import uz.safargo.driver.core.use_case.UseCase
import uz.safargo.driver.features.auth.domain.entities.AutoBrandEntity
import uz.safargo.driver.features.auth.domain.repository.AuthRepository
import javax.inject.Inject


class GetAutoBrandsUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<List<AutoBrandEntity>, MainParams>() {

    override fun call(params: MainParams): Flow<Either<List<AutoBrandEntity>>> {
        return repository.getAutoBrands(params.request)
    }
}

