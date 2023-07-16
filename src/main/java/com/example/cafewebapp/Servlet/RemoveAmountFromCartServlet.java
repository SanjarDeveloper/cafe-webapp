package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.CartDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.Calendar;

@WebServlet(name = "RemoveAmountFromCartServlet", value = "/remove-amount")
public class RemoveAmountFromCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CartDAO cartDAO = new CartDAO();
        String id = request.getParameter("id");
        boolean isSuccess = cartDAO.minusOneAmount(Integer.parseInt(id));
        if (isSuccess){
            session.setAttribute("succMsg", "Success!");
            response.sendRedirect("cart.jsp");
        }else {
            session.setAttribute("succMsg", "Something went wrong");
            response.sendRedirect("cart.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
