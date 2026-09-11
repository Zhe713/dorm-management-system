# 高校宿舍管理系统

Java Web 课设，三个人一起做的一个小项目。我主要负责后端，写 Servlet 和 DAO 层。

技术栈：Java + Servlet + JSP + JDBC + MySQL + MVC。

## 功能

- 管理员登录
- 学生信息的增删改查
- 宿舍信息管理
- 给学生分配宿舍、办理退宿
- 分配/退宿时同步更新宿舍剩余床位

## 数据库

三张表：`admin`（管理员）、`student`（学生）、`dorm`（宿舍）。

建表脚本在 `sql/schema.sql`，先执行这个再跑项目。

## 怎么跑

1. 建库建表：执行 `sql/schema.sql`
2. 改 `src/main/java/com/example/dorm/util/DBUtil.java` 里的数据库连接信息
3. 用 Tomcat 跑起来（或者打成 war 包部署）

## 说明

这是大三的课设，写得比较基础，主要是把 MVC 分层、JDBC 操作数据库和 JSP 页面串起来练了一遍。
