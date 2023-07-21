<%@ page import="com.example.cafewebapp.DAO.AccessDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Users" %>
<%@ page import="com.example.cafewebapp.Entity.Customers" %>
<%@ page import="com.example.cafewebapp.DAO.OrderDAO" %>
<%@ page import="com.example.cafewebapp.Entity.DTO.OrderDetailsDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.cafewebapp.DAO.PaymentDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Orders" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 22.06.2023
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Order</title>
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
                        <h5>Order Details</h5>
                    </div>
                    <c:if test="${not empty succMsg }">
                        <h4 class="text-center text-danger">${succMsg}</h4>
                        <c:remove var="succMsg"/>
                    </c:if>

                    <c:if test="${userobj.user_type eq 'admin'}">
                        <%session.setAttribute("byAdmin", "true");%>
                    </c:if>
                    <%
                        int counter = 1;
                        Customers customer = (Customers) session.getAttribute("customer");
                        int orderId = (int) session.getAttribute("orderId");
                    %>
                    <%--<form action="orders.jsp" method="get">--%>
                    <div class="form-group">
                        <label>Customer Name:</label> <input type="text"
                                                             value="<%=customer.getName()%>"
                                                             class="form-control" id="customer-name"
                                                             aria-describedby="customer-name" name="customer-name"
                                                             readonly>
                    </div>
                    <%
                        OrderDAO orderDAO = new OrderDAO();
                        List<OrderDetailsDTO> orderDetails = orderDAO.getOrderDetailsByOrderId(orderId);
                        for (OrderDetailsDTO orderDetail : orderDetails) {
                    %>
                    <div class="form-group">
                        <label>Food: №<%=counter++%>
                        </label> <input type="text" class="form-control"
                                        id="food-name" name="foodName"
                                        value="<%=orderDetail.getFood().getName()%>" readonly>
                        <label>Pcs:</label> <input type="number" class="form-control"
                                                   id="food-pcs" name="foodPcs"
                                                   value="<%=orderDetail.getAmount()%>" readonly>
                    </div>
                    <%
                        }
                        Orders orderById = orderDAO.getOrderById(orderId);
                    %>
                    <div class="form-group">
                        <label>Payment:</label> <input type="text"
                                                       class="form-control"
                                                       id="PaymentAmount"
                                                       aria-describedby="Amount"
                                                       name="amount"
                                                       value="<%=orderById.getPayment_id().getAmount()%>" readonly>
                        <label>Time:</label> <input type="text"
                                                    class="form-control"
                                                    id="paymentTime"
                                                    aria-describedby="paymentTime"
                                                    name="time"
                                                    value="<%=orderById.getPayment_id().getTime()%>" readonly>
                    </div>

                    <c:if test="${ByBalance eq 'true'}">
                    <div class="form-group">
                        <label>Bonus (1%):</label> <input type="text"
                                                          value="<%=Double.parseDouble(String.valueOf(orderById.getPayment_id().getAmount()))/100%>"
                                                          class="form-control" id="bonus" name="bonus" readonly>
                        </c:if>
                        <div class="form-group">
                            <label>Order Status:</label> <input type="text"
                                                                class="form-control"
                                                                id="status" name="orderStatus"
                                                                value="<%=orderById.getStatus()%>">
                        </div>

                        <a href="orders.jsp">
                            <button type="button" class="btn btn-primary badge-pill btn-block">Back</button>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <%@include file="all_components/footer.jsp" %>
</body>
</html>
