package com.flyguy.big.navigation

/**
 * 底部导航项的封装
 */
sealed class BottomNavItem(
    val title: String, val route: String, val icon: String
) {
    object Module1 : BottomNavItem("首页", NavRoutes.MODULE1, "🏠")
    object Module2 : BottomNavItem("二页", NavRoutes.MODULE2, "❷")
    object Module3 : BottomNavItem("三页", NavRoutes.MODULE3, "❸")
    object Module4 : BottomNavItem("四页", NavRoutes.MODULE4, "❹")
    object Module5 : BottomNavItem("五页", NavRoutes.MODULE5, "❺")

    companion object {
        val items = listOf(Module1, Module2, Module3, Module4, Module5)
    }
}
