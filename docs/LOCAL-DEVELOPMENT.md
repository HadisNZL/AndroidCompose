# 本地开发说明

## 1. 项目简介
本项目为 Android Compose 项目，用于客户端产品开发与迭代。本文档仅说明本地开发流程，不描述业务功能。

## 2. 环境要求
- JDK：建议安装 JDK，具体版本以项目 PRD 或 CI 配置为准。
- Android Studio：需安装 Android SDK、SDK Platform、Build Tools 等组件。
- 设备：Android 模拟器或真机（开启 USB 调试）。

## 3. 构建项目
在项目根目录执行：
```bash
./gradlew build
```
仅构建调试 APK：
```bash
./gradlew assembleDebug
```

## 4. 运行测试
执行单元测试：
```bash
./gradlew test
```
连接设备后执行仪器测试：
```bash
./gradlew connectedAndroidTest
```

## 5. 启动应用
连接模拟器或真机后执行：
```bash
./gradlew installDebug
```
随后在设备上打开应用；也可在 Android Studio 中选择 app 运行配置后点击 Run。

## 6. 安全注意事项
- 禁止提交 API Key、密码、Token 等敏感信息。
- 敏感配置使用环境变量或 local.properties 注入，并确认 local.properties 已被 .gitignore 忽略。
- 示例一律使用占位符：YOUR_API_KEY、YOUR_PASSWORD、YOUR_TOKEN。
- 提交前检查 diff，防止误提交密钥。
