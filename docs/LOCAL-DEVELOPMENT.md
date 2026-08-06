# CC 项目本地开发说明

## 项目简介
CC 项目是一个基于 Android Compose 的 Android 应用，用于演示 Jetpack Compose 在真实业务场景下的开发实践，涵盖 UI 组件、状态管理、数据层集成等核心能力。

## 环境要求
- JDK：版本以仓库 `gradle/wrapper/gradle-wrapper.properties` 及构建配置为准。
- Android SDK：建议安装 API Level 34 和最新 Build Tools，具体以 `app/build.gradle` 配置为准。
- Gradle：无需单独安装，使用项目自带的 Gradle Wrapper（`./gradlew`）。

## 构建项目
在仓库根目录执行：

```bash
./gradlew assemble
```

构建产物位于 `app/build/outputs/apk/`。

## 运行测试
执行单元测试和仪器测试：

```bash
./gradlew test
```

若要测试指定模块，可运行 `./gradlew :app:testDebugUnitTest`。

## 启动应用
1. 连接 Android 模拟器或已开启 USB 调试的真机。
2. 安装应用：

```bash
./gradlew installDebug
```

3. 安装完成后，在设备上从应用列表找到 “CC” 应用并点击启动。

## 敏感信息保护
- **严禁提交** API Key、密码、Token、证书私钥等敏感信息到版本库。
- 推荐使用项目根目录下的 `local.properties`（该文件不纳入版本管理）或本地环境变量存储敏感配置。
- 代码提交前请检查是否误包含敏感内容，可使用 `git diff` 复查变更。
