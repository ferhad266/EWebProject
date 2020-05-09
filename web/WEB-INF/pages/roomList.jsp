<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.List" %>
<%@ page import="hotel.model.Room" %><%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/4/2020
  Time: 5:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<% List<Room> roomList = (List<Room>) request.getAttribute("roomList"); %>--%>
<script type="text/javascript">
    $(function () {
        $('#roomTableId').dataTable();
    });
</script>
<table class="display" id="roomTableId">
    <thead>
    <tr>
        <th>#</th>
        <th>Room Number</th>
        <th>Room Situation</th>
        <th>Price</th>
        <th>Human Count</th>
        <th>Room Type</th>
        <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <%--    <% for (Room room : roomList) {%>--%>
    <c:forEach var="rol" items="${roomList}">
        <tr>
            <td>${rol.r}
            </td>
            <td>${rol.roomNumber}
            </td>
            <td>${rol.roomSituation}
            </td>
            <td>${rol.price}
            </td>
            <td>${rol.humanCount}
            </td>
            <td>${rol.roomType}
            </td>
            <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
                <td><a href="javascript: editRoom('${rol.id}');">Edit</a></td>
                <td><a href="javascript: deleteRoom('${rol.id}','${rol.roomNumber}');">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
    <%--    <%}%>--%>
    </tbody>
</table>