package com.flyguy.big.ui.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.flyguy.big.R
import com.flyguy.big.viewmodel.BaseEffect
import com.flyguy.big.viewmodel.LoginIntent
import com.flyguy.big.viewmodel.LoginViewModel

/**
 * 生产级别登录界面
 */
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigate: (BaseEffect.Navigate) -> Unit
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    
    // 1. 订阅全局效应流 (Effect Interceptor)
    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is BaseEffect.ShowToast -> {
                    Toast.makeText(context, effect.message, Toast.LENGTH_SHORT).show()
                }
                is BaseEffect.Navigate -> {
                    onNavigate(effect)
                }
                is BaseEffect.Back -> {
                    // 处理返回逻辑
                }
            }
        }
    }

    var loginName by remember { mutableStateOf("zilin.niu@net263.com") }
    var password by remember { mutableStateOf("a123456") }

    val m74Fault: M74MissingType = M74MissingType()

    val themeColor = colorResource(id = R.color.themeColor)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "欢迎登录",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = themeColor
        )
        
        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField(
            value = loginName,
            onValueChange = { loginName = it },
            label = { Text("账号") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = themeColor,
                focusedLabelColor = themeColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = themeColor,
                focusedLabelColor = themeColor
            )
        )

        Spacer(modifier = Modifier.height(32.dp))

        if (state.error != null) {
            Text(text = state.error!!, color = Color.Red, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(16.dp))
        }

        Button(
            onClick = { viewModel.dispatch(LoginIntent.Login(loginName, password)) },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            enabled = !state.isLoading,
            colors = ButtonDefaults.buttonColors(containerColor = themeColor)
        ) {
            if (state.isLoading) {
                CircularProgressIndicator(color = Color.White, modifier = Modifier.size(24.dp))
            } else {
                Text(text = "继续登录", fontSize = 18.sp, color = Color.White)
            }
        }
    }
}
