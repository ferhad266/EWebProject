<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/5/2020
  Time: 5:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript">
    $(function () {
        $("#accordion").accordion({
            collapsible: true
        });

        $('.datePicker').datepicker({
            changeMonth: true,
            changeYear: true
        });

        $('#advRoomCmbId').change(function () {
           getRegisterListByRoomId($(this).val());
        });

        $('#paymentTableId').dataTable();

        $('#advSearchBtnId').click(function () {
           advancedSearchPaymentData();
        });
    });
</script>
<div id="accordion">
    <h3>Advanced Search</h3>
    <div>
        <select id="advRoomCmbId" class="cmbDesign">
            <option value="0">Select Room</option>
            <c:forEach items="${roomList}" var="rol">
                <option value="${rol.id}">${rol.roomNumber} ${rol.roomType}</option>
            </c:forEach>
        </select>&nbsp;
        <select id="advRegisterCmbId" class="cmbDesign">
            <option value="0">Select Register</option>
            <c:forEach items="${registerList}" var="rel">
                <option value="${rel.id}">${rel.name} ${rel.surname}</option>
            </c:forEach>
        </select><br>
<%--        <select id="advWorkerCmbId" class="cmbDesign">--%>
<%--            <option value="0">Select Worker</option>--%>
<%--            <c:forEach items="${workerList}" var="wl">--%>
<%--                <option value="${wl.id}">${wl.name} ${wl.surname}</option>--%>
<%--            </c:forEach>--%>
<%--        </select><br>--%>
        <input type="text" id="advMinAmountId" placeholder="Min Amount">&nbsp;
        <input type="text" id="advMaxAmountId" placeholder="Max Amount"><br>
        <input type="text" id="advBeginDateId" class="datePicker" placeholder="Begin Date"> &nbsp;
        <input type="text" id="advEndDateId" class="datePicker dateDesign" placeholder="End Date">
        <input type="button" id="advSearchBtnId" value="Search">
    </div>
</div>
<br>
<div id="paymentDivId">
<table class="display" id="paymentTableId">
    <thead>
    <tr>
        <th>No</th>
        <th>Register</th>
        <th>Room</th>
        <th>Amount</th>
        <th>Pay Date</th>
        <th>Worker</th>
        <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <th>Edit</th>
            <th>Delete</th>
        </c:if>
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
            <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <td><a href="javascript: editPayment('${pl.id}');">Edit</a></td>
            <td><a href="javascript: deletePayment('${pl.id}');">Delete</a></td>
            </c:if>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>