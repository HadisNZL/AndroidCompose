package com.flyguy.big.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.flyguy.big.navigation.NavRoutes
import com.flyguy.big.ui.screens.MainScreen
import com.flyguy.big.ui.screens.DeviceDetailScreen
import com.flyguy.big.ui.screens.LoginScreen
import com.flyguy.big.viewmodel.BaseEffect

/**
 * 整个应用的全局导航中枢
 */
@Composable
fun AppNavigation(isLoggedIn: Boolean) {
    val rootNavController = rememberNavController()

    // 定义一个通用的全局跳转处理器，确保栈清理逻辑一致
    val handleGlobalNavigation: (BaseEffect.Navigate) -> Unit = { navigateEffect ->
        rootNavController.navigate(navigateEffect.route) {
            navigateEffect.popUpTo?.let {
                // 核心：通过 popUpTo + inclusive 实现栈清空
                popUpTo(it) { inclusive = navigateEffect.inclusive }
            }
            // 生产级优化：防止多次快速点击产生多个页面实例
            launchSingleTop = true
        }
    }

    NavHost(
        navController = rootNavController,
        startDestination = if (isLoggedIn) NavRoutes.MAIN else NavRoutes.LOGIN,
    ) {
        // 1. 登录页
        composable(NavRoutes.LOGIN) {
            LoginScreen(
                onNavigate = handleGlobalNavigation
            )
        }

        // 2. 主页容器
        composable(NavRoutes.MAIN) {
            MainScreen(
                onNavigateToDetail = { device ->
                    rootNavController.navigate(NavRoutes.deviceDetail(device.DeviceId))
                },
                onGlobalNavigate = handleGlobalNavigation
            )
        }
        
        // 3. 设备详情页
        composable(
            route = NavRoutes.DEVICE_DETAIL,
            arguments = listOf(navArgument("deviceId") { type = NavType.LongType })
        ) { backStackEntry ->
            val deviceId = backStackEntry.arguments?.getLong("deviceId")
            
            DeviceDetailScreen(
                deviceId = deviceId,
                onNavigate = handleGlobalNavigation,
                onBack = { rootNavController.popBackStack() }
            )
        }
    }
}
