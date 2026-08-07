# 本地开发说明

## 项目简介
这是一个基于 Jetpack Compose 的现代 Android 应用项目。本文档协助新成员快速完成本地开发环境准备、构建、测试和应用启动。

## 环境要求
- JDK：项目使用 JDK 17+，请安装与 Gradle 兼容的 JDK。具体版本可参考 `gradle/wrapper/gradle-wrapper.properties` 和根目录 `build.gradle.kts`。
- Android SDK：需要安装 Android SDK。通过 `local.properties`（不提交）或环境变量 `ANDROID_HOME` 指向 SDK。SDK 平台版本以 `app/build.gradle.kts` 中 `compileSdk` 为准，请安装对应 Platform 和 Build-Tools。
- Gradle：无需本地安装，使用仓库中的 Gradle Wrapper（`./gradlew`）。Wrapper 版本定义在 `gradle/wrapper/gradle-wrapper.properties`。
- Android Studio：推荐使用最新稳定版，以便自动配置 SDK 与模拟器。

## 构建与测试
以下命令均在项目根目录执行。
- 构建：`./gradlew assemble`
- 测试：`./gradlew test`
- 更多检查：`./gradlew check`

## 启动应用
1. 使用 Android Studio 打开项目并等待 Gradle 同步完成。
2. 连接开启开发者选项与 USB 调试的 Android 设备，或启动模拟器。
3. 点击 Run 运行 `app` 模块；或在命令行执行：
   `./gradlew installDebug`

## 敏感信息与提交规范
- 禁止提交 API Key、密码、Token、私钥等敏感信息。
- 开发时若需密钥，请通过环境变量或本地 `local.properties` 注入，且该文件必须保持在 `.gitignore` 中。
- 提交前检查变更内容，避免误提交；发现敏感信息后立即移除并作废相关密钥。
- 本仓库所有文档与配置不含真实敏感信息。

## 常见问题
- 如果 `./gradlew` 提示权限不足，请先执行 `chmod +x gradlew`（仅 Unix/Linux）。
- 如果无法找到 SDK，请检查 `local.properties` 或 `ANDROID_HOME` 是否正确。
- 若依赖下载缓慢，可配置镜像源，但不要提交镜像地址中的凭据。
