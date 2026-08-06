# Q Demo 本地开发说明

本文档介绍如何在本地环境准备、构建、测试和启动 Q Demo 项目。请在开发和提交代码前阅读最后一节“敏感信息注意事项”。

## 项目简介

Q Demo 是用于演示 Q 核心能力与交互流程的本地项目。项目使用 Gradle 作为构建工具，并通过 Gradle Wrapper 统一构建命令，团队成员无需手动安装指定版本的 Gradle。

## 环境要求

- JDK：安装项目构建配置要求的 JDK 版本（建议 JDK 11 或 17，以 `build.gradle` 或 `gradle` 配置为准）。
- Android SDK：如果本地需要构建或运行 Android 相关模块，请配置 `ANDROID_HOME`，或通过 Android Studio 安装所需 SDK。
- 操作系统：Windows、macOS、Linux 均可。
- 无需单独安装 Gradle：本仓库使用 Gradle Wrapper。

## 构建项目

在项目根目录执行：

```bash
./gradlew build
```

该命令会完成依赖解析、编译、测试和打包。首次执行可能较慢，请保持网络畅通。

Windows 用户可使用：

```bat
gradlew.bat build
```

## 运行测试

执行全部单元测试：

```bash
./gradlew test
```

如果需要查看测试报告，可在构建完成后打开 `build/reports/tests/test` 下的 HTML 页面（具体路径可能随模块结构变化）。

## 启动应用

- 如果项目配置了 Gradle `application` 插件，可在项目根目录执行：

  ```bash
  ./gradlew run
  ```

- 如果是 Android 应用，请先连接设备或启动模拟器，然后执行：

  ```bash
  ./gradlew installDebug
  ```

  安装完成后，在设备上点击应用图标启动；也可以在 Android Studio 中选择对应 `app` 运行配置启动。

- 其他类型项目请参考 README 或 IDE 运行配置中的入口类说明。

## 敏感信息注意事项

- 严禁将 API Key、密码、Token、私钥等敏感凭据提交到 Git 仓库。
- 敏感信息应通过环境变量或本地私有配置文件注入，并确保相关本地文件已被 `.gitignore` 忽略。
- 提交前请检查代码、日志和文档中是否包含真实凭据；一旦误提交，请立即撤销相关变更、轮换密钥并清理 Git 历史。

如无特殊说明，以上命令均在项目根目录下执行。若在 Windows 下使用非 `cmd` 终端，请根据终端语法调整命令行。
