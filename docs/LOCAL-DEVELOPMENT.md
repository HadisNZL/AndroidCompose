# Q Demo 本地开发说明

## 项目简介

Q Demo 是一个使用 Kotlin 和 Gradle 构建的 Android 示例应用，用于演示 Android 项目的本地构建、测试和运行流程。项目通过根目录下的 `./gradlew` 脚本统一执行构建任务。

## 环境要求

- JDK 17：本地需安装 JDK 17，并将 `JAVA_HOME` 指向该版本；项目 Gradle wrapper 与 Android Gradle Plugin 要求 JDK 17。
- Android SDK：通过 Android Studio SDK Manager 安装项目中 `compileSdk` 对应的 Platform、Build Tools 和 `platform-tools`，并设置 `ANDROID_HOME` 指向 SDK 目录。
- 运行设备（可选）：Android 7.0 及以上版本的模拟器或真机，用于安装运行调试版应用。
- 无需单独安装 Gradle，使用项目提供的 `./gradlew` wrapper 即可。

## 构建项目

在项目根目录执行：

```bash
./gradlew assembleDebug
```

如需执行完整构建（含单元测试、Lint 等）：

```bash
./gradlew build
```

## 运行测试

执行所有本地单元测试：

```bash
./gradlew test
```

如需在已连接设备或模拟器上运行仪器测试：

```bash
./gradlew connectedAndroidTest
```

## 启动应用

1. 启动 Android 模拟器，或使用 USB 连接已开启调试的真机。
2. 在项目根目录执行：

```bash
./gradlew installDebug
```

3. 安装完成后，在设备或模拟器上点击应用图标启动 Q Demo。

## 敏感信息注意事项

开发过程中不得将 API Key、密码、Token、签名文件、`local.properties` 等敏感信息提交到版本控制。提交前检查暂存内容，避免包含真实凭据；`local.properties` 应保持被 `.gitignore` 忽略。
