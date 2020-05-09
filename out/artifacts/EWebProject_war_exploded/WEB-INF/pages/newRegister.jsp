<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/9/2020
  Time: 1:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
       $('#dobId, #checkInId, #checkOutId').datepicker({
           changeMonth:true,
           changeYear:true
       }) ;
    });
</script>
<div class="lblDesign">Name:</div>
<input type="text" id="nameId"><br>
<div class="lblDesign">Surname:</div>
<input type="text" id="surnameId"><br>
<div class="lblDesign">Birthday:</div>
<input type="text" id="dobId"><br>
<div class="lblDesign">Father name:</div>
<input type="text" id="fatherNameId"><br>
<div class="lblDesign">Adult Count:</div>
<input type="text" id="adultCountId"><br>
<div class="lblDesign">childCount:</div>
<input type="text" id="childCountId"><br>
<div class="lblDesign">Phone:</div>
<input type="text" id="phoneId"><br>
<div class="lblDesign">Email:</div>
<input type="text" id="emailId"><br>
<div class="lblDesign">Check In:</div>
<input type="text" id="checkInId"><br>
<div class="lblDesign">Check Out:</div>
<input type="text" id="checkOutId"><br>
<div class="lblDesign">Worker</div>
<select id="workerCmbId">
    <option value="0">Select Worker</option>
    <c:forEach items="${workerList}" var="wl">
        <option value="${wl.id}">${wl.name} ${wl.surname}</option>
    </c:forEach>
</select><br>
<div class="lblDesign">Room</div>
<select id="roomCmbId">
    <option value="0">Select Room</option>
    <c:forEach items="${roomList}" var="rml">
        <option value="${rml.id}">${rml.roomNumber} ${rml.roomType}</option>
    </c:forEach>
</select>

