package com.flyguy.big.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flyguy.big.R
import com.flyguy.big.data.model.api.Device
import com.flyguy.big.viewmodel.BaseEffect
import com.flyguy.big.viewmodel.Module1Intent
import com.flyguy.big.viewmodel.Module1ViewModel

/**
 * 首页/模块一：展示真实设备列表
 */
@Composable
fun Module1Screen(
    viewModel: Module1ViewModel = hiltViewModel(),
    onNavigate: (BaseEffect.Navigate) -> Unit,
    onDeviceClick: (Device) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    // 1. 拦截全局效应（处理踢出登录跳转）
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            if (effect is BaseEffect.Navigate) {
                onNavigate(effect)
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        when {
            state.isLoading && state.devices.isEmpty() -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            state.error != null -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "加载失败: ${state.error}", color = Color.Red)
                    Button(onClick = { viewModel.dispatch(Module1Intent.LoadDevices(1308)) }) {
                        Text("重试")
                    }
                }
            }

            else -> {
                DeviceList(
                    devices = state.devices, onItemClick = onDeviceClick
                )
            }
        }
    }
}

@Composable
fun DeviceList(
    devices: List<Device>, onItemClick: (Device) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(devices) { device ->
            DeviceItem(
                device = device, modifier = Modifier.clickable { onItemClick(device) })
        }
    }
}

@Composable
fun DeviceItem(device: Device, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(colorResource(R.color.teal_700).copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "📟", fontSize = 24.sp)
            }

            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = device.Model,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "ID: ${device.DeviceId} | Type: ${device.DeviceType}",
                    fontSize = 14.sp,
                    color = Color.Gray
                )
            }

            StatusBadge(status = device.ConnectionState)
        }
    }
}

@Composable
fun StatusBadge(status: String) {
    val color = if (status == "online") Color(0xFF4CAF50) else Color.Gray
    Surface(
        color = color.copy(alpha = 0.1f),
        shape = RoundedCornerShape(16.dp),
        border = BorderStroke(0.5.dp, color)
    ) {
        Text(
            text = status,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp),
            fontSize = 12.sp,
            color = color,
            fontWeight = FontWeight.Medium
        )
    }
}
