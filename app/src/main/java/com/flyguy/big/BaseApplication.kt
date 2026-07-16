package com.flyguy.big

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * 生产级别应用入口
 * 启用 HiltAndroidApp，开启 Hilt 依赖注入编译时处理
 */
@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // 全局上下文初始化
        instance = this
    }

    companion object {
        lateinit var instance: BaseApplication
            private set
    }
}
