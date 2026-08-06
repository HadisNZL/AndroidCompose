# PLanA 本地开发说明

## 项目简介
PLanA 是一个使用 Kotlin 与 Jetpack Compose 开发的 Android 项目。仓库采用声明式 UI 和现代 Android 工程实践，便于团队协作与功能迭代。本文面向首次接触该仓库的开发者，帮助快速完成本地环境配置、构建、测试和启动。

## 本地环境要求
- JDK：需要安装 JDK，最低版本要求待确认后补充。
- Android SDK：需要安装 Android SDK，具体编译版本与最低支持版本待确认后补充。
- 推荐使用 Android Studio（版本不限，但需支持 Compose）。
- 项目使用 Gradle Wrapper，无需单独安装 Gradle；命令行构建时可确保 `ANDROID_HOME` 已配置，或通过 `local.properties` 指向 SDK 路径。

## 构建项目
在项目根目录执行以下命令构建 Debug 包：

```bash
./gradlew assembleDebug
```

首次构建需要下载 Gradle 依赖和 Android 依赖，耗时可能较长。构建成功后，APK 通常输出在 `app/build/outputs/apk/debug/` 目录。

## 运行测试
在项目根目录执行以下命令运行单元测试：

```bash
./gradlew test
```

测试结果报告通常输出在 `app/build/reports/tests/` 目录。如项目后续增加 instrumented 测试，可结合设备或模拟器单独运行。

## 启动应用
方式一：使用 Android Studio
1. 使用 Android Studio 打开项目根目录。
2. 等待 Gradle 同步完成。
3. 选择 `app` 的 debug 运行配置。
4. 选择模拟器或已连接的真机并点击 Run。

方式二：使用命令行
1. 连接已开启 USB 调试的 Android 设备或启动模拟器。
2. 在项目根目录执行：

```bash
./gradlew installDebug
```

安装完成后可在设备上启动应用。

## 敏感信息注意事项
- 严禁将 API Key、密码、Token、证书私钥等敏感信息提交到代码仓库。
- 不得在源码、构建脚本、文档或本地配置中写入真实密钥。
- 敏感配置应通过环境变量或本地配置文件提供；本项目当前未实现该机制，后续如有需要应单独设计并补充说明。
- 每次提交前请检查 diff，确保没有意外包含本地隐私或密钥信息。
