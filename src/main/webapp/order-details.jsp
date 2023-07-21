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
    <title>Registration</title>
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
                        String feedback = "";
                        String rating = "";
                        Customers customer = (Customers) session.getAttribute("customer");
                        int orderId = (int) session.getAttribute("orderId");

                    %>
                    <form action="edit-order-status" method="get">
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
                            session.setAttribute("orderStatus", orderById.getStatus());
                            if (orderById.getPayment_id().getType().equals("credit-card")) {
                                session.setAttribute("ByBalance", true);
                            } else {
                                session.removeAttribute("ByBalance");
                            }
                            if (orderById.getFeedback() != null) {
                                session.setAttribute("feedback", orderById.getFeedback());
                            }
                            if (orderById.getRating() == 1) {
                                session.setAttribute("rating", "1");
                            } else if (orderById.getRating() == 2) {
                                session.setAttribute("rating", "2");
                            } else if (orderById.getRating() == 3) {
                                session.setAttribute("rating", "3");
                            } else if (orderById.getRating() == 4) {
                                session.setAttribute("rating", "4");
                            } else if (orderById.getRating() == 5) {
                                session.setAttribute("rating", "5");
                            }
                            if (orderById.getFeedback() != null){
                                feedback = orderById.getFeedback();
                            }
                            if (orderById.getRating() != 0){
                                rating = String.valueOf(orderById.getRating());
                            }
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
                            <label>Takeaway Time:</label> <input type="text"
                                                                 class="form-control"
                                                                 id="takeaway-time"
                                                                 aria-describedby="takeaway-time"
                                                                 name="takeaway-time"
                                                                 value="<%=orderById.getTakeaway_time()%>" readonly>
                        </div>
                        <c:if test="${ByBalance eq true}">
                            <div class="form-group">
                                <label>Bonus (1%):</label> <input type="text"
                                                                  value="<%=Double.parseDouble(String.valueOf(orderById.getPayment_id().getAmount()))/100%>"
                                                                  class="form-control" id="bonus" name="bonus" readonly>
                            </div>
                        </c:if>
                        <div class="form-group">
                            <label>Order Status:
                                <c:if test="${userobj.user_type eq 'admin'}">(New,Preparing,Order Taken or Order Not Taken)</c:if>

                            </label> <input type="text"
                                            class="form-control"
                                            name="orderStatus"
                                            value="<%=orderById.getStatus()%>"
                                            <c:if test="${userobj.user_type eq 'user'}">readonly</c:if>
                                            <c:if test="${orderStatus eq 'Order Taken' or orderStatus eq 'Order Not Taken'}">readonly</c:if>>
                        </div>
                        <c:if test="${orderStatus eq 'Order Taken'}">
                        <div class="form-group">
                        <label for="feedback">Feedback</label>
                        <input <c:if test="${userobj.user_type eq 'admin'}">readonly</c:if> id="feedback"
                                name="feedback" placeholder="Leave your feedback" value="<%=feedback%>" required>
                        <label>Rating</label>
                        <select name="rating" required>
                            <option value="1" <c:if test="${rating eq 1}">selected</c:if>>1</option>
                            <option value="2" <c:if test="${rating eq 2}">selected</c:if>>2</option>
                            <option value="3" <c:if test="${rating eq 3}">selected</c:if>>3</option>
                            <option value="4" <c:if test="${rating eq 4}">selected</c:if>>4</option>
                            <option value="5" <c:if test="${rating eq 5}">selected</c:if>>5</option>
                        </select>

                        <input type="submit" value="Submit Feedback"
                               class="btn btn-primary badge-pill btn-block">
                        </div>
                        </c:if>
                        <a href="orders.jsp">
                            <button type="button" class="btn btn-primary badge-pill btn-block">Back</button>
                        </a>

                        <c:if test="${userobj.user_type eq 'admin'}">
                            <button type="submit" class="btn btn-primary badge-pill btn-block">Update</button>

                        </c:if>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="all_components/footer.jsp" %>
</body>
</html>
