package uz.safargo.driver.navigation

import uz.safargo.driver.core.utils.AppScreen


interface CustomNavigator {
    suspend fun navigateTo(screen: AppScreen)
    suspend fun back()
    suspend fun replaceTo(screen: AppScreen)

    suspend fun popUntilRoot()
}