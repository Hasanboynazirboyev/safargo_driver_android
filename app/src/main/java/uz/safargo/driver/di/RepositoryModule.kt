package uz.safargo.driver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.safargo.driver.features.auth.data.repository.AuthRepositoryImpl
import uz.safargo.driver.features.auth.domain.repository.AuthRepository
import uz.safargo.driver.features.home.domain.repository.HomeRepository
import uz.safargo.driver.features.home.data.repository.HomeRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @Binds
    fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

}