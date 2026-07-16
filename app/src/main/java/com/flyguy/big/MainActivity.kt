package com.flyguy.big

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.flyguy.big.data.repository.AuthRepository
import com.flyguy.big.ui.AppNavigation
import com.flyguy.big.ui.components.KickOutDialogManager
import com.flyguy.big.ui.theme.FlyGuyTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var authRepository: AuthRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        // 生产级别：初始化全局弹窗管理器的物理登出逻辑
        KickOutDialogManager.init {
            authRepository.logout()
        }
        
        // 生产级别：根据 Token 有效性决定启动页
        val isLoggedIn = authRepository.isUserLoggedIn()
        
        setContent {
            FlyGuyTheme {
                AppNavigation(isLoggedIn = isLoggedIn)
            }
        }
    }
}
