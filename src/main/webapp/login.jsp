<%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 22.06.2023
  Time: 15:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
    <%@include file="all_components/all_css.jsp"%>
</head>
<body style="background-color:#f0f1f2;">
<%@include file="all_components/navbar.jsp" %>
<div class="container-fluid">
    <div class="row p-5">
        <div class="col-md-4 offset-md-4">
            <div class="card">
                <div class="card-body">
                    <div class="text-center">
                        <i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
                        <h5>Login Page</h5>
                    </div>
                    <c:if test="${not empty succMsg }">
                        <h4 class="text-center text-danger">${succMsg}</h4>
                        <c:remove var="succMsg"/>
                    </c:if>
                  <form action="login" method="post">
                    <div class="form-group">
                      <label>Enter Email</label> <input type="email"
                                                        required="required"
                    class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="em">
                    </div>
                    <div class="form-group">
                      <label for="exampleInputPassword1">Enter Password</label> <input type="password"
                                                        required="required" type="password"
                                                        class="form-control" id="exampleInputPassword1" name="ps">
                    </div>
                    <button type="submit" class="btn btn-primary badge-pill btn-block">Login</button>
                  </form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="all_components/footer.jsp" %>
</body>
</html>
