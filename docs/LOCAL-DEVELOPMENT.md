# PlanQAI 本地开发说明

## 1. 项目说明
PlanQAI 是一个 Android 平台的任务规划应用，帮助用户管理日程与事项。项目基于 Kotlin 开发，使用 Gradle 构建。

## 2. 环境要求
- JDK：推荐 17，具体版本以项目 Gradle 配置为准
- Android Studio：最新稳定版
- Android SDK：建议包含 API 34 与 Build-Tools
- 运行环境：模拟器或已开启 USB 调试的 Android 真机

## 3. 构建项目
在项目根目录执行：
```bash
./gradlew clean assembleDebug
```
生成的 debug APK 位于 `app/build/outputs/apk/debug/`。

## 4. 运行测试
执行单元测试：
```bash
./gradlew testDebugUnitTest
```
如需运行全部检查，可执行：
```bash
./gradlew check
```

## 5. 启动应用
1. 使用 Android Studio 打开项目根目录。
2. 等待 Gradle 同步完成。
3. 连接设备或启动模拟器。
4. 选择 `app` 运行配置，点击 Run 启动应用。

命令行安装方式：
```bash
./gradlew installDebug
```
安装完成后在设备上手动打开应用即可。

## 6. 敏感信息声明
开发过程中不得将 API Key、密码、Token、密钥等敏感信息提交到仓库。请使用本地配置或环境变量管理，提交前检查 `git status`，确保没有敏感内容。
