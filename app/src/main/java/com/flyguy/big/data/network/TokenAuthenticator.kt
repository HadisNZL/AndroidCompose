package com.flyguy.big.data.network

import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject
import javax.inject.Provider

/**
 * 生产级 401 自动重连器
 */
class TokenAuthenticator @Inject constructor(
    private val tokenManager: TokenManager,
    private val authServiceProvider: Provider<AuthService> // 使用 Provider 避免循环依赖
) : Authenticator {

    override fun authenticate(route: Route?, response: Response): Request? {
        // 1. 如果已经重试过且还是 401，放弃重试，防止死循环
        if (response.priorResponse != null) {
            return null
        }

        // 2. 同步执行刷新 Token 操作
        val refreshToken = tokenManager.refreshToken ?: return null
        val authService = authServiceProvider.get()
        
        val refreshResponse = authService.refreshTokenSync(
            refreshToken = refreshToken
        ).execute()

        return if (refreshResponse.isSuccessful && refreshResponse.body() != null) {
            // 3. 刷新成功，保存新 Token
            val newToken = refreshResponse.body()!!
            tokenManager.saveToken(newToken)

            // 4. 使用新 Token 构造并返回新请求
            response.request.newBuilder()
                .header("Authorization", "Bearer ${newToken.access_token}")
                .build()
        } else {
            // 5. 刷新失败（如 RefreshToken 也过期了），清除 Token 并引导用户重新登录
            tokenManager.clearToken()
            null
        }
    }
}
