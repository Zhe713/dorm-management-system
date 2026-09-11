# 高校宿舍管理系统

一个 Java Web 项目，Servlet + JSP + JDBC，MVC 分层。

## 功能

- 管理员登录
- 学生信息的增删改查
- 宿舍信息管理
- 给学生分配宿舍、办理退宿
- 分配/退宿时用事务保证宿舍剩余床位同步更新

## 数据库

三张表：`admin`（管理员）、`student`（学生）、`dorm`（宿舍）。

建表脚本在 `sql/schema.sql`，先执行这个再跑项目。

## 怎么跑

1. 建库建表：执行 `sql/schema.sql`
2. 改 `src/main/java/com/example/dorm/util/DBUtil.java` 里的数据库连接信息
3. 打成 war 包部署到 Tomcat
