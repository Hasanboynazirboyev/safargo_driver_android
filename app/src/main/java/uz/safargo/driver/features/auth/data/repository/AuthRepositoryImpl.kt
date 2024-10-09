package uz.safargo.driver.features.auth.data.repository


import uz.safargo.driver.features.auth.data.remote.AuthApi
import uz.safargo.driver.features.auth.domain.repository.AuthRepository

import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
) : AuthRepository {


}
