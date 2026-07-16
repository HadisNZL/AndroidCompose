package com.flyguy.big.data.repository

import com.flyguy.big.data.model.api.TokenResponse
import com.flyguy.big.data.network.AuthService
import com.flyguy.big.data.network.NetworkResult
import com.flyguy.big.data.network.TokenManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authService: AuthService,
    private val tokenManager: TokenManager
) {

    /**
     * 执行登录
     */
    suspend fun login(
        loginName: String,
        password: String,
        deviceInfo: String,
        deviceUuid: String
    ): NetworkResult<TokenResponse> = withContext(Dispatchers.IO) {
        try {
            val response = authService.getToken(
                loginName = loginName,
                password = password,
                deviceInfo = deviceInfo,
                deviceUuid = deviceUuid
            )
            // 保存 Token
            tokenManager.saveToken(response)
            NetworkResult.Success(response)
        } catch (e: Exception) {
            NetworkResult.Error(-1, e.message ?: "Login Failed")
        }
    }

    /**
     * 检查是否已登录
     */
    fun isUserLoggedIn(): Boolean = tokenManager.isTokenValid()

    /**
     * 退出登录
     */
    fun logout() {
        tokenManager.clearToken()
    }
}
