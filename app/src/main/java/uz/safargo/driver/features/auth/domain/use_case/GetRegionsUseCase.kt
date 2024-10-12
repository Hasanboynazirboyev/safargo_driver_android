package uz.safargo.driver.features.auth.domain.use_case

import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.use_case.MainParams
import uz.safargo.driver.core.use_case.UseCase
import uz.safargo.driver.features.auth.domain.entities.RegionEntity
import uz.safargo.driver.features.auth.domain.repository.AuthRepository
import javax.inject.Inject


class GetRegionsUseCase @Inject constructor(
    private val repository: AuthRepository
) : UseCase<List<RegionEntity>, MainParams>() {

    override fun call(params: MainParams): Flow<Either<List<RegionEntity>>> {
        return repository.getRegions(params.request)
    }
}

