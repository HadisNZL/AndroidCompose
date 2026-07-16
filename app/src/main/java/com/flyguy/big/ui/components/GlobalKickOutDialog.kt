package com.flyguy.big.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import com.flyguy.big.R

/**
 * 生产级别：全局踢出登录对话框组件
 */
@Composable
fun GlobalKickOutDialog() {
    if (KickOutDialogManager.isShow) {
        AlertDialog(
            onDismissRequest = { 
                // 强制用户点击按钮，不允许通过点击外部区域消失
            },
            title = {
                Text(text = "登录提醒")
            },
            text = {
                Text(text = KickOutDialogManager.dialogMessage)
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        KickOutDialogManager.confirm()
                    }
                ) {
                    Text(
                        text = "去登录",
                        color = colorResource(id = R.color.themeColor)
                    )
                }
            }
        )
    }
}
