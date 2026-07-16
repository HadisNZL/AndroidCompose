package com.flyguy.big.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flyguy.big.data.model.api.LatestDeviceInfo
import com.flyguy.big.data.network.NetworkResult
import com.flyguy.big.navigation.NavRoutes
import com.flyguy.big.ui.components.KickOutDialogManager
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * 生产级别 UI 效应基类
 */
sealed class BaseEffect {
    data class ShowToast(val message: String) : BaseEffect()
    data class Navigate(val route: String, val popUpTo: String? = null, val inclusive: Boolean = false) : BaseEffect()
    object Back : BaseEffect()
}

/**
 * 生产级别 MVI ViewModel 基类
 */
abstract class BaseViewModel<State, Intent>(initialState: State) : ViewModel() {

    private val _uiState = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<BaseEffect>()
    val effect: SharedFlow<BaseEffect> = _effect.asSharedFlow()

    protected fun updateState(reducer: (State) -> State) {
        _uiState.update(reducer)
    }

    protected fun sendEffect(effect: BaseEffect) {
        viewModelScope.launch {
            _effect.emit(effect)
        }
    }

    /**
     * 生产级别：全局错误码处理中心
     */
    protected fun handleGlobalError(error: NetworkResult.Error) {
        when {
            // 拦截 74015：被其他设备挤下线
            error.code == 74015 && error.extra is LatestDeviceInfo -> {
                // 核心修复：一旦收到踢出信号，【立即】物理清除本地 Token
                // 这样即使不点确认直接杀进程，下次进来也是登录页
                KickOutDialogManager.performActionThenShow(error.extra) {
                    sendEffect(BaseEffect.Navigate(
                        route = NavRoutes.LOGIN,
                        popUpTo = NavRoutes.MAIN,
                        inclusive = true
                    ))
                }
            }
            // 拦截其他通用错误码 (如 401 Token 失效等) 也可以写在这里
            
            // 默认处理：显示 Toast
            else -> {
                sendEffect(BaseEffect.ShowToast(error.message))
            }
        }
    }

    abstract fun dispatch(intent: Intent)
}
