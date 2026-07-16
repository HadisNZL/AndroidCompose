package com.flyguy.big.data.network

import android.content.Context
import com.flyguy.big.data.model.api.TokenResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 生产级别 Token 管理器
 * 负责 Token 的持久化、内存缓存以及过期逻辑判断
 */
@Singleton
class TokenManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    // 内存缓存提高性能
    var accessToken: String? = prefs.getString("access_token", null)
        private set
    var refreshToken: String? = prefs.getString("refresh_token", null)
        private set
    private var expiresAt: Long = prefs.getLong("expires_at", 0L)

    /**
     * 保存 Token
     */
    fun saveToken(token: TokenResponse) {
        accessToken = token.access_token
        refreshToken = token.refresh_token
        // 计算过期时间点 (当前时间 + 有效时长，提前 60 秒作为缓冲区)
        expiresAt = System.currentTimeMillis() + (token.expires_in * 1000) - 60000

        prefs.edit().apply {
            putString("access_token", accessToken)
            putString("refresh_token", refreshToken)
            putLong("expires_at", expiresAt)
            apply()
        }
    }

    /**
     * 判断 Token 是否有效
     */
    fun isTokenValid(): Boolean {
        return accessToken != null && System.currentTimeMillis() < expiresAt
    }

    /**
     * 清除 Token (用于注销)
     */
    fun clearToken() {
        accessToken = null
        refreshToken = null
        expiresAt = 0
        prefs.edit().clear().apply()
    }
}
