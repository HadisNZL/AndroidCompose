package com.flyguy.big.data.network

import com.flyguy.big.data.model.api.TokenResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Headers
import retrofit2.http.POST

/**
 * 专门处理认证（Token）相关的接口
 */
interface AuthService {

    /**
     * 获取 Token (登录获取)
     * 标准 OAuth2.0 返回，直接对应 TokenResponse
     */
    @Headers("No-Auth: true")
    @FormUrlEncoded
    @POST("UserIdentity_v1/connect/token")
    suspend fun getToken(
        @Field("grant_type") grantType: String = "user_login_in",
        @Field("login_name") loginName: String,
        @Field("password") password: String,
        @Field("device_info") deviceInfo: String,
        @Field("device_uuid") deviceUuid: String,
        @Field("client_id") clientId: String = "ahc_client",
        @Field("client_secret") clientSecret: String = "3331e256-c21a-4624-90f6-3402725e027c",
        @Field("scopes") scopes: String = "ahc_business_api"
    ): TokenResponse

    /**
     * 刷新 Token (同步调用)
     */
    @Headers("No-Auth: true")
    @FormUrlEncoded
    @POST("UserIdentity_v1/connect/token")
    fun refreshTokenSync(
        @Field("grant_type") grantType: String = "refresh_token",
        @Field("refresh_token") refreshToken: String,
        @Field("client_id") clientId: String = "ahc_client",
        @Field("client_secret") clientSecret: String = "3331e256-c21a-4624-90f6-3402725e027c"
    ): retrofit2.Call<TokenResponse>
}
