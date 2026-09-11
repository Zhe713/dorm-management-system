<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dorm.model.Student" %>
<html>
<head>
    <title>学生列表</title>
</head>
<body>
    <h2>学生列表</h2>
    <p><a href="dorm?action=list">宿舍管理</a> | <a href="login.jsp">退出</a></p>
    <table border="1" cellpadding="5">
        <tr>
            <th>学号</th><th>姓名</th><th>性别</th><th>宿舍ID</th><th>操作</th>
        </tr>
        <%
            List<Student> list = (List<Student>) request.getAttribute("list");
            for (Student s : list) {
        %>
        <tr>
            <td><%= s.getSno() %></td>
            <td><%= s.getName() %></td>
            <td><%= s.getGender() %></td>
            <td><%= s.getDormId() == 0 ? "未分配" : s.getDormId() %></td>
            <td>
                <a href="student?action=delete&id=<%= s.getId() %>">删除</a>
            </td>
        </tr>
        <% } %>
    </table>

    <h3>添加学生</h3>
    <form action="student?action=add" method="post">
        学号：<input type="text" name="sno"/>
        姓名：<input type="text" name="name"/>
        性别：
        <select name="gender">
            <option value="男">男</option>
            <option value="女">女</option>
        </select>
        <input type="submit" value="添加"/>
    </form>
</body>
</html>
