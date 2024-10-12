package uz.safargo.driver.navigation

import android.content.Context
import dagger.hilt.android.EntryPointAccessors
import uz.safargo.driver.core.utils.AppScreen
import uz.safargo.driver.di.MyNavigatorEntryPoint


interface CustomNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
    suspend fun replaceTo(screen: AppScreen)

    suspend fun popUntilRoot()
}