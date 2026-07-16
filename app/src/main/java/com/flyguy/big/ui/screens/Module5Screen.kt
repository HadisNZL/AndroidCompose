package com.flyguy.big.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.flyguy.big.R
import com.flyguy.big.viewmodel.BaseEffect
import com.flyguy.big.viewmodel.Module5Intent
import com.flyguy.big.viewmodel.Module5ViewModel

/**
 * 五页 (我的)
 * 实现退出登录功能并还原图片 UI
 */
@Composable
fun Module5Screen(
    viewModel: Module5ViewModel = hiltViewModel(),
    onLogout: (BaseEffect.Navigate) -> Unit
) {
    // 监听退出登录的效应
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            if (effect is BaseEffect.Navigate) {
                onLogout(effect)
            }
        }
    }

    val themeColor = colorResource(id = R.color.themeColor)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFDFDFD)),
        contentPadding = PaddingValues(bottom = 32.dp)
    ) {
        // 1. 头部用户信息
        item {
            HeaderSection()
        }

        // 2. 功能卡片
        item {
            PromoCardsSection(themeColor)
        }

        // 3. 我的家庭组
        item {
            HomeGroupSection()
        }

        // 4. 通用设置
        item {
            GeneralSettingsSection()
        }

        // 5. 退出登录按钮
        item {
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp)
                    .height(54.dp)
                    .clip(RoundedCornerShape(27.dp))
                    .background(Color(0xFFF5F5F5))
                    .clickable { viewModel.dispatch(Module5Intent.Logout) },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "退出登录",
                    color = Color.Red,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun HeaderSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp, 40.dp, 24.dp, 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = "Hi!", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
            Text(text = "diviner", fontSize = 28.sp, fontWeight = FontWeight.ExtraBold)
        }
        // 头像
        Box(
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .background(Color.LightGray),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "👤", fontSize = 32.sp)
        }
    }
}

@Composable
fun PromoCardsSection(themeColor: Color) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // 卡片1
        Box(
            modifier = Modifier
                .weight(1f)
                .height(90.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Brush.horizontalGradient(listOf(themeColor, Color(0xFFFFB74D))))
                .padding(16.dp)
        ) {
            Column {
                Text("功能快速指南", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("快速玩转Aljia APP >>", color = Color.White.copy(0.8f), fontSize = 11.sp)
            }
        }
        // 卡片2
        Box(
            modifier = Modifier
                .weight(1f)
                .height(90.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color(0xFF66BB6A))
                .padding(16.dp)
        ) {
            Column {
                Text("全新守护", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text("可靠的家庭安全", color = Color.White.copy(0.8f), fontSize = 11.sp)
            }
        }
    }
}

@Composable
fun HomeGroupSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("我的家庭组", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Text("查看全部 >", color = Color.Gray, fontSize = 13.sp)
        }
        
        Spacer(modifier = Modifier.height(12.dp))
        
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column {
                GroupItem("diviner", 8)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color(0xFFF0F0F0))
                GroupItem("AijiaTest1", 3)
                HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp), thickness = 0.5.dp, color = Color(0xFFF0F0F0))
                GroupItem("birt", 8)
            }
        }
    }
}

@Composable
fun GroupItem(name: String, count: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.size(36.dp).clip(CircleShape).background(Color(0xFFEEEEEE)), contentAlignment = Alignment.Center) {
            Text("🏠", fontSize = 18.sp)
        }
        Text(text = name, modifier = Modifier.padding(start = 12.dp).weight(1f), fontSize = 16.sp)
        Surface(color = Color(0xFFE8EAF6), shape = RoundedCornerShape(12.dp)) {
            Row(modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp), verticalAlignment = Alignment.CenterVertically) {
                Text("👥", fontSize = 12.sp)
                Text(text = " $count", fontSize = 12.sp, color = Color(0xFF3F51B5), fontWeight = FontWeight.Bold)
            }
        }
        Text(" >", color = Color.LightGray, modifier = Modifier.padding(start = 8.dp))
    }
}

@Composable
fun GeneralSettingsSection() {
    Column(modifier = Modifier.padding(16.dp)) {
        Text("通用设置", fontWeight = FontWeight.Bold, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(12.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 1.dp),
            shape = RoundedCornerShape(12.dp)
        ) {
            Column {
                SettingItem("推送服务设置", icon = "🚀")
                SettingItem("系统设置", icon = "⚙️")
                SettingItem("授权登录", icon = "📺")
                SettingItem("联系客服", icon = "🎧", extra = "+1(877)482-5503")
                SettingItem("使用帮助", icon = "📔")
                SettingItem("用户反馈", icon = "💬")
                SettingItem("关于我们", icon = "ℹ️", extra = "v5.1.5")
                SettingItem("内测功能", icon = "🧪")
            }
        }
    }
}

@Composable
fun SettingItem(title: String, icon: String, extra: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = icon, fontSize = 20.sp)
        Text(text = title, modifier = Modifier.padding(start = 12.dp).weight(1f), fontSize = 16.sp)
        if (extra != null) {
            Text(text = extra, color = Color.Gray, fontSize = 14.sp)
        }
        Text(" >", color = Color.LightGray, modifier = Modifier.padding(start = 8.dp))
    }
}
