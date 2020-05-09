<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/5/2020
  Time: 4:34 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#dateId').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>
<%--JSTL - JAVA SERVER TAG LIBRARY--%>
<div class="lblDesign">Worker</div>
<select id="workerCmbId">
    <option value="0">Select Worker</option>
    <c:forEach items="${workerList}" var="wl">
        <option value="${wl.id}">${wl.name} ${wl.surname}</option>
    </c:forEach>
</select><br>
<div class="lblDesign">Register</div>
<select id="registerCmbId">
    <option value="0">Select Register</option>
    <c:forEach items="${registerList}" var="rl">
        <option value="${rl.id}">${rl.name} ${rl.surname}</option>
    </c:forEach>
</select><br>
<div class="lblDesign">Room</div>
<select id="roomCmbId">
    <option value="0">Select Room</option>
    <c:forEach items="${roomList}" var="rml">
        <option value="${rml.id}">${rml.roomNumber} ${rml.roomType}</option>
    </c:forEach>
</select><br>
<div class="lblDesign">Amount</div>
<input type="text" id="amountId"><br>
<div class="lblDesign">Date</div>
<input type="text" id="dateId">


