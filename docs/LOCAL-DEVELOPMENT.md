# 本地开发说明

## 项目简介
本项目（仓库名 Plan）是一个基于 Android Jetpack Compose 的客户端工程，使用 Kotlin 和 Gradle 构建，用于在 Android 设备上提供界面与功能。本文档说明本地开发环境的准备、构建、测试与启动方式。

## 环境要求
- JDK：17 或更高版本
- Android SDK：Android API 34（Android 14）及对应 Build-Tools
- Gradle：无需单独安装，使用仓库内 Gradle Wrapper（具体版本见 gradle/wrapper/gradle-wrapper.properties）
- Android Studio：推荐最新稳定版，并在 SDK Manager 中安装所需 SDK
- 若本机 SDK 路径未自动识别，请在 local.properties 中配置 sdk.dir（该文件不提交到版本库）

## 构建项目
在仓库根目录执行：
```bash
./gradlew assembleDebug
```
构建完成后，调试 APK 输出于 app/build/outputs/apk/debug/。

## 运行测试
执行全部单元测试：
```bash
./gradlew test
```
如需在设备或模拟器上运行 Android 仪器测试：
```bash
./gradlew connectedDebugAndroidTest
```

## 启动应用
- 方式一：使用 Android Studio 打开工程，连接设备或模拟器，选择 app 运行配置后点击 Run。
- 方式二：命令行构建并安装：
```bash
./gradlew installDebug
```
安装完成后，在设备或模拟器上点击应用图标即可启动。

## 敏感信息注意事项
- 严禁将真实密码、Token、API Key、签名密钥等敏感信息写入代码并提交。
- 本地敏感配置请放入 local.properties 或环境变量，并确保已被 .gitignore 忽略。
- 提交前检查 git status 与 git diff，避免误提交 local.properties、签名文件等文件。
