<%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 09.07.2023
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<c:if test="${not empty errorMsg }">
  <h4 class="text-center text-danger">${errorMsg}</h4>
  <c:remove var="errorMsg"/>
</c:if>
</body>
</html>
