<!DOCTYPE html><%@ page pageEncoding="UTF-8"%><%@ include file="/commons/inc.jsp"%>
<html>
<head>
    <meta charset="UTF-8" />
    <title>${model?uncap_first} edit page</title>
</head>
<body>
<h1>EDIT ${model}</h1>
<form action="${'$'}{ctx}/${model?lower_case}/modify" method="post">
    <input type="hidden" name="id" value="${'$'}{sessionScope.${model?lower_case}.id}">
            <#assign keys = properties?keys>
            <#list keys as key>
                ${key?upper_case}: <input name="${key}" value="${'$'}{sessionScope.${model?lower_case}.${key}}"><br>
            </#list>
    <input type="submit" value="SAVE">
</form>
</body>
</html>