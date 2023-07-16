package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.CartDAO;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "AddFoodCartServlet", value = "/add-cart")
public class AddFoodCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();
            String id = request.getParameter("id");
            int i = Integer.parseInt(id);
            CartDAO cartDAO = new CartDAO();
            boolean isSuccess = cartDAO.addFoodToCart(1, i);
            if (isSuccess){
                session.setAttribute("succMsg", "Successfully added to Cart!");
                response.sendRedirect("menu.jsp");
            }else {
                session.setAttribute("succMsg", "Something went wrong");
                response.sendRedirect("menu.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
