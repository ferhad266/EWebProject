<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/5/2020
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $('#dobIdU').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>
<div class="lblDesign">Name:</div>
<input type="text" id="nameIdU" value="${worker.name}"><br>
<div class="lblDesign">Surname:</div>
<input type="text" id="surnameIdU" value="${worker.surname}"><br>
<div class="lblDesign">Birthday:</div>
<input type="text" id="dobIdU" value="${worker.dob}">
<div class="lblDesign">Father name:</div>
<input type="text" id="fatherNameIdU" value="${worker.fatherName}">
<div class="lblDesign">Phone:</div>
<input type="text" id="phoneIdU" value="${worker.phone}">
<div class="lblDesign">Email:</div>
<input type="text" id="emailIdU" value="${worker.email}">