<%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 21.07.2023
  Time: 18:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String feedback = request.getParameter("feedback");
    String rating = request.getParameter("rating");
    if (feedback != null){
        session.setAttribute("feedback",feedback);
    }
    if (rating != null){
        session.setAttribute("rating",rating);
    }
    response.sendRedirect("order-details.jsp");
%>