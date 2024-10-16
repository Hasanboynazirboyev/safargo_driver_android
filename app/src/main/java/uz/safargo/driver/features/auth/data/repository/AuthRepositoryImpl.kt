package uz.safargo.driver.features.auth.data.repository


import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.core.helpers.AppHelper
import uz.safargo.driver.core.models.MainRequestModel
import uz.safargo.driver.core.models.Message
import uz.safargo.driver.features.auth.data.models.toEntity
import uz.safargo.driver.features.auth.data.remote.AuthApi
import uz.safargo.driver.features.auth.domain.entities.AutoBrandEntity
import uz.safargo.driver.features.auth.domain.entities.AutoModelEntity
import uz.safargo.driver.features.auth.domain.entities.CheckPhoneNumberEntity
import uz.safargo.driver.features.auth.domain.entities.FacilityEntity
import uz.safargo.driver.features.auth.domain.entities.GenerateOtpForSignUpEntity
import uz.safargo.driver.features.auth.domain.entities.RegionEntity
import uz.safargo.driver.features.auth.domain.entities.UploadFileEntity
import uz.safargo.driver.features.auth.domain.entities.UserEntity
import uz.safargo.driver.features.auth.domain.repository.AuthRepository
import java.io.File

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
) : AuthRepository {
    override fun checkPhone(request: MainRequestModel): Flow<Either<CheckPhoneNumberEntity>> {
        return flow {
            val response =
                api.checkPhone(request.body)
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

    override fun generateOtpForSignUp(request: MainRequestModel): Flow<Either<GenerateOtpForSignUpEntity>> {
        return flow {
            val response =
                api.generateOtpForSignUp(request.body)
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

    override fun signIn(request: MainRequestModel): Flow<Either<UserEntity>> {
        return flow {
            val response =
                api.signIn(request.body)
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

    override fun signUp(request: MainRequestModel): Flow<Either<UserEntity>> {
        return flow {
            val response =
                api.signUp(request.body)
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

    override fun uploadFile(file: File): Flow<Either<UploadFileEntity>> {
        return flow {
            val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
            val dimensions = AppHelper.getImageDimensions(file)

            val body = MultipartBody.Part.createFormData("files", file.name, requestFile)
            val response =
                api.uploadFile(files = body, width = dimensions.first, height = dimensions.second)
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

    override fun getRegions(request: MainRequestModel): Flow<Either<List<RegionEntity>>> {
        return flow {
            val response =
                api.getRegions(request.body)
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

    override fun getAutoBrands(request: MainRequestModel): Flow<Either<List<AutoBrandEntity>>> {
        return flow {
            val response =
                api.getBrands(request.body)
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

    override fun getAutoModels(request: MainRequestModel): Flow<Either<List<AutoModelEntity>>> {
        return flow {
            val response =
                api.getAutoModels(request.body)
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

    override fun getFacilities(request: MainRequestModel): Flow<Either<List<FacilityEntity>>> {
        return flow {
            val response =
                api.getFacilities(request.body)
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
