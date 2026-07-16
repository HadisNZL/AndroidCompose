package com.flyguy.big.data.model.api

/**
 * 踢出登录设备信息
 */
data class LatestDeviceInfo(
    val LatestDevice: String,
    val LatestLoginTime: String
)

/**
 * 生产级别网络响应基类
 */
data class BaseResponse<T>(
    val Data: T?,
    val IsSuccess: Boolean,
    val Message: String,
    val ErrorCode: Int,
    val ErrorDomain: String? = null,
    val LatestDeviceInfo: LatestDeviceInfo? = null
)
