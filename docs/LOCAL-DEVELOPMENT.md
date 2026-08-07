# Plan-farm 本地开发说明

## 1. 项目用途
Plan-farm 是用于农业计划与任务管理的 Android 项目，帮助用户规划、跟踪农场日常计划与执行情况。

仓库地址：请使用团队提供的 Git 仓库地址，例如：`git clone <repository-url>`。

## 2. 本地环境要求
- JDK：17 或更高版本（推荐 17/21）。
- Android SDK：建议 API 34+，需安装 Platform-Tools。
- Gradle：无需单独安装，使用项目自带 Gradle Wrapper（`./gradlew`）。
- Android Studio（可选）：建议使用 2023.1 及以上版本。

## 3. 构建项目
在仓库根目录执行：
```bash
./gradlew assembleDebug
```
构建产物位置：
```
app/build/outputs/apk/debug/app-debug.apk
```

## 4. 运行测试与启动应用
运行单元测试：
```bash
./gradlew testDebugUnitTest
```

启动应用：
- 方式一：使用 Android Studio 打开项目，选择 `app` 模块后点击 Run。
- 方式二：连接已开启调试的设备/模拟器后执行：
```bash
./gradlew installDebug
```

## 5. 敏感信息保护约定
禁止向仓库提交 API Key、密码、Token 等敏感信息。本地敏感配置请使用 `local.properties` 或环境变量，并确保 `local.properties` 不纳入版本控制（在 `.gitignore` 中忽略）。提交前请检查暂存内容，避免泄露真实敏感数据。
