package uz.safargo.driver.di
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.safargo.driver.core.local_storage.LocalStorage

@Module
@InstallIn(SingletonComponent::class)
class LocalStorageModule {
    @Provides
    fun localStorageProvides(@ApplicationContext context: Context): LocalStorage =
        LocalStorage(context)


}