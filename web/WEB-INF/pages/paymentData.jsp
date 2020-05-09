<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/11/2020
  Time: 9:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $('#paymentTableId').DataTable();
</script>
<table class="display" id="paymentTableId">
    <thead>
    <tr>
        <th>No</th>
        <th>Register</th>
        <th>Room</th>
        <th>Amount</th>
        <th>Pay Date</th>
        <th>Worker</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${paymentList}" var="pl">
        <tr>
            <td>${pl.id}</td>
            <td>${pl.register.name} ${pl.register.surname}</td>
            <td>${pl.room.roomNumber} ${pl.room.roomType}</td>
            <td>${pl.amount}</td>
            <td>${pl.payDate}</td>
            <td>${pl.worker.name} ${pl.worker.surname}</td>
            <td><a href="javascript: editPayment('${pl.id}');">Edit</a></td>
            <td><a href="javascript: deletePayment('${pl.id}');">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>