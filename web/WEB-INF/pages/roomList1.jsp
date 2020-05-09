<%@ page import="java.util.List" %>
<%@ page import="hotel.model.Room" %><%--
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
<% List<Room> roomList = (List<Room>) request.getAttribute("roomList"); %>
<div id="container">
    <jsp:include page="./static/header.jsp"></jsp:include>
    <jsp:include page="./static/menu.jsp"></jsp:include>
    <div id="content">
        <table border="1" id="roomTableId">
            <thead>
            <tr>
                <th>#</th>
                <th>Room Number</th>
                <th>Room Situation</th>
                <th>Price</th>
                <th>Human Count</th>
                <th>Room Type</th>
            </tr>
            </thead>
            <tbody>
            <% for (Room room : roomList) {%>
            <tr>
                <td><%= room.getId() %>
                </td>
                <td><%= room.getRoomNumber() %>
                </td>
                <td><%= room.getRoomSituation() %>
                </td>
                <td><%= room.getPrice() %>
                </td>
                <td><%= room.getHumanCount() %>
                </td>
                <td><%= room.getRoomType() %>
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
