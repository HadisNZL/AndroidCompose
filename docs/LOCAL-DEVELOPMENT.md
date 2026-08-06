# 本地开发说明

本文档帮助新成员快速了解本项目（AndroidCompose）的用途、搭建本地环境，并完成构建、测试与启动应用。

## 项目简介

这是一个基于 Jetpack Compose 的 Android 示例应用，用于演示使用 Compose 构建界面和基础业务逻辑。

## 环境要求

- JDK：17 或更高版本（与项目 Gradle 配置一致）。
- Android SDK：通过 Android Studio 安装项目所需平台与构建工具。
- IDE：推荐使用 Android Studio 最新稳定版。
- 模拟器或真机：用于运行应用。

## 构建

在项目根目录执行：

```bash
./gradlew assembleDebug
```

或使用 Android Studio 菜单 `Build > Make Project`。

## 测试

运行单元测试：

```bash
./gradlew test
```

运行插桩测试（需连接设备或模拟器）：

```bash
./gradlew connectedAndroidTest
```

## 启动应用

1. 在 Android Studio 中创建或选择模拟器（AVD），或通过 USB 连接已开启开发者模式的真机。
2. 点击运行按钮，选择 `app` 模块并部署到设备。

也可使用命令行安装调试包：

```bash
./gradlew installDebug
```

## 敏感信息提醒

开发过程中严禁提交 API Key、密码、Token 等敏感信息。请在本地使用环境变量或 `local.properties`（该文件不应被提交）管理密钥，提交代码前检查是否包含敏感内容。
