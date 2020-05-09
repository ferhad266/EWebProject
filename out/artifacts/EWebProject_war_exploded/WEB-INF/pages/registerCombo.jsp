<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/10/2020
  Time: 2:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<option value="0">Select Register</option>
<c:forEach items="${registerList}" var="rel">
    <option value="${rel.id}">${rel.name} ${rel.surname}</option>
</c:forEach>
