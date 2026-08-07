# Plan 本地开发说明

## 项目用途
Plan 是一个基于 Kotlin 与 Jetpack Compose 的 Android 应用，提供简洁的移动端界面与交互。

## 环境要求
- JDK：17（若 Gradle 配置要求更高，以仓库中的 `gradle-wrapper.properties` 与构建文件为准）。
- Android SDK：建议安装 API 34 及以上平台，并确认 SDK 路径已配置到 `local.properties` 的 `sdk.dir` 或系统环境变量。
- Gradle：使用仓库 `gradle/wrapper/gradle-wrapper.properties` 中声明的 wrapper 版本，通过 `./gradlew` 自动下载。
- Android Studio：建议使用稳定版，用于模拟器与调试。
- 系统：macOS / Windows / Linux 均可，需满足 Android Studio 与 JDK 的安装要求。

## 构建项目
在项目根目录执行：
```bash
./gradlew build
```
首次构建会自动下载依赖，请保持网络通畅。

## 运行测试
在项目根目录执行：
```bash
./gradlew test
```
如需运行 Android 仪器测试，请先启动设备或模拟器，然后执行：
```bash
./gradlew connectedAndroidTest
```

## 启动应用
方式一：使用 Android Studio 打开项目根目录，等待 Gradle 同步完成后，选择设备或模拟器并点击 Run。

方式二：连接设备或模拟器后，在项目根目录执行：
```bash
./gradlew installDebug
```
安装完成后从应用列表启动应用。

## 敏感信息保护
开发过程中请勿将 API Key、密码、Token 等敏感信息写入文档或提交到仓库。建议将敏感配置放在本地文件（如 `local.properties` 或未跟踪的配置文件中），并确保已加入 `.gitignore`。
