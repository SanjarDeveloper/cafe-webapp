<%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 22.06.2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <%@include file="all_components/all_css.jsp"%>
</head>
<body style="background-color:#f0f1f2;">
<%@include file="all_components/navbar.jsp"%>
<div class="container-fluid">
  <div class="row p-4">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <div class="text-center">
            <i class="fa fa-user-plus fa-2x" aria-hidden="true"></i>
            <h5>Registration</h5>
          </div>
          <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
          </c:if>
          <form action="register" method="post">
            <div class="form-group">
              <label>Enter Name</label> <input type="text"
                                                required="required"
                                                class="form-control" id="InputName" aria-describedby="name" name="name">
            </div>
            <div class="form-group">
              <label>Enter Contact Number</label> <input type="tel" pattern="[0-9]{5} [0-9]{7}"
                                                         placeholder="99890 1223334"
                                                        required="required"
                                                         class="form-control" id="InputContact" name="phone">
            </div>
            <div class="form-group">
              <label>Enter Address</label> <input type="text"
                                                        required="required"
                                                        class="form-control" id="InputAddress" aria-describedby="Address" name="address">
            </div>
            <div class="form-group">
              <label>Enter Username</label> <input type="text"
                                                        required="required"
                                                        class="form-control" id="InputUsername" name="username">
            </div>
            <div class="form-group">
              <label>Enter Password</label> <input type="password"
                                                   required="required"
                                                   class="form-control" id="InputPassword" name="password">
            </div>

            <button type="submit" class="btn btn-primary badge-pill btn-block">Register</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%@include file="all_components/footer.jsp"%>
</body>
</html>
