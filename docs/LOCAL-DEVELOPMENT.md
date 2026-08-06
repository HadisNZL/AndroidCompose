# PlanA 本地开发说明

## 项目用途
PlanA 是当前仓库中的 Android 应用项目，提供基础功能模块与统一工程化配置，帮助新成员快速开始本地开发。

## 环境要求
- JDK：以仓库 `gradle.properties` 或 `build.gradle` 中配置的版本为准。
- Android SDK：`compileSdk`、`minSdk` 以 `app/build.gradle` 配置为准，通过 Android Studio SDK Manager 安装对应平台。
- Gradle：无需手动安装，使用项目自带 Gradle Wrapper（`./gradlew`），Wrapper 版本见 `gradle/wrapper/gradle-wrapper.properties`。
- 建议使用最新稳定版 Android Studio。

## 构建
在项目根目录执行：

```bash
./gradlew assembleDebug
```

debug APK 输出在 `app/build/outputs/apk/debug/`。

## 测试
运行单元测试：

```bash
./gradlew test
```

运行仪器测试（需连接设备或模拟器）：

```bash
./gradlew connectedAndroidTest
```

## 启动应用
1. 用 Android Studio 打开仓库根目录，等待 Gradle Sync 完成。
2. 连接 Android 设备或启动模拟器。
3. 直接点击 Run 运行 `app` 配置；或使用命令行：

```bash
./gradlew installDebug
adb shell am start -n <应用ID>/<入口Activity>
```

`应用ID` 和入口 Activity 以 `app/build.gradle` 与 `AndroidManifest.xml` 中的实际配置为准。

## 敏感信息提醒
- 严禁提交 API Key、密码、Token、签名密钥等敏感信息。
- 开发环境配置请放入 `local.properties`（已在 `.gitignore` 中忽略）或通过环境变量注入。
- 提交前请检查暂存内容中是否包含真实凭据。
