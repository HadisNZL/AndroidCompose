package com.flyguy.big.data.model.api

/**
 * OAuth2.0 Token 响应模型
 */
data class TokenResponse(
    val access_token: String,
    val expires_in: Long,
    val token_type: String,
    val refresh_token: String? = null
)
