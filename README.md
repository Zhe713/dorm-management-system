# 高校宿舍管理系统

一个 Java Web 项目，Servlet + JSP + JDBC，MVC 分层，实现宿舍日常管理的基本功能。

技术栈：Java 8、Servlet、JSP、JDBC、MySQL、Tomcat。

## 功能特性

- ✅ 管理员登录鉴权
- ✅ 学生信息增删改查
- ✅ 宿舍信息管理（楼栋、房间号、床位）
- ✅ 给学生分配宿舍、办理退宿
- ✅ 分配/退宿时用事务保证宿舍剩余床位同步更新
- ✅ MVC 分层：model / dao / servlet / jsp 各管各的

## 快速开始

### 1. 环境准备

- JDK 8+
- MySQL 5.7 或 8.x
- Tomcat 9

### 2. 建库建表

执行 `sql/schema.sql`：

```bash
mysql -u root -p < sql/schema.sql
```

脚本会自动建 `dorm_db` 库和三张表，并插入默认管理员 `admin / 123456`。

### 3. 修改数据库连接

改 `src/main/java/com/example/dorm/util/DBUtil.java` 里的 URL、用户名、密码：

```java
private static final String URL = "jdbc:mysql://localhost:3306/dorm_db?useSSL=false&characterEncoding=utf8";
private static final String USER = "root";
private static final String PASSWORD = "你的密码";
```

### 4. 打包部署

```bash
mvn package
```

把生成的 war 包丢到 Tomcat 的 `webapps/` 目录，启动 Tomcat 后访问：

```
http://localhost:8080/dorm-management/login.jsp
```

用 `admin / 123456` 登录。

## 目录结构

```
dorm-management-system/
├── sql/schema.sql                        # 建库建表脚本
├── pom.xml
├── src/main/java/com/example/dorm/
│   ├── model/                            # Student、Dorm
│   ├── dao/                              # StudentDAO、DormDAO
│   ├── servlet/                          # LoginServlet、StudentServlet、DormServlet
│   └── util/DBUtil.java                  # 数据库连接工具
└── webapp/
    ├── login.jsp
    ├── student_list.jsp
    ├── dorm_list.jsp
    └── WEB-INF/web.xml
```

## 工作原理

1. 浏览器请求 login.jsp，输入账号密码提交到 `LoginServlet`
2. Servlet 调 DAO 查 admin 表，验证通过后写 session，跳到学生列表
3. 学生列表的增删改走 `StudentServlet`，调用 `StudentDAO` 操作数据库
4. 分配宿舍时，`DormDAO.assignStudent` 开事务：先改学生的 dorm_id，再把宿舍 occupied +1，两步要么都成要么都回滚
5. 退宿同理，学生 dorm_id 置空、occupied -1

## 常见问题

**Q: 中文乱码怎么办？**

确认 JDBC URL 里有 `characterEncoding=utf8`，JSP 页面头有 `contentType="text/html;charset=UTF-8"`。

**Q: 启动报 ClassNotFoundException？**

mysql-connector-java 没打进 war 包，检查 pom.xml 里的依赖 scope 是不是 provided。

**Q: 默认登录账号是什么？**

`admin / 123456`，建表脚本里自带的，登录后可以自己改。

## 更新日志

- v1.0.0 初版：登录、学生 CRUD、宿舍管理、分配/退宿事务

## 许可证

MIT
