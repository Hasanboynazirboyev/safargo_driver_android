package uz.safargo.driver.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        localStorage: LocalStorage,
        chuckerInterceptor: ChuckerInterceptor,
    ): OkHttpClient = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).addInterceptor(chuckerInterceptor)
        .addInterceptor(Interceptor { chain ->
            val request = chain.request()
            val response =
                request.newBuilder()
                    .header("Authorization", localStorage.accessToken ?: "")
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


    @Provides
    @Singleton
    fun provideChuckerInterceptor(
        @ApplicationContext context: Context,
        chuckerCollector: ChuckerCollector
    ): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(chuckerCollector)
            .maxContentLength(250_000L)

            .build()

    }

    @Provides
    @Singleton
    fun provideChuckerCollector(@ApplicationContext context: Context): ChuckerCollector {
        return ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
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