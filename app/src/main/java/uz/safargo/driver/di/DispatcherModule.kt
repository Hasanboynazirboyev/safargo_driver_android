package uz.safargo.driver.di
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.safargo.driver.core.dispatcher.DispatcherProvider
import uz.safargo.driver.core.dispatcher.PlatformDispatcherProvider

@Module
@InstallIn(SingletonComponent::class)
interface DispatcherModule {
    @Binds
    fun bindDispatcherProvider(impl: PlatformDispatcherProvider): DispatcherProvider
}