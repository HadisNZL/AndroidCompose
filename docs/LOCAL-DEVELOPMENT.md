# 本地开发说明

## 项目用途

AndroidCompose 是一个基于 Kotlin 和 Jetpack Compose 的 Android 示例项目，用于演示 Compose UI 开发，帮助开发者快速了解声明式 UI 的写法与工程结构。

## 本地开发环境要求

- JDK 17
- Android Studio 最新稳定版
- Android SDK，并安装项目所需的 API Level
- Gradle：建议使用项目自带的 Gradle Wrapper，无需单独安装

## 构建项目

在项目根目录执行以下命令：

- `./gradlew assemble`：构建调试与发布产物
- `./gradlew build`：执行完整构建（包含检查与打包）

## 运行测试

在项目根目录执行：

- `./gradlew test`：运行单元测试
- `./gradlew connectedAndroidTest`：运行设备/模拟器测试

## 启动应用

使用 Android Studio 打开项目，选择模拟器或真机后点击 Run 按钮即可运行应用。

也可以使用命令行安装调试包：

- `./gradlew installDebug`

## 敏感信息注意事项

开发时不得提交 API Key、密码、Token 等敏感信息。示例代码或配置中如涉及凭据，只能使用占位符（例如 `your_api_key_here`），并由开发者通过本地环境变量或安全配置注入。

提交代码前请再次检查是否误提交了任何真实密钥或凭据。
