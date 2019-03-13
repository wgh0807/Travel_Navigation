<!DOCTYPE html><%@ page pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>user edit page</title>
</head>
<body>
<h1>EDIT User</h1>
<form action="../../user/modify" method="post">
    <input type="hidden" name="id" value="${sessionScope.user.id}">
                NAME: <input name="name" value="${sessionScope.user.name}"><br>
                PASSWORD: <input name="password" value="${sessionScope.user.password}"><br>
                MAIL: <input name="mail" value="${sessionScope.user.mail}"><br>
    <input type="submit" value="SAVE">
</form>
</body>
</html>