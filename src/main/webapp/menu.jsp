<%--
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
    <%@include file="all_components/all_css.jsp"%>
    <style>
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

        button.btn-edit:hover{
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
        body{
            background-color: #c4eaf4;
        }
    </style>
</head>
<body>
<%@include file="all_components/navbar.jsp"%>
<h1 class="header">Available Foods:</h1>
<div class="menu">
    <div class="menu-item">
        <h1>Pizza</h1>
        <div class="details">
            <p>lorem ipsum et die sa lure mi</p>
            <h3>Price: 10$</h3>
        </div>
        <div class="options">
            <button class="btn-edit">Edit</button>
            <button class="btn-delete">Delete</button>
        </div>
    </div>
</div>
<%@include file="all_components/footer.jsp"%>
</body>
</html>
