package uz.safargo.driver.features.auth.data.remote

import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.QueryMap
import uz.safargo.driver.features.auth.data.models.AutoBrandsListResponse
import uz.safargo.driver.features.auth.data.models.AutoModelsListResponse
import uz.safargo.driver.features.auth.data.models.CheckPhoneNumberResponse
import uz.safargo.driver.features.auth.data.models.FacilitiesListResponse
import uz.safargo.driver.features.auth.data.models.GenerateOtpForSignUpResponse
import uz.safargo.driver.features.auth.data.models.RegionsListResponse
import uz.safargo.driver.features.auth.data.models.UploadFileResponse
import uz.safargo.driver.features.auth.data.models.UserResponse


interface AuthApi {
    @POST("/api/auth/check-phone")
    suspend fun checkPhone(
        @Body body: Map<String, @JvmSuppressWildcards Any>

    ): Response<CheckPhoneNumberResponse>

    @POST("/api/auth/generate-otp-for-sing-up")
    suspend fun generateOtpForSignUp(
        @Body body: Map<String, @JvmSuppressWildcards Any>
    ): Response<GenerateOtpForSignUpResponse>

    @Multipart
    @POST("/api/files/upload")
    fun uploadFile(
        @Part files: MultipartBody.Part,
        @Part("height") height: Int,
        @Part("width") width: Int
    ): Response<UploadFileResponse>

    @POST("/api/auth/sign-in-driver")
    suspend fun signIn(
        @Body body: Map<String, @JvmSuppressWildcards Any>
    ): Response<UserResponse>

    @POST("/api/auth/sign-up")
    suspend fun signUp(
        @Body body: Map<String, @JvmSuppressWildcards Any>
    ): Response<UserResponse>

    @GET("/api/driver/regions")
    suspend fun getRegions(
        @QueryMap query: Map<String, Any>
    ): Response<RegionsListResponse>

    @GET("/api/driver/auto-brands")
    suspend fun getBrands(
        @QueryMap query: Map<String, Any>
    ): Response<AutoBrandsListResponse>

    @GET("/api/driver/auto-models")
    suspend fun getAutoModels(
        @QueryMap query: Map<String, Any>
    ): Response<AutoModelsListResponse>

    @GET("/api/driver/facilities")
    suspend fun getFacilities(
        @QueryMap query: Map<String, Any>
    ): Response<FacilitiesListResponse>

}