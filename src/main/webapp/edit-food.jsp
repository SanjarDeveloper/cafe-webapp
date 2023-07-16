<%@ page import="com.example.cafewebapp.DAO.AccessDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Users" %>
<%@ page import="com.example.cafewebapp.Entity.Foods" %>
<%@ page import="com.example.cafewebapp.DAO.FoodsDAO" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 22.06.2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Edit food</title>
  <%@include file="all_components/all_css.jsp" %>
</head>
<body style="background-color:#f0f1f2;">
<%@include file="all_components/navbar.jsp" %>
<div class="container-fluid">
  <div class="row p-4">
    <div class="col-md-4 offset-md-4">
      <div class="card">
        <div class="card-body">
          <div class="text-center">
            <h5>Edit Food</h5>
          </div>
          <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
          </c:if>
          <%
            FoodsDAO foodsDAO = new FoodsDAO();
            String id = request.getParameter("id");
            Foods food = foodsDAO.getFoodById(Integer.parseInt(id));
            session.setAttribute("food",food);
            session.setAttribute("id",id);
          %>
          <form action="edit-food" method="post">
            <div class="form-group">
              <label>Food Name</label> <input type="text"
                                              pattern="[a-zA-Z]+" required
                                              class="form-control" id="InputName" aria-describedby="name"
                                              name="name" value="<%=food.getName()%>">
            </div>
            <div class="form-group">
              <label>Food Details</label> <input type="text"
                                                 required="required"
                                                 class="form-control" id="InputDetails" aria-describedby="details"
                                                 name="details" placeholder="info about food" value="<%=food.getDetails()%>">
            </div>
            <div class="form-group">
              <label>Category</label> <input type="text"
                                             pattern="[a-zA-Z]+" required
                                             class="form-control" id="InputCategory" aria-describedby="category"
                                             name="category" placeholder="fast-food" value="<%=food.getCategory()%>">
            </div>
            <div class="form-group">
              <label>Price</label> <input type="number"
                                          step=".01"
                                          required="required"
                                          class="form-control" id="InputPrice" aria-describedby="price"
                                          name="price" placeholder="10$" value="<%=food.getPrice()%>">
            </div>
            <div class="form-group">
              <label>Active</label>
              <select name="active">
                <c:if test="${food.active eq true}">
                <option value="true" selected>True</option>
                  <option value="false">False</option>
                </c:if>
                <c:if test="${food.active eq false}">
                  <option value="true" >True</option>
                  <option value="false" selected>False</option>
                </c:if>
              </select>
            </div>
            <button type="submit" class="btn btn-primary badge-pill btn-block">Create</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>

<%@include file="all_components/footer.jsp" %>
</body>
</html>
