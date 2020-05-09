<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/15/2020
  Time: 1:19 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>View</title>
</head>
<body>
<div style="color: red;">${message}</div>
<form action="ds" method="post">
    <input type="hidden" value="${imagePath}" name="fileName" style="width: 100%"><br>
    <input type="submit" value="Download">
</form>
<img src="${imagePath2}" height="500" width="500">
</body>
</html>
