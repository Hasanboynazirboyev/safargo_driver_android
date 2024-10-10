package uz.safargo.driver.navigation
import cafe.adriel.voyager.androidx.AndroidScreen
import kotlinx.coroutines.flow.MutableSharedFlow
import uz.safargo.driver.core.utils.AppNavigation
import uz.safargo.driver.core.utils.AppScreen
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CustomNavigatorImpl @Inject constructor() : CustomNavigator, NavigationDispatcher {
    override val dispatcher = MutableSharedFlow<AppNavigation>()

    private suspend fun navigate(screen: AppNavigation) {
        dispatcher.emit(screen)
    }

    override suspend fun navigateTo(screen: AndroidScreen) = navigate {
        push(screen)

    }

    override suspend fun back() = navigate {
        pop()
    }

    override suspend fun replaceTo(screen: AppScreen) = navigate {
        replace(screen)
    }

    override suspend fun popUntilRoot() = navigate {
        popUntilRoot()
    }

}