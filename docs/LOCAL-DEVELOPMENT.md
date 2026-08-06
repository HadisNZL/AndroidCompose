# PlanA 本地开发说明

## 项目简介
PlanA 是一个基于 Gradle 构建的 Android 应用程序项目，用于提供核心业务功能并支持后续迭代。项目包含单元测试和本地调试运行入口，便于开发者快速验证改动。

## 环境要求
- JDK：17 或更高版本（推荐最新 LTS 稳定版）
- Gradle：使用项目自带的 Gradle Wrapper（`./gradlew`），无需全局安装
- Android SDK：需安装 Android SDK 并配置 `ANDROID_HOME` 环境变量
- 推荐 IDE：Android Studio 或 IntelliJ IDEA（最新稳定版）

## 构建项目
在项目根目录执行以下命令完成编译与构建：

```bash
./gradlew build
```

## 运行测试
执行以下命令运行全部单元测试：

```bash
./gradlew test
```

## 启动应用
连接 Android 设备或启动模拟器后，执行以下命令安装并启动调试版应用：

```bash
./gradlew installDebug
```

也可以在 Android Studio 中打开项目，选择目标设备后点击 Run 按钮启动。

## 敏感信息警告
严禁将 API Key、密码、Token、数据库连接串等敏感信息提交到版本库。请使用环境变量或本地配置文件（如 `local.properties`）保存，并确认 `.gitignore` 已忽略相关文件。
