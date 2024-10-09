package uz.safargo.driver.features.home.presentation.viewmodel
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import uz.safargo.driver.core.domain.FormZStatus
import uz.safargo.driver.core.error.Either
import uz.safargo.driver.features.home.data.models.request.GetProductsRequest
import uz.safargo.driver.features.home.domain.entities.ProductsEntity
import uz.safargo.driver.features.home.domain.use_case.GetProductsParams
import uz.safargo.driver.features.home.domain.use_case.GetProductsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getWeatherUseCase: GetProductsUseCase,
) : ViewModel() {
    val uiState = MutableStateFlow(HomeScreenUiState())

    private fun onEventDispatch(event: HomeScreenIntent) {
        when (event) {
            is HomeScreenIntent.GetWeatherData -> getWeatherData()
        }

    }

    init {
        Log.i("TAG", "HomeViewModelInit")
        onEventDispatch(HomeScreenIntent.GetWeatherData)
    }

    private fun getWeatherData() {
        uiState.update {
            it.copy(
                status = FormZStatus.Loading,
            )
        }
       viewModelScope.launch {
           getWeatherUseCase.call(
               GetProductsParams(
                   request = GetProductsRequest(
                     example = "example"
                   )
               )
           )
               .collect { result ->
                   when (result) {
                       is Either.Right -> {
                           uiState.update {
                               it.copy(
                                   status = FormZStatus.Success,
                                   products = result.data,
                               )
                           }
                       }
                       is Either.Left -> {
                           uiState.update {
                               it.copy(
                                   status = FormZStatus.Error,
                               )
                           }
                       }

                   }
               }
       }

    }

}

data class HomeScreenUiState(
    val status: FormZStatus = FormZStatus.Initial,
    val products: List<ProductsEntity> = emptyList(),
)

sealed class HomeScreenIntent {
    object GetWeatherData : HomeScreenIntent()

}