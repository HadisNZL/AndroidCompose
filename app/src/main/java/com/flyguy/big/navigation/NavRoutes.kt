package com.flyguy.big.navigation

/**
 * 集中管理路由常量
 */
object NavRoutes {
    const val LOGIN = "login" // 登录页
    const val MAIN = "main"   // 主页容器

    // 底部导航五个模块
    const val MODULE1 = "module1"
    const val MODULE2 = "module2"
    const val MODULE3 = "module3"
    const val MODULE4 = "module4"
    const val MODULE5 = "module5"

    // 详情页
    const val DEVICE_DETAIL = "device_detail/{deviceId}"

    // 辅助函数
    fun deviceDetail(deviceId: Long) = "device_detail/$deviceId"
}
