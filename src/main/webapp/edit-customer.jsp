<%@ page import="com.example.cafewebapp.DAO.AccessDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Users" %>
<%@ page import="com.example.cafewebapp.DAO.CustomerDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Customers" %><%--
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
            <h5>Edit Customer</h5>
          </div>
          <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
          </c:if>
          <%
            String id = request.getParameter("id");
            CustomerDAO customerDAO = new CustomerDAO();
            Customers customer = customerDAO.getCustomerById(Integer.parseInt(id));
            session.setAttribute("customer",customer);
            session.setAttribute("id",customer.getId());
          %>
          <form action="edit-customer" method="post">
            <div class="form-group">
              <label>Enter Name</label> <input type="text"
                                               value="<%=customer.getName()%>"
                                               pattern="[a-zA-Z]+" required
                                               class="form-control" id="InputName" aria-describedby="name" name="name" placeholder="John">
            </div>
            <div class="form-group">
              <label>Enter Contact Number</label> <input type="tel"
                                                         value="<%=customer.getContact()%>"
                                                         pattern="[0-9]{5} [0-9]{7}"
                                                         placeholder="99890 1223334"
                                                         required="required"
                                                         class="form-control" id="InputContact" name="phone">
            </div>
            <div class="form-group">
              <label>Enter Address</label> <input type="text"
                                                  value="<%=customer.getAddress()%>"
                                                  required="required"
                                                  class="form-control" id="InputAddress" aria-describedby="Address" name="address">
            </div>
            <div class="form-group">
              <label>Enter Username</label> <input type="text"
                                                   value="<%=customer.getUser_id().getUsername()%>"
                                                   pattern="[a-zA-Z0-9]+" required
                                                   class="form-control" id="InputUsername" name="username" placeholder="SomeOne12">
            </div>
            <div class="form-group">
              <label>Enter Password</label> <input type="password"
                                                   value="<%=customer.getUser_id().getPassword()%>"
                                                   required="required"
                                                   class="form-control" id="InputPassword" name="password">
            </div>
            <div class="form-group">
              <label>Bonus</label> <input type="number"
                                          step=".01"
                                          required="required"
                                          class="form-control" id="InputBonus" aria-describedby="bonus"
                                          name="bonus" value="<%=customer.getBonus()%>">
            </div>
            <div class="form-group">
              <label>Balance</label> <input type="number"
                                          step=".01"
                                          required="required"
                                          class="form-control" id="InputBalance" aria-describedby="balance"
                                          name="balance" value="<%=customer.getBalance()%>">
            </div>
            <div class="form-group">
              <label>Active</label>
              <select name="active">
                <c:if test="${customer.active eq true}">
                  <option value="true" selected>True</option>
                  <option value="false">False</option>
                </c:if>
                <c:if test="${customer.active eq false}">
                  <option value="true" >True</option>
                  <option value="false" selected>False</option>
                </c:if>
              </select>
            </div>

            <div class="form-group">
              <label>User type</label>
              <select name="user_type">
                <c:if test="${customer.user_id.user_type eq 'user'}">
                  <option value="user" selected>User</option>
                  <option value="admin">Admin</option>
                </c:if>
                <c:if test="${customer.user_id.user_type eq 'admin'}">
                  <option value="user" >User</option>
                  <option value="admin" selected>Admin</option>
                </c:if>
              </select>
            </div>

            <div class="form-group">
              <label>Banned</label>
              <select name="banned">
                <c:if test="${customer.banned eq true}">
                  <option value="true" selected>true</option>
                  <option value="false">false</option>
                </c:if>
                <c:if test="${customer.banned eq false}">
                  <option value="true" >true</option>
                  <option value="false" selected>false</option>
                </c:if>
              </select>
            </div>

            <button type="submit" class="btn btn-primary badge-pill btn-block">Update</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>


</body>
</html>
