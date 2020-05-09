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
        $('#dobId').datepicker({
            changeMonth: true,
            changeYear: true
        });
    });
</script>
<div class="lblDesign">Name:</div>
<input type="text" id="nameId"><br>
<div class="lblDesign">Surname:</div>
<input type="text" id="surnameId"><br>
<div class="lblDesign">Birthday:</div>
<input type="text" id="dobId">
<div class="lblDesign">Father name:</div>
<input type="text" id="fatherNameId">
<div class="lblDesign">Phone:</div>
<input type="text" id="phoneId">
<div class="lblDesign">Email:</div>
<input type="text" id="emailId">