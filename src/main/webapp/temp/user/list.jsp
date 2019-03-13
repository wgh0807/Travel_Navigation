<!DOCTYPE html><%@ page pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>user list page</title>
    <script>
        function del() { return confirm('REMOVE?'); }
    </script>
</head>
<body>
<c:import url="add.jsp"/>
<hr/>
<h1>LIST User</h1>
<hr/>
<table border="1">
    <tr>
        <th>COUNT</th>
                <th>NAME</th>
                <th>PASSWORD</th>
                <th>MAIL</th>
        <th colspan="2">OPERATION</th>
    </tr>
    <%
        out.println(session.getAttribute("list"));
    %>
    <c:forEach var="user" items="${sessionScope.list}" varStatus="vs">
        <tr>
            <td>${vs.count}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
                <td>${user.mail}</td>
            <td><a href="/user/queryById/${user.id}">EDIT</a></td>
            <td><a class="delete" href="/user/remove/${user.id}" onclick="return del()">REMOVE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>