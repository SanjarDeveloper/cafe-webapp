<%@ page import="com.example.cafewebapp.DAO.AccessDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Users" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 22.06.2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create food</title>
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
                        <h5>Create Food</h5>
                    </div>
                    <c:if test="${not empty succMsg }">
                        <h4 class="text-center text-danger">${succMsg}</h4>
                        <c:remove var="succMsg"/>
                    </c:if>
                    <form action="create-food" method="post">
                        <div class="form-group">
                            <label>Food Name</label> <input type="text"
                                                            pattern="[a-zA-Z]+" required
                                                            class="form-control" id="InputName" aria-describedby="name"
                                                            name="name" placeholder="pizza">
                        </div>
                        <div class="form-group">
                            <label>Food Details</label> <input type="text"
                                                               required="required"
                                                            class="form-control" id="InputDetails" aria-describedby="details"
                                                            name="details" placeholder="info about food">
                        </div>
                        <div class="form-group">
                            <label>Category</label> <input type="text"
                                                            pattern="[a-zA-Z]+" required
                                                            class="form-control" id="InputCategory" aria-describedby="category"
                                                            name="category" placeholder="fastfood">
                        </div>
                        <div class="form-group">
                            <label>Price</label> <input type="number"
                                                            required="required"
                                                           class="form-control" id="InputPrice" aria-describedby="price"
                                                           name="price" placeholder="10$">
                        </div>
                        <div class="form-group">
                            <label>Active</label>
                            <select name="active">
                                <option value="true">True</option>
                                <option value="false">False</option>
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
