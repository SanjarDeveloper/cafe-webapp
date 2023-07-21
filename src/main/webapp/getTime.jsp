<%@ page import="com.example.cafewebapp.DAO.OrderDAO" %>
<%@ page import="com.example.cafewebapp.DAO.CustomerDAO" %>
<%@ page import="com.example.cafewebapp.DAO.AccessDAO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.time.chrono.ChronoLocalDateTime" %>
<%@ page import="java.time.LocalTime" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 19.07.2023
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String time = request.getParameter("date");
    time = time.substring(0, 10) + " " + time.substring(11);
    session.setAttribute("takeaway-time",time);
    response.sendRedirect("menu.jsp");
%>
