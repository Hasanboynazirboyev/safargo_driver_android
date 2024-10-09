package uz.safargo.driver.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.safargo.driver.navigation.NavigationDispatcher
import uz.safargo.driver.navigation.Navigator
import uz.safargo.driver.navigation.NavigatorImpl

@Module
@InstallIn(SingletonComponent::class)
interface NavigationModule {

    @Binds
    fun provideNavigator(impl: NavigatorImpl): Navigator

    @Binds
    fun provideNavigationDispatcher(impl: NavigatorImpl): NavigationDispatcher

}