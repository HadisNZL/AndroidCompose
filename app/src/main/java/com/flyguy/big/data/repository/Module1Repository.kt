package com.flyguy.big.data.repository

import com.flyguy.big.data.model.api.Device
import com.flyguy.big.data.model.api.DeviceData
import com.flyguy.big.data.network.ApiService
import com.flyguy.big.data.network.NetworkResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

/**
 * 模块一数据层
 */
@Singleton
class Module1Repository @Inject constructor(
    private val apiService: ApiService
) : BaseRepository() {

    private val _devices = MutableStateFlow<List<Device>>(emptyList())
    val devices: StateFlow<List<Device>> = _devices.asStateFlow()

    suspend fun fetchDevices(areaId: Int): NetworkResult<DeviceData> {
        val result = safeApiCall { apiService.getDeviceList(areaId) }
        if (result is NetworkResult.Success) {
            _devices.update { result.data.Devices }
        }
        return result
    }

    fun getDeviceById(deviceId: Long?): Device? {
        return _devices.value.find { it.DeviceId == deviceId }
    }
}
