# 本地开发说明

## 项目简介

本项目是一个基于 Jetpack Compose 的 Android 应用，采用 Kotlin 编写，使用 Gradle 构建。本文档帮助新成员快速完成本地开发环境的准备、构建、测试与应用启动。

## 环境要求

- JDK 17 或更高版本
- Android SDK（推荐使用 Android Studio 最新稳定版自动配置）
- Android SDK Platform 34（或项目 `build.gradle` 中指定的版本）
- Android SDK Build-Tools（随 Android Studio 安装即可）

## 构建项目

在项目根目录执行以下命令进行完整构建（包括编译、资源处理和打包）：

```bash
./gradlew build
```

如需跳过测试快速编译，可使用：

```bash
./gradlew assembleDebug
```

## 运行测试

执行单元测试：

```bash
./gradlew test
```

执行 Android 仪器化测试（需连接模拟器或真机）：

```bash
./gradlew connectedAndroidTest
```

## 启动应用

### 使用命令行

连接 Android 模拟器或开启 USB 调试的真机后，安装并启动 debug 包：

```bash
./gradlew installDebug
```

安装完成后，在设备上点击应用图标即可启动。

### 使用 Android Studio

1. 使用 Android Studio 打开项目根目录。
2. 等待 Gradle 同步完成。
3. 选择需要运行的设备（模拟器或真机）。
4. 点击 Run 按钮，选择 `app` 模块，运行 debug 变体。

## 敏感信息保护

- 严禁在代码、配置文件、文档或提交信息中包含真实 API Key、密码、Token、密钥等敏感信息。
- 此类信息应通过本地环境变量或受保护的配置管理工具提供，并在 `.gitignore` 中忽略相关文件。
- 提交前请检查暂存内容，确保没有意外混入敏感数据。
- 若发现敏感信息被提交，请立即联系管理员轮换凭据，并联系仓库管理员清除历史记录。

---

更多构建命令可参见项目根目录的 Gradle 配置与模块说明。
