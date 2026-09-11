<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.dorm.model.Dorm" %>
<html>
<head>
    <title>宿舍列表</title>
</head>
<body>
    <h2>宿舍列表</h2>
    <p><a href="student?action=list">学生管理</a></p>
    <table border="1" cellpadding="5">
        <tr>
            <th>楼栋</th><th>房间号</th><th>床位</th><th>已住</th>
        </tr>
        <%
            List<Dorm> list = (List<Dorm>) request.getAttribute("list");
            for (Dorm d : list) {
        %>
        <tr>
            <td><%= d.getBuilding() %></td>
            <td><%= d.getRoomNo() %></td>
            <td><%= d.getCapacity() %></td>
            <td><%= d.getOccupied() %></td>
        </tr>
        <% } %>
    </table>

    <h3>添加宿舍</h3>
    <form action="dorm?action=add" method="post">
        楼栋：<input type="text" name="building"/>
        房间号：<input type="text" name="roomNo"/>
        床位：<input type="text" name="capacity"/>
        <input type="submit" value="添加"/>
    </form>
</body>
</html>
