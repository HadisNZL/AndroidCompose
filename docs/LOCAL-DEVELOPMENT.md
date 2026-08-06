# 本地开发指南

## 项目简介

这是一个基于 Jetpack Compose 的 Android 示例应用，演示 Compose UI 与常规 Android 工程结构，适合作为 Compose 开发入门参考。

## 环境要求

- JDK：使用项目 Gradle 配置所要求的 JDK 版本（通常为 JDK 17）。
- Android SDK：安装 Android SDK，并配置 `ANDROID_HOME` 环境变量。
- Android Studio：建议使用最新稳定版，便于管理 SDK、Gradle 与模拟器。
- 若使用命令行，请确保 Java 环境变量已正确配置。

具体版本以项目根目录中的 Gradle 配置为准。

## 构建项目

使用 Android Studio：

1. 打开项目根目录。
2. 等待 Gradle 同步完成。
3. 点击 Build > Make Project。

命令行构建：

```bash
./gradlew assembleDebug
```

生成的 APK 位于 `app/build/outputs/apk/debug/`。

## 运行测试

运行所有单元测试：

```bash
./gradlew test
```

如需运行仪器测试，请先连接模拟器或真机：

```bash
./gradlew connectedAndroidTest
```

## 启动应用

1. 启动 Android 模拟器，或通过 USB 连接已开启开发者模式并授权的真机。
2. 使用 Android Studio 点击 Run 按钮运行应用。
3. 也可使用命令行安装到已连接的设备：

```bash
./gradlew installDebug
```

4. 在设备上找到应用图标并点击启动。

## 敏感信息提醒

开发过程中请勿在代码、配置或文档中提交 API Key、密码、Token 等敏感信息。请使用本地环境变量或未跟踪的配置文件管理机密信息，并确保 `.gitignore` 已忽略相关文件。
