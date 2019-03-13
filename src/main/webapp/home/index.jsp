<%--
  Created by IntelliJ IDEA.
  User: wangguanhua
  Date: 2019/3/13
  Time: 6:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    out.print(session.getAttribute("user"));
%>

</body>
</html>
