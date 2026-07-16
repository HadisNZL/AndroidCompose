package com.flyguy.big.viewmodel

import com.flyguy.big.data.repository.AuthRepository
import com.flyguy.big.navigation.NavRoutes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed class Module5Intent {
    object Logout : Module5Intent()
}

@HiltViewModel
class Module5ViewModel @Inject constructor(
    private val repository: AuthRepository
) : BaseViewModel<Unit, Module5Intent>(Unit) {

    override fun dispatch(intent: Module5Intent) {
        when (intent) {
            is Module5Intent.Logout -> {
                // 1. 清除本地 Token
                repository.logout()
                // 2. 发送全局导航效应：返回登录页并清空主页栈
                sendEffect(BaseEffect.Navigate(
                    route = NavRoutes.LOGIN,
                    popUpTo = NavRoutes.MAIN,
                    inclusive = true
                ))
            }
        }
    }
}
