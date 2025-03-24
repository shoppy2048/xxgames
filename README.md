# XXGames.space - 游戏导航网站

这是一个游戏导航网站，按类别汇总各种在线游戏，用户可以通过点击游戏名称或Logo访问相应的游戏。

## 项目结构

- 后端：Java 8 + Spring Boot
- 前端：HTML + Tailwind CSS
- 数据库：MySQL

## 功能特点

- 游戏分类展示
- 游戏搜索功能
- 响应式设计，适配各种设备
- SEO优化，提高网站在搜索引擎中的排名

## 页面说明

### 首页 (index.html)
- 顶部导航栏：网站logo、搜索框、主要游戏分类链接
- 热门游戏推荐区域：展示最受欢迎的游戏
- 游戏分类区域：按类别展示游戏缩略图和名称
- 页脚：版权信息、联系方式等

### 分类页面 (category.html)
- 显示特定类别下的所有游戏
- 支持分页浏览
- 提供排序选项（如按热度、最新等）

## 开发指南

### 环境要求
- JDK 8
- Maven 3.6+
- MySQL 5.7+

### 本地运行
1. 克隆项目到本地
2. 配置application.properties中的数据库连接
3. 运行`mvn spring-boot:run`启动项目
4. 访问`http://localhost:8080`查看网站

## 部署说明

网站将部署在域名xxgames.space上，使用以下步骤：
1. 构建项目：`mvn clean package`
2. 将生成的jar文件上传到服务器
3. 在服务器上运行：`java -jar xxgames-space-1.0.0.jar` 