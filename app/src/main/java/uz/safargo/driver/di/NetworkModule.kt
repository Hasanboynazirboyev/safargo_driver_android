package uz.safargo.driver.di
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.safargo.driver.core.constants.AppConstants
import uz.safargo.driver.core.local_storage.LocalStorage
import uz.safargo.driver.features.auth.data.remote.AuthApi
import uz.safargo.driver.features.home.data.remote.HomeApi
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @[Provides Singleton]
    fun providesHttpLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @[Provides Singleton]
    fun providesOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        localStorage: LocalStorage
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor)
        .addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val response =
                request.newBuilder()
//                    .header("Authorization", localStorage.loginResponse?.access_token ?: "")
                    .header("content-type", "application/json")
                    .build()
            return@Interceptor chain.proceed(response)
        })
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .callTimeout(30, TimeUnit.SECONDS)
        .build()


    @[Provides Singleton]
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder().baseUrl(AppConstants.baseUrl).client(client).addConverterFactory(
            GsonConverterFactory.create()
        ).build()
    }
    @[Provides Singleton]
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @[Provides Singleton]
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }


}