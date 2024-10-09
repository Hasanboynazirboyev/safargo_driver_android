package uz.safargo.driver.features.auth.data.remote
import retrofit2.Response
import retrofit2.http.GET
import uz.safargo.driver.features.home.data.models.response.ProductsResponse


interface AuthApi {
    @GET("/products")
    suspend fun getProducts(
    ): Response<ProductsResponse>

}