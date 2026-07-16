package com.flyguy.big.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.flyguy.big.ui.components.AppTopBar
import com.flyguy.big.viewmodel.BaseEffect
import com.flyguy.big.viewmodel.Module1ViewModel

/**
 * 设备详情页面
 */
@Composable
fun DeviceDetailScreen(
    deviceId: Long?,
    viewModel: Module1ViewModel = hiltViewModel(),
    onNavigate: (BaseEffect.Navigate) -> Unit, // 增加导航回调
    onBack: () -> Unit
) {
    // 1. 拦截全局效应（处理详情页请求时触发的踢出登录）
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            if (effect is BaseEffect.Navigate) {
                onNavigate(effect)
            }
        }
    }

    val device = viewModel.getDeviceById(deviceId)

    Scaffold(
        topBar = {
            AppTopBar(
                title = "设备详情",
                onBackClick = onBack
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "设备型号: ${device?.Model ?: "加载中..."}", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "设备ID: ${device?.DeviceId}", fontSize = 16.sp)
            Text(text = "连接状态: ${device?.ConnectionState}", fontSize = 16.sp)
            Text(text = "Wi-Fi: ${device?.WifiName ?: "N/A"}", fontSize = 16.sp)
            Text(text = "电量: ${device?.BattryStatus}%", fontSize = 16.sp)
        }
    }
}
