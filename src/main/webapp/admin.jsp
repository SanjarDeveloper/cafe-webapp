<%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 30.06.2023
  Time: 10:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Admin</title>
    <%@include file="all_components/all_css.jsp"%>
    <style type="text/css">
        .back-img {
            background: url("all_components/images/img-admin.png");
            height: 90vh;
            width: 100%;
            background-repeat: no-repeat;
            background-size: cover;
        }
    </style>
</head>
<body>
    <c:if test="${userobj.user_type ne 'admin'}" >
        <c:redirect url="login.jsp"></c:redirect>
    </c:if>
    <%@include file="all_components/navbar.jsp"%>
    <div class="container-fluid back-img">
        <div class="text-center">
            <h1 class="text-white p-4">Welcome Admin</h1>
        </div>
    </div>
</body>
</html>
