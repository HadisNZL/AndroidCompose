# 本地开发说明

## 项目简介
本仓库为基于 Jetpack Compose 的 Android 应用示例项目，用于演示声明式 UI 开发与 Android 现代开发工具链的使用。

## 环境要求
- JDK：17（或与 `gradle/libs.versions.toml` 及根 `build.gradle.kts` 中配置一致）
- Android SDK：API 34（或项目 `compileSdk` 要求版本）
- Android Gradle Plugin：以项目 `gradle/libs.versions.toml` 中声明版本为准
- Kotlin：以项目 `gradle/libs.versions.toml` 中声明版本为准
- 构建工具：推荐使用仓库自带的 Gradle Wrapper（`./gradlew`）

## 构建项目
在仓库根目录执行：
```bash
./gradlew assembleDebug
```
如需构建 release 版本，可执行 `./gradlew assembleRelease`（需提前配置签名）。

## 运行测试
执行所有单元测试：
```bash
./gradlew test
```
如需运行仪器化测试（需连接设备/模拟器）：
```bash
./gradlew connectedAndroidTest
```

## 启动应用
连接 Android 设备或启动模拟器后，执行：
```bash
./gradlew installDebug
```
安装完成后从应用列表打开应用。也可以在 Android Studio 中打开项目，直接点击 Run 运行。

## 敏感信息保护
- 不得将 API Key、密码、Token、密钥文件等敏感信息提交到 Git 仓库。
- 本地敏感配置应使用局部文件（如 `local.properties`，但该文件通常不提交）或环境变量管理。
- 提交代码前，请检查是否包含真实凭据，必要时使用占位符或移除。
