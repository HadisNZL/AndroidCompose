# Q Demo 本地开发说明

## 项目用途

Q Demo 是一个基于 Jetpack Compose 的 Android 示例项目，用于演示现代 Android UI 开发的基础用法，帮助新成员快速了解项目结构和常用依赖。

## 环境要求

- JDK 17
- Android Studio 最新稳定版
- Android SDK Platform 33 或 34
- 已配置 ANDROID_HOME 环境变量
- Gradle 使用项目内置 Wrapper，无需单独安装

## 构建项目

在项目根目录执行：

```bash
./gradlew assembleDebug
```

生成的调试 APK 位于 `app/build/outputs/apk/debug/` 目录下。

## 运行测试

运行所有单元测试：

```bash
./gradlew test
```

仅运行 app 模块调试版单元测试：

```bash
./gradlew testDebugUnitTest
```

## 启动应用

推荐使用 Android Studio 打开项目，选择 `app` 运行配置，连接设备或模拟器后点击 Run。

也支持命令行安装调试包：

```bash
./gradlew installDebug
```

安装后可通过 `adb shell` 或模拟器界面启动应用。

## 敏感信息警告

- 严禁在代码、文档、提交信息中包含 API Key、密码、Token 等敏感信息。
- 本地签名配置和密钥请放在 `local.properties` 或环境变量中，且该文件不应提交到版本控制。
- 若不慎提交敏感信息，请立即撤销相关提交并轮换密钥。
