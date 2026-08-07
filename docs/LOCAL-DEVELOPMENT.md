# 本地开发文档

## 项目简介
Plan 是一个基于 Android Jetpack Compose 构建的 Android 客户端应用，采用 Kotlin 语言开发。项目的具体业务用途请参阅仓库根目录 README 或产品文档。

## 本地开发环境要求
- JDK 17 或更高版本
- Android Studio（最新稳定版）
- Android SDK Platform 34（Android 14）
- Gradle 8.x（推荐直接使用仓库自带 Gradle Wrapper）
- 模拟器或真机用于运行调试

## 如何构建项目
在项目根目录执行：

```bash
./gradlew assembleDebug
```

如需构建发布版本：

```bash
./gradlew assembleRelease
```

## 如何运行测试
执行单元测试：

```bash
./gradlew test
```

执行仪器测试（需连接设备/模拟器）：

```bash
./gradlew connectedDebugAndroidTest
```

## 如何启动应用
命令行方式：连接设备或启动模拟器后执行：

```bash
./gradlew installDebug
```

Android Studio 方式：使用 Android Studio 打开项目，等待 Gradle 同步完成，选择 app 运行配置，点击 Run 即可安装并启动应用。

## 敏感信息提交注意事项
- 严禁将 API Key、密码、Token、签名证书等敏感信息提交到代码仓库。
- 本地敏感配置请放于 `local.properties` 或本地 `gradle.properties`，并确认已被 `.gitignore` 忽略。
- 提交前检查变更文件，确保不包含任何真实密钥或凭据。

---

本文档仅用于本地开发环境说明。