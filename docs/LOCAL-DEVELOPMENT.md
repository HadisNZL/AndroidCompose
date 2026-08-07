# 本地开发说明

## 项目简介

本项目是基于 Jetpack Compose 的 Android 应用示例，用于演示现代 Android 开发技术栈与工程实践。

## 环境要求

- JDK 17 或更高版本
- Android SDK（建议通过 Android Studio 安装）
- Gradle（推荐使用项目自带的 Gradle Wrapper）
- Android Studio（推荐，非必需）

除上述基础工具外，项目无额外依赖。

## 构建项目

在项目根目录执行：

```bash
./gradlew assembleDebug
```

Windows 下使用：

```bash
gradlew.bat assembleDebug
```

## 运行测试

执行单元测试：

```bash
./gradlew test
```

如需运行仪器测试（需已连接设备或模拟器）：

```bash
./gradlew connectedAndroidTest
```

## 启动应用

### 使用 Android Studio

打开项目，选择 `app` 运行配置，点击 Run 按钮。

### 命令行方式

确保已连接设备或模拟器，先构建并安装：

```bash
./gradlew installDebug
```

然后启动应用（请将 `<applicationId>` 替换为实际包名）：

```bash
adb shell am start -n <applicationId>/.MainActivity
```

> 注意：`applicationId` 与主 Activity 名称请以项目实际配置为准。

## 敏感信息提示

开发时请勿提交 API Key、密码、Token 或其他敏感信息。建议将本地配置放入单独文件，并确保已被 `.gitignore` 忽略。
