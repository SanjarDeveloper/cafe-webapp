package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.CartDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddAmountToCartServlet", value = "/add-amount")
public class AddAmountToCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            HttpSession session = request.getSession();
            CartDAO cartDAO = new CartDAO();
            String id = request.getParameter("id");
            boolean isSuccess = cartDAO.plusOneAmount(Integer.parseInt(id));
            if (isSuccess){
                session.setAttribute("succMsg", "Success!");
                response.sendRedirect("cart.jsp");
            }else {
                session.setAttribute("succMsg", "Something went wrong");
                response.sendRedirect("cart.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
