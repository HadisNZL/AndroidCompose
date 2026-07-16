package com.flyguy.big.data.repository

import com.flyguy.big.data.model.api.BaseResponse
import com.flyguy.big.data.network.NetworkResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * 生产级别 Repository 基类
 */
abstract class BaseRepository {

    /**
     * 安全地执行 API 调用并封装结果
     */
    suspend fun <T> safeApiCall(
        apiCall: suspend () -> BaseResponse<T>
    ): NetworkResult<T> = withContext(Dispatchers.IO) {
        try {
            val response = apiCall()
            if (response.IsSuccess && response.Data != null) {
                NetworkResult.Success(response.Data)
            } else {
                // 生产级：底层统一提取 ErrorCode 及其关联的元数据 (如 LatestDeviceInfo)
                NetworkResult.Error(
                    code = response.ErrorCode,
                    message = response.Message,
                    extra = response.LatestDeviceInfo
                )
            }
        } catch (e: Exception) {
            NetworkResult.Error(-1, e.message ?: "Unknown Error")
        }
    }
}
