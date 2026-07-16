package com.flyguy.big.data.model.api

/**
 * 设备列表数据模型
 * 直接使用与 JSON Key 一致的命名，移除 SerializedName
 */
data class DeviceData(
    val Devices: List<Device>
)

data class Device(
    val DeviceId: Long,
    val DeviceType: String,
    val ConnectionState: String,
    val Model: String,
    val PushConfig: PushConfig?,
    val PicInfo: PicInfo?,
    val BattryStatus: Int,
    val WifiName: String?,
    val PSTN: String?
)

data class PushConfig(
    val PushCategoryList: List<PushCategory>,
    val IsEnable: Boolean
)

data class PushCategory(
    val PushCategoryName: String,
    val IsPush: Boolean,
    val PushCategoryId: Int
)

data class PicInfo(
    val PicId: Long,
    val PicName: String?
)
