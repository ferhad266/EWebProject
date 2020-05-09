<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/9/2020
  Time: 2:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">

    $(function () {
        $('#workerCmbIdU').val('${register.worker.id}');
        $('#roomCmbIdU').val('${register.room.id}');

        $('#dobIdU, #checkInIdU, #checkOutIdU').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>
<div class="lblDesign">Name:</div>
<input type="text" id="nameIdU" value="${register.name}"><br>
<div class="lblDesign">Surname:</div>
<input type="text" id="surnameIdU" value="${register.surname}"><br>
<div class="lblDesign">Birthday:</div>
<input type="text" id="dobIdU" value="${register.dob}"><br>
<div class="lblDesign">Father name:</div>
<input type="text" id="fatherNameIdU" value="${register.fatherName}"><br>
<div class="lblDesign">Adult Count:</div>
<input type="text" id="adultCountIdU" value="${register.adultCount}"><br>
<div class="lblDesign">childCount:</div>
<input type="text" id="childCountIdU" value="${register.childCount}"><br>
<div class="lblDesign">Phone:</div>
<input type="text" id="phoneIdU" value="${register.phone}"><br>
<div class="lblDesign">Email:</div>
<input type="text" id="emailIdU" value="${register.email}"><br>
<div class="lblDesign">Check In:</div>
<input type="text" id="checkInIdU" value="${register.check_in}"><br>
<div class="lblDesign">Check Out:</div>
<input type="text" id="checkOutIdU" value="${register.check_out}"><br>
<div class="lblDesign">Worker</div>
<select id="workerCmbIdU">
    <option value="0">Select Worker</option>
    <c:forEach items="${workerList}" var="wl">
        <option value="${wl.id}">${wl.name} ${wl.surname}</option>
    </c:forEach>
</select><br>
<div class="lblDesign">Room</div>
<select id="roomCmbIdU">
    <option value="0">Select Room</option>
    <c:forEach items="${roomList}" var="rml">
        <option value="${rml.id}">${rml.roomNumber} ${rml.roomType}</option>
    </c:forEach>
</select>
