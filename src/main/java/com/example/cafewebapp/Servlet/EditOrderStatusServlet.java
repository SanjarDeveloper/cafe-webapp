package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.OrderDAO;
import com.example.cafewebapp.Entity.Orders;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "EditOrderServlet", value = "/edit-order-status")
public class EditOrderStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String orderStatus = request.getParameter("orderStatus");
        int orderId = (int) session.getAttribute("orderId");
        OrderDAO orderDAO = new OrderDAO();
        Orders orderById = orderDAO.getOrderById(orderId);
        orderById.setStatus(orderStatus);
        boolean isSuccess = orderDAO.updateOrderStatus(orderById);
        if (isSuccess){
            session.setAttribute("succMsg","Success!");
        }else {
            session.setAttribute("succMsg", "Something went wrong!");
        }
        response.sendRedirect("orders.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
