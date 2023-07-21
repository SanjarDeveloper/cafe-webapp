<%@ page import="java.util.List" %>
<%@ page import="com.example.cafewebapp.DAO.FoodsDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Foods" %>
<%@ page import="com.example.cafewebapp.DAO.CartDAO" %>
<%@ page import="java.util.LinkedHashMap" %>
<%@ page import="java.math.BigDecimal" %><%--
  Created by IntelliJ IDEA.
  User: Sanja
  Date: 06.07.2023
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
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

        button.btn-minus, .btn-clear {
            background-color: #f71818;
            color: white;
            font-weight: bolder;
            border-radius: 5px;
            font-size: 15px;
            padding: 10px 17px;
        }

        button.btn-minus:hover, .btn-clear:hover {
            background-color: rgba(187, 3, 3, 0.9);
        }

        .header {
            text-align: center;
        }

        body {
            background-color: #c4eaf4;
        }

        .btn-plus, .btn-order {
            background-color: #18f71d;
            color: white;
            font-weight: bolder;
            font-size: 15px;
            border-radius: 5px;
            padding: 10px 15px;
        }

        button.btn-plus:hover, .btn-order:hover {
            background-color: rgba(28, 160, 2, 0.76);
        }

        .total {
            background-color: #d9ba03;
            border: #000 solid 1px;
            border-radius: 20px;
            padding-bottom: 50px;
        }

        .total h1 {
            margin: 10px auto;
            width: fit-content;
            background-color: #fff;
            border-radius: 30px;
            padding: 5px;
            color: #000;
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

<%
    LinkedHashMap<Foods, Integer> allFoodsInCart = CartDAO.foodsList;
    if (allFoodsInCart.size() == 0) {
        session.setAttribute("emptyCart", true);
    }else {
        session.setAttribute("emptyCart", false);
    }
%>

<div class="main">
    <c:if test="${customer.banned ne true}">
        <h1 class="header">Cart:</h1>
        <div class="menu">
        <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
        </c:if>
        <c:if test="${emptyCart eq false}">
            <%
                double total = 0;
                for (Foods foods : allFoodsInCart.keySet()) {
                    total = total + allFoodsInCart.get(foods) * Double.parseDouble(String.valueOf(foods.getPrice()));
            %>

            <div class="menu-item">
                <h1><%=foods.getName()%>
                </h1>
                <div class="details">
                    <p>Pcs: <%=allFoodsInCart.get(foods)%>
                    </p>
                    <h3>Price: <%=allFoodsInCart.get(foods)%> * <%=foods.getPrice()%>
                        = <%=allFoodsInCart.get(foods) * Double.parseDouble(String.valueOf(foods.getPrice()))%>
                    </h3>
                </div>
                <div class="options">
                    <a href="add-amount?id=<%=foods.getId()%>">
                        <button class="btn-plus">+</button>
                    </a>
                    <a href="remove-amount?id=<%=foods.getId()%>">
                        <button class="btn-minus">-</button>
                    </a>
                </div>
            </div>

            <%
                }
                session.setAttribute("total", total);
            %>

            </div>
            <div class="total">
                <h1>Total</h1>
                <div class="details">
                    <h3>Price: ${total}
                    </h3>

                </div>
                <div class="options">
                    <a href="clear-cart">
                        <button class="btn-clear">Clear Cart</button>
                    </a>
                    <a href="create-order">
                        <button class="btn-order">Pay By Balance</button>
                    </a>
                    <a href="create-order?byBonus=true">
                        <button class="btn-order">Pay By Bonus</button>
                    </a>
                </div>
            </div>
        </c:if>
        <c:if test="${emptyCart eq true}">
            <h1 class="text-center text-danger">Cart is empty!</h1>
        </c:if>
    </c:if>
    <c:if test="${customer.banned eq true}">
        <h1 class="text-center text-danger">You are Banned. Please contact admin!</h1>
    </c:if>
    <%@include file="all_components/footer.jsp" %>
</div>

</body>
</html>
