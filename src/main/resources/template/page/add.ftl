<!DOCTYPE html><%@ page pageEncoding="UTF-8"%><%@ include file="/commons/inc.jsp"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>${model?uncap_first} add page</title>
</head>
<body>
<h1>ADD ${model}</h1>
<form action="${'$'}{ctx}/${model?lower_case}/create" method="post">
            <#assign keys = properties?keys>
            <#list keys as key>
            <input name="${key}" placeholder="${key?upper_case}"><br>
            </#list>
    <input type="submit" value="ADD">
</form>
</body>
</html>