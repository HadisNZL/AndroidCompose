package com.flyguy.big.data.network

import com.flyguy.big.data.model.api.BaseResponse
import com.flyguy.big.data.model.api.DeviceData
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * 生产级别 API 定义
 */
interface ApiService {

    companion object {
        // 将版本号抽离，方便未来统一升级（如改为 V3.5）
        private const val API_VERSION = "V3.4"
        private const val BASE_PATH = "Aijia/BusinessAPI/$API_VERSION/APP"
    }

    /**
     * [需鉴权] 获取设备列表
     * 默认拦截器会自动注入 Authorization: Your-Token
     */
    @GET("$BASE_PATH/GetDevices")
    suspend fun getDeviceList(
        @Query("areaId") areaId: Int
    ): BaseResponse<DeviceData>

}
