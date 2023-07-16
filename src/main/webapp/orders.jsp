<%@ page import="java.util.List" %>
<%@ page import="com.example.cafewebapp.DAO.FoodsDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Foods" %>
<%@ page import="com.example.cafewebapp.Entity.Orders" %>
<%@ page import="com.example.cafewebapp.DAO.OrderDAO" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 06.07.2023
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu</title>
    <%@include file="all_components/all_css.jsp" %>
    <style>
        .menu-item:last-child {
            padding-bottom: 50px;
        }

        .menu-item {
            background-color: #ffe900;
            border: #000 solid 1px;
            border-radius: 20px;
        }

        .menu-item h1 {
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

        button.btn-take-order, button.btn-not-taken {
            background-color: #ffa100;
            color: white;
            font-weight: bolder;
            border-radius: 5px;
            font-size: 15px;
            padding: 10px 10px;
        }

        button.btn-not-taken{
            background-color: #d80000;
        }

        button.btn-take-order:hover {
            background-color: rgba(220, 148, 0, 0.9);
        }
        button.btn-not-taken:hover {
            background-color: #ab0000;
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

        .btn-create-food {
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
    <c:if test="${customer.banned eq true}">
        <h1 class="text-center text-danger">You are Banned. Please contact admin!</h1>
    </c:if>
    <h1 class="header">Orders:</h1>
    <div class="menu">
        <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
        </c:if>
        <%
            OrderDAO orderDAO = new OrderDAO();
            CustomerDAO customerDAO = new CustomerDAO();
            Customers customer = (Customers) session.getAttribute("customer");
            int numOfNotTakenOrders = orderDAO.getAllNotTakenOrders(customer.getId()).size();
            session.setAttribute("numOfNotTakenOrders", numOfNotTakenOrders);
            String banStatus = (String) session.getAttribute("BanStatus");
            if (numOfNotTakenOrders == 1 ){
                if (banStatus == null) {
                    customerDAO.minusAmountFromBalance(customer.getId(), 10.0);
                    session.setAttribute("BanStatus", "1");
                }
            }else if(numOfNotTakenOrders == 2){
                if (banStatus.equals("1")) {
                    customerDAO.minusAmountFromBalance(customer.getId(), 10.0);
                    session.setAttribute("BanStatus", "2");
                }
            } else if (numOfNotTakenOrders >= 3) {
                if (banStatus.equals("2")) {
                    customerDAO.makeCustomerBanned(customer.getId());
                }
            }

            List<Orders> orders = orderDAO.getAllOrders();
            if (!orders.isEmpty()) {
                for (Orders order : orders) {
                    if (order.getStatus().equals("Order Taken")){
                        session.setAttribute("orderStatus","Order Taken");
                    }
                    else if (order.getStatus().equals("Order Not Taken")){
                        session.setAttribute("orderStatus","Order Not Taken");
                    } else {
                        session.removeAttribute("orderStatus");
                    }
        %>

        <div class="menu-item">
            <h1>Order №<%=order.getId()%>
            </h1>
            <div class="details">
                <p>Status: <%=order.getStatus()%>
                </p>
                <h3>Total Price: $<%=order.getPayment_id().getAmount()%>
                </h3>
            </div>
            <c:if test="${userobj.user_type eq 'user'}">
                <div class="options">
                    <a href="order-details?id=<%=order.getId()%>">
                        <button class="btn-add-cart">View Details</button>
                    </a>
                    <c:if test="${orderStatus eq 'Order Taken'}">
                        <button class="btn-take-order">Order Taken</button>
                    </c:if>
                    <c:if test="${orderStatus eq 'Order Not Taken'}">
                        <button class="btn-not-taken">Order Not Taken -$
                            <c:if test="${numOfNotTakenOrders ne 0}">1</c:if>0</button>
                    </c:if>
                </div>
            </c:if>
            <c:if test="${userobj.user_type eq 'admin'}">
                <div class="options">
                    <a href="order-details?id=<%=order.getId()%>">
                        <button class="btn-edit">Edit</button>
                    </a>
                </div>
            </c:if>
        </div>

        <%
                    session.setAttribute("orderStatus","");
                }
            }
        %>
    </div>
    <%@include file="all_components/footer.jsp" %>
</div>
</body>
</html>
