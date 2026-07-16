package com.flyguy.big.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.flyguy.big.R

/**
 * 完全自定义高度、沉浸式适配、零依赖的通用标题栏
 */
@Composable
fun AppTopBar(
    title: String,
    modifier: Modifier = Modifier,
    height: Dp = 48.dp, // 默认提供 48dp 精简高度
    showBack: Boolean = true,
    onBackClick: () -> Unit = {},
    containerColor: Color = colorResource(id = R.color.white),
    contentColor: Color = Color.Black,
    actions: @Composable RowScope.() -> Unit = {}
) {
    Surface(
        color = containerColor, shadowElevation = 3.dp, modifier = modifier.fillMaxWidth()
    ) {
        Column {
            // 自动适配系统状态栏高度，实现沉浸式效果
            Spacer(Modifier.windowInsetsTopHeight(WindowInsets.statusBars))
            // 核心交互区域
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(height)
                    .padding(horizontal = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                // 居中标题：由于在 Box 居中，无论 height 是多少，文字都会垂直居中
                Text(
                    text = title,
                    color = contentColor,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis//文字末尾是省略号
                )

                // 左侧返回按钮
                if (showBack) {
                    IconButton(
                        onClick = onBackClick, modifier = Modifier.align(Alignment.CenterStart)
                    ) {
                        Text(
                            text = "<",
                            color = contentColor,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                // 右侧 Actions 区域
                Row(
                    modifier = Modifier.align(Alignment.CenterEnd),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    actions()
                }
            }
        }
    }
}
