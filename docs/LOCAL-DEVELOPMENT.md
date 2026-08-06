# 本地开发说明

## 项目用途
Q Demo 是一个 Android 示例项目，用于展示项目结构与常用开发流程，帮助新成员快速上手。

## 环境要求
- JDK 17
- Android SDK（API 34）
- Android Studio（最新稳定版）
- 使用项目自带的 gradlew，无需单独安装 Gradle

首次同步依赖需要联网。

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
在项目根目录执行：

```bash
./gradlew test
```

## 启动应用
1. 使用 Android Studio 打开本仓库；
2. 等待 Gradle 同步完成；
3. 连接设备或启动模拟器；
4. 选择 `app` 运行配置，点击 Run 启动。

## 敏感信息保护
- 严禁提交 API Key、密码、Token 等敏感信息。
- 请使用本地配置文件（如 `local.properties`）或环境变量管理密钥，并确保被 `.gitignore` 忽略。
- 提交前检查代码与配置，避免敏感信息泄漏。
