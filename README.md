<h1 align="center">星海智伴 · 智慧养老护理平台</h1>

<p align="center">
  基于 Spring Boot 3 + Vue3 前后端分离的智慧养老护理管理系统，集成 AI 智能问答 / RAG 知识库与物联网设备接入
</p>

<p align="center">
  <img src="https://img.shields.io/badge/JDK-17-blue.svg">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.0-green.svg">
  <img src="https://img.shields.io/badge/Spring%20AI-1.1.2-green.svg">
  <img src="https://img.shields.io/badge/Vue-3.4-brightgreen.svg">
  <img src="https://img.shields.io/badge/license-MIT-blue.svg">
</p>

## 项目简介

星海智伴（xhzb）是一套面向养老机构的智慧护理管理平台，在 [RuoYi-Vue v3.9.0](https://gitee.com/y_project/RuoYi-Vue) 快速开发框架基础上二次开发，覆盖「机构运营 + 护理业务 + 智能设备 + AI 服务」四大板块：

- **机构运营**：楼层 / 房型 / 房间 / 床位管理、老人档案、入住与合同管理、家属端预约
- **护理业务**：护理等级、护理计划、护理项目、护理任务排班与执行、健康评估
- **智能设备**：智能床位与 IoT 设备接入（华为云 IoTDA）、设备数据采集、告警规则与告警数据
- **AI 服务**：基于 Spring AI 的智能问答对话、RAG 知识库（向量检索）、聊天记录管理
- **平台能力**：若依内置的权限、菜单、字典、日志、定时任务、代码生成等通用能力

## 技术栈

| 层次 | 技术 |
| - | - |
| 后端核心 | JDK 17、Spring Boot 3.5.0、Spring Security、JWT |
| 数据层 | MySQL 8、MyBatis / MyBatis-Plus 3.5.7、Druid、Redis |
| AI 能力 | Spring AI 1.1.2（通义千问 DashScope / DeepSeek / Ollama）、Redis Stack 向量检索（RAG） |
| 物联网 | 华为云 IoTDA（AMQP 设备数据接入） |
| 存储 | 阿里云 OSS |
| 前端 | Vue 3.4、Element Plus、Vite 5、Pinia、Vue Router、ECharts |
| 部署 | Docker、Jenkins（Jenkinsfile） |

## 系统架构

```
xhzb
├── xhzb-admin              # 后端启动入口（XhzbApplication）+ 全局配置
├── xhzb-framework          # 框架核心（安全、拦截器、数据权限等）
├── xhzb-system             # 系统管理模块（用户/角色/菜单/字典/日志…）
├── xhzb-nursing-platform   # 核心业务模块（养老护理 + AI 问答 / RAG 知识库 + IoT 设备）
├── xhzb-oss                # 对象存储（阿里云 OSS）
├── xhzb-quartz             # 定时任务
├── xhzb-generator          # 代码生成器
├── xhzb-common             # 通用工具与公共组件
├── xhzb_ui                 # 前端工程（Vue3 + Element Plus + Vite）
├── sql                     # 数据库初始化脚本（xhzb.sql）
└── doc                     # 需求规格说明书
```

## 功能特性

### 若依平台内置能力

用户 / 部门 / 岗位 / 菜单 / 角色 / 字典 / 参数管理，通知公告，操作与登录日志，在线用户监控，定时任务，代码生成，系统接口文档（Swagger），服务 / 缓存监控等。

### 养老护理业务

- **机构管理**：楼层、房型、房间、床位的层级化管理
- **老人管理**：老人档案、入住 / 退住、合同管理
- **护理管理**：护理等级评估、护理计划、护理项目、护理任务排班与执行
- **健康管理**：健康评估、评估数据采集
- **家属端**：家庭成员、预约参观、房间预订、服务项目下单

### 智能设备与告警

- 智能床位 / 设备管理，设备数据上报与查询（支持华为云 IoTDA AMQP 接入）
- 告警规则配置、告警数据记录与处理

### AI 智能服务

- **智能问答**：基于 Spring AI 的多模型对话（通义千问 / DeepSeek / 本地 Ollama）
- **RAG 知识库**：文档向量化 + Redis 向量检索，支持知识库管理与智能检索问答
- **聊天记录**：会话历史的保存与查询

## 快速开始

### 环境要求

- JDK 17+、Maven 3.6+
- MySQL 8.x、Redis（RAG 功能需安装 RediSearch 模块，即 Redis Stack）
- Node.js 16+（前端）

### 1. 初始化数据库

创建数据库并导入初始化脚本：

```bash
mysql -u root -p -e "CREATE DATABASE xhzb DEFAULT CHARACTER SET utf8mb4;"
mysql -u root -p xhzb < sql/xhzb.sql
```

### 2. 修改配置

仓库中的配置文件已脱敏，占位符需替换为真实值（位置：`xhzb-admin/src/main/resources/`）：

| 配置项 | 文件 | 占位符 |
| - | - | - |
| MySQL 地址 / 账号密码 | `application-{dev,test,prod}.yml` | `localhost`、`your_mysql_password` |
| Redis 地址 / 密码 | `application-{dev,test,prod}.yml` | `localhost`、`your_redis_password` |
| 微信公众号 AppID / Secret | `application-dev.yml` | `your_wechat_appid`、`your_wechat_secret` |
| 华为云 IoTDA 密钥 | `application-dev.yml` | `your_huaweicloud_ak`、`your_huaweicloud_sk` 等 |
| Logstash 日志地址 | `logback-logstash.xml` | `your-logstash-host:5044` |

部分密钥通过环境变量注入，无需写入配置文件：

```powershell
# Windows PowerShell
$env:OPENAI_API_KEY = "你的 DashScope / OpenAI 兼容密钥"
$env:DEEPSEEK_API_KEY = "你的 DeepSeek 密钥"
$env:OSS_ACCESS_KEY_ID = "你的阿里云 OSS AK"
$env:OSS_ACCESS_KEY_SECRET = "你的阿里云 OSS SK"
```

### 3. 启动后端

IDE 中运行 `xhzb-admin` 模块的 `com.xhzb.XhzbApplication`，或：

```bash
mvn -pl xhzb-admin -am package -DskipTests
java -jar xhzb-admin/target/xhzb-admin.jar
```

验证启动成功：

```bash
curl http://localhost:8080/captchaImage
```

接口文档：http://localhost:8080/swagger-ui.html

### 4. 启动前端

```bash
cd xhzb_ui
npm install
npm run dev
```

访问 http://localhost:9001（默认端口，见 `xhzb_ui/vite.config.js`），默认账号 `admin / admin123`。

## 可选功能配置

| 功能 | 前置条件 |
| - | - |
| AI 对话 | 设置 `OPENAI_API_KEY` / `DEEPSEEK_API_KEY` 环境变量（默认走阿里 DashScope `qwen-max-latest`） |
| RAG 知识库 | Redis 需安装 RediSearch 模块（Redis Stack）；并将 `RedisVectorConfig` 中 `initializeSchema` 改为 `true` 以自动创建向量索引 |
| 文件上传 | 设置 `OSS_ACCESS_KEY_ID` / `OSS_ACCESS_KEY_SECRET`，并确认 `oss.bucketName`、`oss.endpoint` |
| 微信登录 / 推送 | 填写 `wechat.appid` / `wechat.secret` |
| 设备数据接入 | 填写 `huaweicloud` 段落（ak / sk / projectId / endpoint / AMQP accessKey / accessCode） |

> **上线前必改**：`application.yml` 中 `token.secret`（若依默认 JWT 密钥）需替换为随机长字符串；`ruoyi.profile` 上传目录需存在且可写。

## 多环境说明

`application.yml` 中 `spring.profiles.active` 可选 `dev` / `test` / `prod`：

| 简述 | 端口 |
| - | - |
| dev | 8080 |
| test | 8080 |
| prod | 9000 |

## 常见问题

| 现象 | 原因与解决 |
| - | - |
| `InvalidAccessKeyId` | OSS 密钥未配置（启动不会阻断，文件上传功能不可用） |
| `FT._LIST unknown command` | Redis 未安装 RediSearch 模块，请使用 Redis Stack |
| `Could not resolve placeholder` | 对应环境变量（AI / OSS 密钥）未设置 |
| `Communications link failure` | MySQL 地址 / 端口不通，检查 yml 与网络 |
| `Access denied for user` | MySQL 账号密码错误 |

## 致谢

- 本项目基于 [RuoYi-Vue](https://gitee.com/y_project/RuoYi-Vue) v3.9.0 二次开发，感谢若依开源社区。
- AI 能力基于 [Spring AI](https://spring.io/projects/spring-ai)。

## 许可证

本项目遵循 [MIT License](LICENSE) 开源协议。
