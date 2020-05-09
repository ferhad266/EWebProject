<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="hotel.model.LoginUser" %><%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 2/22/2020
  Time: 1:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Project</title>
    <script type="text/javascript" src="js/jquery/jquery-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.layout-latest.js"></script>
    <script type="text/javascript" src="js/jquery/jquery.dataTables.min.js"></script>
    <script type="text/javascript" src="js/jquery/jquery-ui.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="css/main.css">
    <link rel="stylesheet" type="text/css" href="css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
    <%--    <script type="text/javascript">--%>
    <%--        history.pushState(null, null, "login.jsp");--%>
    <%--        window.addEventListener('popstate', function (event) {--%>
    <%--            history.pushState(null, null, "login.jsp");--%>
    <%--        });--%>
    <%--    </script>--%>
</head>
<body>
<%
    LoginUser loginUser = (LoginUser) request.getSession(false).getAttribute("login");
    if (loginUser == null) {
        response.sendRedirect("login.jsp");
    }
%>
<div class="ui-layout-north">
    <h1 style="text-align: center;">
        AdminPanel
    </h1>
    <div style="float: right;">Welcome, ${login.name} ${login.surname}
        <a href="logout.jsp"><img src="img/logogttt.png" height="50" width="50"></a></div>
</div>
<div class="ui-layout-center">

</div>
<div class="ui-layout-west">
    <c:choose>
        <c:when test="${login.role.roleName eq 'ROLE_WORKER'}">
            <input type="button" class="btnDesign" value="Room data" id="roomDataBtnId"><br>
            <input type="button" class="btnDesign" value="Payment data" id="paymentDataBtnId">
        </c:when>
        <c:when test="${login.role.roleName eq 'ROLE_ADMIN'}">
            <input type="button" class="btnDesign" value="Room data" id="roomDataBtnId"><br>
            <input type="button" class="btnDesign" value="Worker data" id="workerDataBtnId"><br>
            <input type="button" class="btnDesign" value="Customer data" id="customerDataBtnId"><br>
            <input type="button" class="btnDesign" value="register data" id="registerDataBtnId"><br>
            <input type="button" class="btnDesign" value="Payment data" id="paymentDataBtnId">
        </c:when>
        <c:otherwise>
            <input type="button" class="btnDesign" value="Worker data" id="workerDataBtnId"><br>
            <input type="button" class="btnDesign" value="Room data" id="roomDataBtnId"><br>
        </c:otherwise>
    </c:choose>

</div>
<div class="ui-layout-east">
    <c:if test="${login.role.roleName eq 'ROLE_ADMIN'}">
        <input type="button" value="New" class="btnDesign1" id="newBtnId">
    </c:if>
    <input type="text" id="keywordId" placeholder="Search" class="searchDesign">
    <input type="button" value="Search" id="searchBtnId">
</div>
<div class="ui-layout-south">
    <div style="text-align: center;">Copyright © Ferhad Musayev</div>
</div>
<div id="newWorkerDialogId"></div>
<div id="newRoomDialogId"></div>
<div id="newPaymentDialogId"></div>
<div id="newRegisterDialogId"></div>
<div id="editWorkerDialogId"></div>
<div id="editPaymentDialogId"></div>
<div id="editRoomDialogId"></div>
<div id="editRegisterDialogId"></div>
</body>
</html>