<%@ page import="java.util.List" %>
<%@ page import="hotel.model.Worker" %><%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 2/22/2020
  Time: 1:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>AdminPanel</title>
    <link rel="stylesheet" type="text/css" href="css/layout.css">
    <script type="text/javascript" src="js/main.js"></script>
</head>
<body>
<% List<Worker> workerList = (List<Worker>) request.getAttribute("workerList"); %>
<div id="container">
    <jsp:include page="./static/header.jsp"></jsp:include>
    <jsp:include page="./static/menu.jsp"></jsp:include>
    <div id="content">
        <table border="1" id="workerTableId">
            <thead>
            <tr>
                <th>#</th>
                <th>Name</th>
                <th>Surname</th>
                <th>Birthday</th>
                <th>Father Name</th>
                <th>Phone</th>
                <th>Email</th>
            </tr>
            </thead>
            <tbody>
            <% for (Worker worker : workerList) {%>
            <tr>
                <td><%= worker.getR() %>
                </td>
                <td><%= worker.getName()%>
                </td>
                <td><%= worker.getSurname()%>
                </td>
                <td><%= worker.getDob()%>
                </td>
                <td><%= worker.getFatherName()%>
                </td>
                <td><%= worker.getPhone()%>
                </td>
                <td><%= worker.getEmail()%>
                </td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <jsp:include page="./static/footer.jsp"></jsp:include>
</div>
</body>
</html>
