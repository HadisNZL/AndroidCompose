# Android Compose MVVM 项目

这是一个基于 Jetpack Compose 和 MVVM 架构的现代化 Android 应用项目，展示了企业级 Android 开发的最佳实践。

## ✨ 主要特性

- 🎨 **Jetpack Compose** - 使用声明式 UI 构建原生界面
- 🏗️ **MVVM 架构** - 清晰的架构分层，便于维护和测试
- 💉 **Hilt 依赖注入** - 管理应用依赖，提高代码可测试性
- 🌐 **Retrofit + OkHttp** - 网络请求和 API 集成
- 🧭 **Navigation Compose** - 类型安全的导航管理
- 🔐 **Token 认证系统** - 自动刷新 token 和统一认证管理
- 🎯 **多环境配置** - 支持开发、测试、生产环境切换

## 📦 技术栈

- **最低 SDK**: 24 (Android 7.0)
- **目标 SDK**: 36
- **Kotlin**: 现代化的 Android 开发语言
- **Jetpack Compose**: 声明式 UI 框架
- **Hilt**: 依赖注入框架
- **Retrofit**: RESTful API 客户端
- **OkHttp**: HTTP 客户端
- **Coroutines**: 协程处理异步任务
- **ViewModel & LiveData**: 生命周期感知的数据管理

## 🏛️ 项目架构

```
app/src/main/java/com/flyguy/big/
├── config/              # 配置管理
│   └── AppConfig.kt     # 环境配置
├── data/                # 数据层
│   ├── model/           # 数据模型
│   ├── network/         # 网络请求
│   │   ├── ApiService.kt
│   │   ├── AuthService.kt
│   │   ├── TokenAuthenticator.kt
│   │   └── TokenManager.kt
│   └── repository/      # 数据仓库
├── di/                  # 依赖注入
│   └── NetworkModule.kt
├── navigation/          # 导航管理
├── ui/                  # UI 层
│   ├── components/      # 可复用组件
│   ├── screens/         # 页面
│   └── theme/           # 主题配置
└── viewmodel/           # ViewModel 层
```

## 🚀 快速开始

### 前置要求

- Android Studio Hedgehog | 2023.1.1 或更高版本
- JDK 11 或更高版本
- Android SDK 36

### 构建项目

1. 克隆项目
```bash
git clone https://github.com/HadisNZL/AndroidCompose.git
cd AndroidCompose
```

2. 使用 Android Studio 打开项目

3. 同步 Gradle 依赖

4. 选择构建变体
   - `devDebug` - 开发环境
   - `qaDebug` - 测试环境
   - `prodDebug` - 生产环境

5. 运行项目

## 🔧 环境配置

项目支持三种环境配置，在 `AppConfig.kt` 中管理：

- **DEV**: 开发环境
- **QA**: 测试环境
- **PROD**: 生产环境

通过构建变体（Build Variants）切换不同环境。

## 📱 核心功能模块

### 认证系统
- 用户登录
- Token 自动刷新
- 认证拦截器
- 统一的认证状态管理

### 网络请求
- 基于 Retrofit 的 RESTful API 调用
- OkHttp 拦截器
- 统一的错误处理
- 请求日志记录

### UI 组件
- 登录页面
- 主页面（底部导航）
- 多个功能模块
- 设备详情页面
- 全局对话框管理

## 🎯 代码规范

- 遵循 Kotlin 官方编码规范
- 使用有意义的命名
- 适当的代码注释
- 单一职责原则
- 依赖注入优先

## 📝 待办事项

- [ ] 添加单元测试
- [ ] 添加 UI 测试
- [ ] 完善错误处理
- [ ] 添加数据持久化
- [ ] 优化性能
- [ ] 添加更多功能模块

## 🤝 贡献

欢迎提交 Issue 和 Pull Request！

## 📄 许可证

本项目仅用于学习和参考。

## 👨‍💻 作者

HadisNZL

---

**注意**: 使用本项目前请根据实际情况配置 API 地址和相关参数。