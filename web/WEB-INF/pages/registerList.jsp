<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/9/2020
  Time: 12:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#registerTableId').dataTable();
    });
</script>
<table class="display" id="registerTableId">
    <thead>
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Surname</th>
        <th>Birthday</th>
        <th>Father Name</th>
        <th>Adult Count</th>
        <th>Child Count</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Check In</th>
        <th>Check Out</th>
        <th>Worker</th>
        <th>Room</th>
        <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${registerList}" var="rel">
        <tr>
            <td>${rel.r}</td>
            <td>${rel.name}</td>
            <td>${rel.surname}</td>
            <td>${rel.dob}</td>
            <td>${rel.fatherName}</td>
            <td>${rel.adultCount}</td>
            <td>${rel.childCount}</td>
            <td>${rel.phone}</td>
            <td>${rel.email}</td>
            <td>${rel.check_in}</td>
            <td>${rel.check_out}</td>
            <td>${rel.worker.name} ${rel.worker.surname}</td>
            <td>${rel.room.roomNumber} ${rel.room.roomType}</td>
            <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <td><a href="javascript: editRegister('${rel.id}');">Edit</a></td>
            <td><a href="javascript: deleteRegister('${rel.id}', '${register.name} ${register.surname}');">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
