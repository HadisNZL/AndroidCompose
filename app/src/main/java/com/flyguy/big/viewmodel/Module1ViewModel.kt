package com.flyguy.big.viewmodel

import androidx.lifecycle.viewModelScope
import com.flyguy.big.data.model.api.Device
import com.flyguy.big.data.network.NetworkResult
import com.flyguy.big.data.repository.Module1Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * 模块一状态封装
 */
data class Module1UiState(
    val isLoading: Boolean = false,
    val devices: List<Device> = emptyList(),
    val error: String? = null,
)

/**
 * 模块一意图封装
 */
sealed class Module1Intent {
    data class LoadDevices(val areaId: Int) : Module1Intent()
    object ClearError : Module1Intent()
}

@HiltViewModel
class Module1ViewModel @Inject constructor(
    private val repository: Module1Repository
) : BaseViewModel<Module1UiState, Module1Intent>(Module1UiState()) {

    init {
        viewModelScope.launch {
            repository.devices.collect { deviceList ->
                updateState { it.copy(devices = deviceList) }
            }
        }
        dispatch(Module1Intent.LoadDevices(1308))
    }

    override fun dispatch(intent: Module1Intent) {
        when (intent) {
            is Module1Intent.LoadDevices -> fetchDevices(intent.areaId)
            is Module1Intent.ClearError -> updateState { it.copy(error = null) }
        }
    }

    private fun fetchDevices(areaId: Int) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true) }
            val result = repository.fetchDevices(areaId)
            updateState { it.copy(isLoading = false) }
            if (result is NetworkResult.Error) {
                // 生产级：直接交给基类处理全局错误码 (如 74015 踢出登录)
                handleGlobalError(result)
            }
        }
    }

    fun getDeviceById(deviceId: Long?) = repository.getDeviceById(deviceId)
}
