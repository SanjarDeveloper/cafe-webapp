<%@ page import="java.util.List" %>
<%@ page import="com.example.cafewebapp.DAO.FoodsDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Foods" %>
<%@ page import="com.example.cafewebapp.DAO.OrderDAO" %>
<%@ page import="com.example.cafewebapp.Entity.Orders" %>
<%@ page import="java.text.DateFormat" %><%--
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

    <%--Checking for user is admin and show create food button--%>
    <c:if test="${userobj.user_type eq 'admin'}">
        <button class="btn-create-food"><a href="create-food.jsp">Create Food</a></button>
    </c:if>
    <%--Checking if customer has ban--%>
    <c:if test="${customer.banned eq false}">
    <h1 class="header">Available Foods:</h1>
        <form action="getTime.jsp" method="get">
            <label for="date">Select a date:</label>
            <input type="datetime-local" id="date" name="date" min="2023-07-18T00:00:00" onclick="myFunction()">

            <script>
                function myFunction() {
                    let dateInput = document.getElementById("date");
                    var date = new Date();
                    date.setUTCHours(date.getUTCHours() + 5)
                    dateInput.min = date.toISOString().slice(0,new Date().toISOString().lastIndexOf(":"));
                }
            </script>
            <input type="submit">

            <%
                String time = (String) session.getAttribute("takeaway-time");
                if (time != null) {

            %>
            <h1>Selected time: <%=time%></h1>
            <%
                }
            %>
        </form>
    <div class="menu">
            <%--Message to show when adding to cart is success or failure--%>
        <c:if test="${not empty succMsg }">
            <h4 class="text-center text-danger">${succMsg}</h4>
            <c:remove var="succMsg"/>
        </c:if>

        <%
            FoodsDAO foodsDAO = new FoodsDAO();
            List<Foods> foods = foodsDAO.getAllFoods();
            if (!foods.isEmpty()) {
                for (Foods food : foods) {
        %>

        <div class="menu-item">
            <h1><%=food.getName()%>
            </h1>
            <div class="details">
                <p><%=food.getDetails()%>
                </p>
                <h3>Price: $<%=food.getPrice()%>
                </h3>
            </div>

            <c:if test="${userobj.user_type eq 'user'}">
                <div class="options">
                    <a href="add-cart?id=<%=food.getId()%>">
                        <button class="btn-add-cart">Add Cart</button>
                    </a>
                </div>
            </c:if>
            <c:if test="${userobj.user_type eq 'admin'}">
                <div class="options">
                    <a href="edit-food.jsp?id=<%=food.getId()%>">
                        <button class="btn-edit">Edit</button>
                    </a>
                    <a href="delete-food?id=<%=food.getId()%>">
                        <button class="btn-delete">Delete</button>
                    </a>
                </div>
            </c:if>
        </div>

        <%
                }
            }
        %>
        </form>

    </div>

        </c:if>
        <c:if test="${customer.banned eq true}">
            <h1 class="text-center text-danger">You are Banned. Please contact admin!</h1>
        </c:if>
        <%@include file="all_components/footer.jsp" %>
</div>
</body>
</html>
