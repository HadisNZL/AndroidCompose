package com.flyguy.big.ui.components

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.flyguy.big.data.model.api.LatestDeviceInfo
import java.text.SimpleDateFormat
import java.util.*

/**
 * 生产级别：单例弹窗管理器
 */
object KickOutDialogManager {

    var isShow by mutableStateOf(false)
        private set

    var dialogMessage by mutableStateOf("")
        private set

    private var globalLogoutAction: () -> Unit = {}
    private var onNavigateAction: () -> Unit = {}

    fun init(logoutAction: () -> Unit) {
        this.globalLogoutAction = logoutAction
    }

    /**
     * 核心优化：执行登出逻辑后显示弹窗
     * 确保即使 App 杀进程，本地登录态也已经清理干净
     */
    fun performActionThenShow(info: LatestDeviceInfo, onNavigate: () -> Unit) {
        if (isShow) return 

        // 1. 立即执行物理登出（清除本地存储）
        globalLogoutAction()

        // 2. 准备弹窗数据
        val formattedTime = formatJsonDate(info.LatestLoginTime)
        dialogMessage = "您的账号在 $formattedTime 在 ${info.LatestDevice} 这个设备上登录了"
        onNavigateAction = onNavigate
        isShow = true
    }

    /**
     * 用户点击确认，只需处理导航
     */
    fun confirm() {
        isShow = false
        onNavigateAction()
    }

    private fun formatJsonDate(jsonDate: String): String {
        return try {
            val timestampStr = jsonDate.replace("/Date(", "").replace(")/", "")
            val timestamp = timestampStr.toLong()
            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
            sdf.format(Date(timestamp))
        } catch (e: Exception) {
            jsonDate
        }
    }
}
