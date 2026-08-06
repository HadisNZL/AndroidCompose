# PlanA 本地开发说明

## 项目简介
PlanA 是基于 Android 与 Jetpack Compose 的应用程序，用于展示 Compose UI 组件与核心业务流程。本文档面向首次参与本地开发的新成员，说明从环境准备到应用启动的完整步骤。

## 环境要求
- JDK 17 或更高版本。
- Android SDK：安装 platform-tools、build-tools 以及项目所需 Android 平台；可通过环境变量 `ANDROID_HOME` 或本地 `sdk.dir` 配置 SDK 路径。
- Gradle：无需单独安装，使用仓库内置的 Gradle Wrapper（`./gradlew`）即可。
- Android Studio（推荐）：使用最新稳定版进行运行与调试。
- 设备：Android 模拟器或已开启开发者模式的真机。

## 构建
在仓库根目录执行：

```bash
./gradlew assembleDebug
```

构建成功后的 APK 位于：`app/build/outputs/apk/debug/app-debug.apk`。

## 测试
- 单元测试：

```bash
./gradlew testDebugUnitTest
```

- 仪器测试（需连接模拟器或真机）：

```bash
./gradlew connectedDebugAndroidTest
```

## 启动应用
方式一（推荐）：使用 Android Studio 打开仓库，创建或选择 Android App 运行配置，连接设备后点击 Run。

方式二（命令行）：先安装应用：

```bash
./gradlew installDebug
```

安装完成后，在设备或模拟器的应用列表中点击 PlanA 图标启动。若安装失败并提示版本冲突，可先卸载旧版本再重试。

## 敏感信息警告
严禁将 API Key、密码、Token、私钥等敏感信息提交到 Git。敏感配置应通过环境变量或本地未跟踪文件（例如 `local.properties`）提供，并确保这些文件已被 `.gitignore` 忽略。提交代码前请检查 diff，确认不包含任何真实凭据。
