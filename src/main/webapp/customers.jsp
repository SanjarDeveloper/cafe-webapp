<%@ page import="java.util.List" %>
<%@ page import="com.example.cafewebapp.DAO.FoodsDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Foods" %>
<%@ page import="com.example.cafewebapp.DAO.CustomerDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Customers" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 06.07.2023
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customers</title>
    <%@include file="all_components/all_css.jsp" %>
    <style>
        .customers-item:last-child{
            padding-bottom: 50px;
        }
        .customers-item {
            background-color: #ffe900;
            border: #000 solid 1px;
            border-radius: 20px;
        }

        .customers-item h1 {
            margin: 10px auto;
            width: fit-content;
            background-color: #fff;
            border-radius: 30px;
            padding: 5px;
            color: #000;

        }

        .details {
            text-align: center;
        }

        .options {
            margin: 0;
            background-color: rgba(255, 217, 0, 0.91);
            border-bottom-left-radius: 20px;
            border-bottom-right-radius: 20px;
            padding: 10px;
            text-align: center;
        }

        button.btn-edit {
            background-color: #18f71d;
            color: white;
            font-weight: bolder;
            font-size: 15px;
            border-radius: 5px;
            padding: 10px 15px;
        }

        button.btn-edit:hover {
            background-color: rgba(28, 160, 2, 0.76);

        }

        button.btn-delete {
            background-color: #f71818;
            color: white;
            font-weight: bolder;
            border-radius: 5px;
            font-size: 15px;
            padding: 10px 10px;
        }

        button.btn-delete:hover {
            background-color: rgba(187, 3, 3, 0.9);
        }

        .header {
            text-align: center;
        }

        body {
            background-color: #c4eaf4;
        }
        .btn-add-cart {
            background-color: #18f71d;
            color: white;
            font-weight: bolder;
            font-size: 15px;
            border-radius: 5px;
            padding: 10px 15px;
        }

        button.btn-add-cart:hover {
            background-color: rgba(28, 160, 2, 0.76);
        }
        .btn-create-customer {
            background-color: #18f71d;
            color: white;
            font-weight: bolder;
            font-size: 15px;
            border-radius: 5px;
            padding: 10px 15px;
        }
        .main {
            position: relative;
            min-height: 100%;
        }

    </style>
</head>
<body>
<%@include file="all_components/navbar.jsp" %>
<c:if test="${empty userobj}">
    <c:redirect url="index.jsp"></c:redirect>
</c:if>
<div class="main">
    <c:if test="${userobj.user_type eq 'admin'}">
        <button class="btn-create-customer"><a href="signup.jsp">Create Customer</a></button>
    </c:if>
    <h1 class="header">Customers List:</h1>
    <div class="customers">
        <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
        </c:if>
        <%
            CustomerDAO customerDAO = new CustomerDAO();
            List<Customers> allCustomers = customerDAO.getAllCustomers();
            if (!allCustomers.isEmpty()) {
                for (Customers allCustomer : allCustomers) {
        %>

        <div class="customers-item">
            <a href="edit-customer.jsp?id=<%=allCustomer.getId()%>"><h1><%=allCustomer.getName()%></h1></a>

            <div class="details">
                <p><%=allCustomer.getAddress()%>
                </p>
                <h3>+<%=allCustomer.getContact()%>
                </h3>
            </div>
            <c:if test="${userobj.user_type eq 'user'}">
                <div class="options">
                    <button class="btn-add-cart">Add Cart</button>
                </div>
            </c:if>
            <c:if test="${userobj.user_type eq 'admin'}">
                <div class="options">
                    <a href="edit-customer.jsp?id=<%=allCustomer.getId()%>">
                        <button class="btn-edit">Edit</button>
                    </a>
                    <a href="delete-customer?id=<%=allCustomer.getId()%>">
                        <button class="btn-delete">Delete</button>
                    </a>
                </div>
            </c:if>
        </div>

        <%
                }
            }
        %>
    </div>
    <%@include file="all_components/footer.jsp" %>
</div>
</body>
</html>
