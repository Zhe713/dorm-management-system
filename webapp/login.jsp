<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
    <h2>宿舍管理系统 - 登录</h2>
    <% if (request.getAttribute("error") != null) { %>
        <p style="color:red"><%= request.getAttribute("error") %></p>
    <% } %>
    <form action="login" method="post">
        用户名：<input type="text" name="username"/><br/>
        密  码：<input type="password" name="password"/><br/>
        <input type="submit" value="登录"/>
    </form>
</body>
</html>
