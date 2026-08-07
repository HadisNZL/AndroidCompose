# 本地开发说明

本文面向新成员，介绍本地开发环境搭建、构建、测试与启动应用的方法。

## 项目简介
本项目是一个基于 Kotlin 的 Android 客户端应用，使用 Gradle 作为构建工具。项目采用 Android 官方组件编写界面与逻辑，源码、构建脚本和本地开发文档均在当前仓库中。

## 环境要求
- JDK 17（如项目 Gradle 配置指定其他版本，请以配置为准）
- Android SDK（建议通过 Android Studio 安装对应 Platform）
- Android Studio（推荐，用于查看代码、运行调试和模拟器）
- Gradle Wrapper：项目自带 `./gradlew`，无需单独安装 Gradle
- 如对接后端服务，请提前准备服务地址及网络环境

## 构建项目
拉取代码并进入仓库根目录：

```bash
git clone <repository-url>
cd <project-directory>
```

执行 Debug 构建：

```bash
./gradlew assembleDebug
```

构建成功后，APK 默认输出在 `app/build/outputs/apk/debug/`。如要生成 Release 包，可运行 `./gradlew assembleRelease`（需配置签名）。

## 运行测试
运行单元测试：

```bash
./gradlew test
```

如需运行设备/模拟器上的仪器测试：

```bash
./gradlew connectedDebugAndroidTest
```

## 启动应用
确保设备或模拟器已连接，然后安装并启动 Debug 应用：

```bash
./gradlew installDebug
```

也可以使用 Android Studio 打开项目，点击 Run 按钮启动应用。

## 敏感信息安全说明
- 严禁在源码、文档、注释或日志中硬编码 API Key、密码、Token 等敏感信息。
- `local.properties`、密钥库文件、环境变量脚本等本地配置应加入 `.gitignore`，不得提交到版本库。
- 建议通过环境变量或本地构建配置提供敏感值，代码中通过 BuildConfig 或运行时配置读取。
- 如发现敏感信息被提交，应立即撤销并轮换相关凭据，同时清理 Git 历史。
