package uz.safargo.driver.core.models

data class MainRequestModel(
    val body: Map<String, Any> = mapOf(),
    val queryParams: Map<String, Any> = mapOf()
)
