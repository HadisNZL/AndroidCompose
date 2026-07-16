package com.flyguy.big.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.flyguy.big.R
import com.flyguy.big.data.model.api.Device
import com.flyguy.big.navigation.BottomNavItem
import com.flyguy.big.navigation.NavRoutes
import com.flyguy.big.ui.components.AppTopBar
import com.flyguy.big.ui.components.GlobalKickOutDialog
import com.flyguy.big.viewmodel.BaseEffect

/**
 * 主屏幕：管理底部导航
 */
@Composable
fun MainScreen(
    onNavigateToDetail: (Device) -> Unit,
    onGlobalNavigate: (BaseEffect.Navigate) -> Unit // 统一改名为全局导航处理器
) {
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val currentTitle = when (currentDestination?.route) {
        NavRoutes.MODULE1 -> BottomNavItem.Module1.title
        NavRoutes.MODULE2 -> BottomNavItem.Module2.title
        NavRoutes.MODULE3 -> BottomNavItem.Module3.title
        NavRoutes.MODULE4 -> BottomNavItem.Module4.title
        NavRoutes.MODULE5 -> BottomNavItem.Module5.title
        else -> "FlyGuy"
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            AppTopBar(title = currentTitle, showBack = false)
        },
        bottomBar = {
            GlobalKickOutDialog()

            NavigationBar(containerColor = Color.White, tonalElevation = 8.dp) {
                BottomNavItem.items.forEach { item ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == item.route } == true
                    
                    NavigationBarItem(
                        icon = { Text(text = item.icon, fontSize = 20.sp) },
                        label = {
                            Text(
                                text = item.title,
                                color = if (isSelected) colorResource(R.color.teal_700) else Color.Gray
                            )
                        },
                        selected = isSelected,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = Color.Transparent,
                            selectedIconColor = colorResource(R.color.teal_700),
                            unselectedIconColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = bottomNavController,
            startDestination = NavRoutes.MODULE1,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(NavRoutes.MODULE1) {
                Module1Screen(
                    onNavigate = onGlobalNavigate,
                    onDeviceClick = onNavigateToDetail
                )
            }
            composable(NavRoutes.MODULE2) { Module2Screen() }
            composable(NavRoutes.MODULE3) { Module3Screen() }
            composable(NavRoutes.MODULE4) { Module4Screen() }
            composable(NavRoutes.MODULE5) {
                Module5Screen(onLogout = onGlobalNavigate)
            }
        }
    }
}
