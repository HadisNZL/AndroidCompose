# 本地开发说明

## 项目用途

Q Demo 是一个用于演示 Android Compose 技术栈的示例项目，展示了 Jetpack Compose 的 UI 构建方式以及常见的单向数据流设计。本项目主要用于帮助新成员快速熟悉 Compose 相关开发流程，不承担核心业务逻辑。

## 环境要求

- JDK 11 或更高版本（推荐 17）
- Android SDK（建议包含 Android 13 / API 33 及以上平台）
- Android Studio 最新稳定版（推荐用于模拟器和调试）
- 若需使用命令行构建，请确认已配置 `ANDROID_HOME` 环境变量

## 构建项目

项目使用 Gradle Wrapper，统一使用以下命令构建：

```bash
./gradlew assembleDebug
```

如需构建 Release 包，可运行：

```bash
./gradlew assembleRelease
```

首次构建时会自动下载依赖，请保持网络畅通。

## 运行测试

执行单元测试：

```bash
./gradlew testDebugUnitTest
```

执行 Android 仪器测试（需连接模拟器或真机）：

```bash
./gradlew connectedDebugAndroidTest
```

## 启动应用

1. 使用 Android Studio 打开项目根目录。
2. 连接开启 USB 调试的 Android 设备，或启动已配置的模拟器。
3. 点击 Run 按钮，或使用命令行安装并启动：

```bash
./gradlew installDebug
adb shell am start -n com.example.qdemo/.MainActivity
```

`com.example.qdemo` 为示例包名，实际包名请以 `app/build.gradle` 中配置为准。

## 敏感信息保护规范

- 严禁将真实的 API Key、密码、Token、证书私钥等敏感信息提交到代码仓库。
- 开发时如需使用密钥，请通过本地环境变量或 `local.properties`（该文件不应被 Git 跟踪）注入，避免硬编码。
- 示例代码中如需占位，请使用明显无真实值的占位符，例如 `<YOUR_API_KEY>`。
- 提交前请检查文件内容，确保不包含真实敏感字符串。
- 若不慎提交了敏感信息，请立即轮换该密钥并联系仓库管理员处理。

## 注意事项

- 修改代码前请先阅读现有测试，保证变更不影响既有行为。
- 本项目仅作为演示，不向生产环境提供任何服务。
