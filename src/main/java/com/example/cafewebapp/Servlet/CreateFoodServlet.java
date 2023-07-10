package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.FoodsDAO;
import com.example.cafewebapp.Entity.Foods;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "MenuServlet", value = "/create-food")
public class CreateFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FoodsDAO foodsDAO = new FoodsDAO();
            HttpSession session = request.getSession();

            String name = request.getParameter("name");
            String details = request.getParameter("details");
            String category = request.getParameter("category");
            String price = request.getParameter("price");
            String active = request.getParameter("active");

            Foods food = new Foods();
            food.setName(name);
            food.setDetails(details);
            food.setCategory(category);
            food.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
            food.setActive(Boolean.parseBoolean(active));

            boolean f = foodsDAO.createFood(food);
            if (f){
                session.setAttribute("succMsg", "Successfully created!");
                response.sendRedirect("menu.jsp");
            }else {
                session.setAttribute("succMsg", "Something went wrong");
                response.sendRedirect("create-food.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
