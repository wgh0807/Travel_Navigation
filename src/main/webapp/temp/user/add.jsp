<!DOCTYPE html><%@ page pageEncoding="UTF-8"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>user add page</title>
</head>
<body>
<h1>ADD User</h1>
<form action="/user/create" method="post">
            <input name="name" placeholder="NAME"><br>
            <input name="password" placeholder="PASSWORD"><br>
            <input name="mail" placeholder="MAIL"><br>
    <input type="submit" value="ADD">
</form>
</body>
</html>