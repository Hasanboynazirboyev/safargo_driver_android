package uz.safargo.driver.core.domain

enum class FormzStatus {
    Initial,
    Loading,
    Success,
    Error;
}


fun FormzStatus.isLoading() = this == FormzStatus.Loading
fun FormzStatus.isError() = this == FormzStatus.Error

