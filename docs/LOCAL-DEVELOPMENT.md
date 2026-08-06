# 本地开发说明

## 项目简介

CC 是一个基于 Android Compose 的 Android 项目，用于提供 [在此补充项目主要用途，例如：示例业务功能展示]。

## 环境要求

本地开发需安装以下工具，版本以仓库现有配置为准：

- **JDK**：项目使用 JDK 17（以 `build.gradle` / `gradle` 配置为准）。
- **Android SDK**：需安装 Android SDK Platform 和 Build Tools，版本参见 `app/build.gradle` 中的 `compileSdk` / `targetSdk`。
- **Gradle**：建议使用项目自带的 Gradle Wrapper（`./gradlew`），无需单独安装 Gradle。
- **Android Studio**：推荐使用最新稳定版，便于调试和运行模拟器。

## 构建项目

在项目根目录执行以下命令进行完整构建：

```bash
./gradlew assemble
```

如仅构建调试版本：

```bash
./gradlew assembleDebug
```

## 运行测试

执行单元测试：

```bash
./gradlew test
```

执行 Android 仪器测试（需连接模拟器或设备）：

```bash
./gradlew connectedAndroidTest
```

## 启动应用

1. 启动 Android 模拟器或连接已开启 USB 调试的真机。
2. 执行以下命令安装调试版应用：

```bash
./gradlew installDebug
```

3. 安装完成后，可在设备上点击应用图标启动，或使用 ADB 启动：

```bash
adb shell am start -n <package>/.MainActivity
```

> 说明：实际包名和 Activity 以 `app/build.gradle` 和代码为准。

## 敏感信息保护

**重要警告**：开发过程中，严禁将任何 API Key、密码、Token、密钥或其他敏感信息提交到版本库。

如项目需要本地配置敏感信息，建议采用以下方式（不纳入版本控制）：

- 使用 `local.properties` 文件（该文件默认不纳入 Git）存放本机 SDK 路径或临时配置。
- 使用环境变量注入运行时需要的密钥或 Token。
- 若涉及远端服务，请使用安全的密钥管理服务。

请务必检查 `git status`，避免误提交敏感文件。
