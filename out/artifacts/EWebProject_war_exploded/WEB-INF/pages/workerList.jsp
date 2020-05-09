<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="hotel.model.Worker" %><%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/4/2020
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% List<Worker> workerList = (List<Worker>) request.getAttribute("workerList"); %>--%>
<script type="text/javascript">
    $(function () {
        $('#workerTableId').dataTable();
    });
</script>
<table class="display" id="workerTableId">
    <thead>
    <tr>
        <th>#</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthday</th>
        <th>Father Name</th>
        <th>Phone</th>
        <th>Email</th>
        <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <%--    <% for (Worker worker : workerList) {%>--%>
    <c:forEach items="${workerList}" var="wl">
        <tr>
            <td>${wl.r}
            </td>
            <td>${wl.name}
            </td>
            <td>${wl.surname}
            </td>
            <td>${wl.dob}
            </td>
            <td>${wl.fatherName}
            </td>
            <td>${wl.phone}
            </td>
            <td>${wl.email}
            </td>
            <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
                <td><a href="javascript: editWorker('${wl.id}');">Edit</a></td>
                <td><a href="javascript: deleteWorker('${wl.id}','${wl.name} ${wl.surname}');">Delete</a></td>
            </c:if>

        </tr>
    </c:forEach>
    <%--    <%}%>--%>
    </tbody>
</table>
