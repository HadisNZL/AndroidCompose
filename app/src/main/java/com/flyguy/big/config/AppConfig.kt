package com.flyguy.big.config

import android.os.Build
import com.flyguy.big.BuildConfig
import java.util.UUID

/**
 * 环境类型
 */
enum class EnvType {
    DEV, QA, PROD
}

/**
 * 生产级别配置中心
 */
object AppConfig {

    val isDebug: Boolean = BuildConfig.DEBUG

    val currentEnv: EnvType = try {
        EnvType.valueOf(BuildConfig.ENV_NAME)
    } catch (e: Exception) {
        EnvType.PROD
    }

    val baseUrl: String = when (currentEnv) {
        EnvType.DEV -> "http://211.100.76.62/"
        EnvType.QA -> "https://test.italkdd.com/"
        EnvType.PROD -> "https://hsapi.italkdd.com/"
    }

    val authBaseUrl: String = when (currentEnv) {
        EnvType.DEV -> "http://211.100.76.62/"
        EnvType.QA -> "https://test-account.italkbb.com/"
        EnvType.PROD -> "https://webaccount.italkbb.com/"
    }

    // 对应 Raw 中的 X-App-Build
    const val appVersionName = "v5.1.4"
    const val appVersionCode = "10"
    val appBuildHeader = "$appVersionName($appVersionCode)"

    // 对应 Raw 中的 User-Agent 动态构建
    val userAgent = "Dalvik/2.1.0 (Linux; U; Android ${Build.VERSION.RELEASE}; ${Build.MODEL} Build/${Build.DISPLAY})"

    /**
     * 获取设备信息
     */
    val deviceInfo: String = "${Build.BRAND}${Build.MODEL}"

    /**
     * 获取设备 UUID
     */
    fun getDeviceUuid(): String {
        // 开发环境根据你之前的要求传空，其他环境生成
//        return if (currentEnv == EnvType.DEV) "" else UUID.randomUUID().toString()
        return ""
    }
}
