<%--
  Created by IntelliJ IDEA.
  User: ferha
  Date: 3/12/2020
  Time: 5:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    session.removeAttribute("login");
    session.invalidate();
    response.sendRedirect("login.jsp");
%>