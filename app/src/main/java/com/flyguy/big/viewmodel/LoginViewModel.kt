// Login button loading state already implemented
package com.flyguy.big.viewmodel

import androidx.lifecycle.viewModelScope
import com.flyguy.big.config.AppConfig
import com.flyguy.big.data.network.NetworkResult
import com.flyguy.big.data.repository.AuthRepository
import com.flyguy.big.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

data class LoginUiState(
    val isLoading: Boolean = false, val error: String? = null
)

sealed class LoginIntent {
    data class Login(val loginName: String, val password: String) : LoginIntent()
}

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel<LoginUiState, LoginIntent>(LoginUiState()) {

    override fun dispatch(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.Login -> performLogin(intent.loginName, intent.password)
        }
    }

    private fun performLogin(name: String, pass: String) {
        viewModelScope.launch {
            updateState { it.copy(isLoading = true, error = null) }

            val deviceUuid = AppConfig.getDeviceUuid()
            val deviceInfo = AppConfig.deviceInfo

            val result = repository.login(name, pass, deviceInfo, deviceUuid)

            updateState { it.copy(isLoading = false) }

            when (result) {
                is NetworkResult.Success -> {
                    // 生产级：使用 Effect 统一处理跳转逻辑
                    sendEffect(
                        BaseEffect.Navigate(
                            route = NavRoutes.MAIN, popUpTo = NavRoutes.LOGIN, inclusive = true
                        )
                    )
                }

                is NetworkResult.Error -> {
                    // 生产级：对于特定的错误，可以使用 Effect 弹出 Toast 而不是修改 State
                    if (result.code == 74135) {
                        sendEffect(BaseEffect.ShowToast("账号异常，需二次验证"))
                    } else {
                        updateState { it.copy(error = result.message) }
                    }
                }

                else -> {}
            }
        }
    }
}
