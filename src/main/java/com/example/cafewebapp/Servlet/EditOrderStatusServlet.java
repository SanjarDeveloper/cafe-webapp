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
        boolean isSuccess = false;
        HttpSession session = request.getSession();
        String orderStatus = request.getParameter("orderStatus");
        String feedback = request.getParameter("feedback");
        String rating = request.getParameter("rating");
        int orderId = (int) session.getAttribute("orderId");
        OrderDAO orderDAO = new OrderDAO();
        Orders orderById = orderDAO.getOrderById(orderId);
        orderById.setStatus(orderStatus);
        String byAdmin = (String) session.getAttribute("ByAdmin");
        if (byAdmin != null) {
            if (byAdmin.equals("true")) {
                boolean isStatusUpdated = orderDAO.updateOrderStatus(orderById);
                if (isStatusUpdated) {
                    isSuccess = true;
                }
            }
        } else {
            if (feedback != null) {
                if (rating != null) {
                    boolean isFeedbackRatingAdded = orderDAO.addFeedbackandRating(feedback, Integer.parseInt(rating), orderId);
                    if (isFeedbackRatingAdded) {
                        isSuccess = true;
                    }
                }
            }
        }
        if (isSuccess) {
            session.setAttribute("succMsg", "Success!");
        } else {
            session.setAttribute("succMsg", "Something went wrong!");
        }
        response.sendRedirect("orders.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
