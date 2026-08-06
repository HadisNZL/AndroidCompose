# 本地开发指南

## 项目简介
这是一个基于 Jetpack Compose 的 Android 项目，用于构建简洁的移动端界面与交互逻辑。本文将帮助新成员快速完成环境配置、构建、测试与应用启动。

## 环境要求

- JDK 17 或更高版本
- Android SDK（建议 API Level 34）
- Android 模拟器或已开启 USB 调试的真机
- Gradle（推荐使用项目自带的 Gradle Wrapper）

## 构建

执行以下命令进行完整构建：

```bash
./gradlew build
```

首次构建时需要下载项目依赖，耗时可能较长，请保持网络畅通。之后构建会使用本地缓存，速度会明显加快。

## 运行测试

运行单元测试：

```bash
./gradlew test
```

测试结果报告生成在 `build/reports/tests/` 目录下，可使用浏览器打开其中的 HTML 文件查看详细结果。

## 启动应用

连接设备或启动模拟器后，执行：

```bash
./gradlew installDebug
```

安装成功后，可在设备/模拟器上找到应用图标并点击启动，也可通过 ADB 命令启动主 Activity。

## 敏感信息保护

**严禁提交任何真实 API Key、密码、Token 等敏感信息。** 如需在配置中引用，必须使用占位符，例如：

```properties
API_KEY=YOUR_API_KEY
TOKEN=YOUR_TOKEN
```

请勿将个人凭据写入代码、配置文件或提交到版本库，避免泄露风险。
