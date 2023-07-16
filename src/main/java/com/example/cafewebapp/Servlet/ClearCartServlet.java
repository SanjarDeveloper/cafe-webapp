package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.CartDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ClearCartServlet", value = "/clear-cart")
public class ClearCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            CartDAO cartDAO = new CartDAO();
            cartDAO.clearCart();
            HttpSession session = request.getSession();
            session.setAttribute("succMsg", "Cart cleared!");
            response.sendRedirect("cart.jsp");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
