package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.OrderDAO;
import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Orders;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "OrderDetailsServlet", value = "/order-details")
public class OrderDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            session.setAttribute("orderId", Integer.parseInt(id));
            OrderDAO orderDAO = new OrderDAO();
            Orders orderById = orderDAO.getOrderById(Integer.parseInt(id));
            if (orderById != null) {
                Customers customer = orderById.getCustomer_id();
                if (customer != null) {
                    session.setAttribute("customer", customer);
                }
            }
            response.sendRedirect("order-details.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
