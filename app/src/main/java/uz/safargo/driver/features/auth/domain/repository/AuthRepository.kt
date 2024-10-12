package uz.safargo.driver.features.auth.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.models.MainRequestModel
import uz.safargo.driver.features.auth.domain.entities.AutoBrandEntity
import uz.safargo.driver.features.auth.domain.entities.AutoModelEntity
import uz.safargo.driver.features.auth.domain.entities.CheckPhoneNumberEntity
import uz.safargo.driver.features.auth.domain.entities.FacilityEntity
import uz.safargo.driver.features.auth.domain.entities.GenerateOtpForSignUpEntity
import uz.safargo.driver.features.auth.domain.entities.RegionEntity
import uz.safargo.driver.features.auth.domain.entities.UploadFileEntity
import uz.safargo.driver.features.auth.domain.entities.UserEntity
import java.io.File


interface AuthRepository {
    fun checkPhone(request: MainRequestModel): Flow<Either<CheckPhoneNumberEntity>>

    fun generateOtpForSignUp(request: MainRequestModel): Flow<Either<GenerateOtpForSignUpEntity>>

    fun signIn(request: MainRequestModel): Flow<Either<UserEntity>>

    fun signUp(request: MainRequestModel): Flow<Either<UserEntity>>

    fun uploadFile(file: File): Flow<Either<UploadFileEntity>>

    fun getRegions(request: MainRequestModel): Flow<Either<List<RegionEntity>>>

    fun getAutoBrands(request: MainRequestModel): Flow<Either<List<AutoBrandEntity>>>

    fun getAutoModels(request: MainRequestModel): Flow<Either<List<AutoModelEntity>>>

    fun getFacilities(request: MainRequestModel): Flow<Either<List<FacilityEntity>>>


}