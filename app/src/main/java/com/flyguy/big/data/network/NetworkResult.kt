package com.flyguy.big.data.network

/**
 * 生产级别网络结果封装
 * 增加 extra 字段用于携带特定错误码下的附加业务数据（如设备冲突信息）
 */
sealed class NetworkResult<out T> {
    data class Success<out T>(val data: T) : NetworkResult<T>()
    data class Error(val code: Int, val message: String, val extra: Any? = null) : NetworkResult<Nothing>()
    object Loading : NetworkResult<Nothing>()
}
