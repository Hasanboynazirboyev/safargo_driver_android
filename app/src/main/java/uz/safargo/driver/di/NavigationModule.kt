package uz.safargo.driver.di

import dagger.Binds
import dagger.Module
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.safargo.driver.navigation.NavigationDispatcher
import uz.safargo.driver.navigation.CustomNavigator
import uz.safargo.driver.navigation.CustomNavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun provideNavigator(impl: CustomNavigatorImpl): CustomNavigator

    @Binds
    fun provideNavigationDispatcher(impl: CustomNavigatorImpl): NavigationDispatcher

}

@EntryPoint
@InstallIn(SingletonComponent::class)
interface MyNavigatorEntryPoint {
    fun getNavigator(): CustomNavigator
}