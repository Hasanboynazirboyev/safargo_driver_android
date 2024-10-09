package uz.safargo.driver.navigation
import kotlinx.coroutines.flow.SharedFlow
import uz.safargo.driver.core.utils.AppNavigation

interface NavigationDispatcher {
    val dispatcher: SharedFlow<AppNavigation>
}
