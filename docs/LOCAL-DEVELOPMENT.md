# 本地开发说明

## 项目用途
Q Demo 是一个基于 Android Jetpack Compose 的示例项目，用于演示 Compose UI、状态管理和 Material 组件，帮助新成员快速熟悉项目架构与开发流程。

## 开发环境要求
- JDK 17 或以上版本（以项目 Gradle 配置为准）
- Android SDK（以模块 compileSdk 为准，建议包含 API 34）
- Android Studio 最新稳定版
- 已配置 ANDROID_HOME 或通过 Android Studio 路径设置
- 运行应用需要 Android 设备或模拟器

## 构建命令
在项目根目录执行：

```bash
./gradlew assembleDebug
```

构建成功后，调试版 APK 位于 `app/build/outputs/apk/debug/`。

## 测试命令
运行单元测试：

```bash
./gradlew test
```

若需运行仪器测试，请连接设备或模拟器后执行：

```bash
./gradlew connectedAndroidTest
```

## 启动应用
### 方式一：Android Studio
1. 使用 Android Studio 打开项目并等待 Gradle 同步完成。
2. 在运行配置中选择 `app` 和目标设备。
3. 点击 Run（或 Shift+F10）启动应用。

### 方式二：命令行
确保设备或模拟器已连接后，在项目根目录执行：

```bash
./gradlew installDebug
```

安装完成后，在设备上点击应用图标即可启动，或使用以下命令直接拉起（请将 `your.application.id` 替换为实际 Application ID）：

```bash
adb shell am start -n your.application.id/.MainActivity
```

## 敏感信息警告
请勿在代码、文档或提交信息中提交 API Key、密码、Token、密钥等敏感信息。本地配置文件（如 `local.properties`）不应纳入版本控制。若误提交，请立即移除并轮换相关密钥。
