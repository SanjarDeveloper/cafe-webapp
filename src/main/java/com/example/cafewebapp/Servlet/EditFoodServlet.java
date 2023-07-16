package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.FoodsDAO;
import com.example.cafewebapp.Entity.Foods;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import jakarta.websocket.server.PathParam;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "EditFoodServlet", value = "/edit-food")
public class EditFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            FoodsDAO foodsDAO = new FoodsDAO();
            HttpSession session = request.getSession();
            Foods food1 = (Foods) session.getAttribute("food");
            String id = String.valueOf(food1.getId());
            String name = request.getParameter("name");
            String details = request.getParameter("details");
            String category = request.getParameter("category");
            String price = request.getParameter("price");
            String active = request.getParameter("active");

            Foods food = foodsDAO.getFoodById(Integer.parseInt(id));
            food.setName(name);
            food.setDetails(details);
            food.setCategory(category);
            food.setPrice(BigDecimal.valueOf(Double.parseDouble(price)));
            food.setActive(Boolean.parseBoolean(active));

            boolean f = foodsDAO.updateFood(food);
            if (f){
                session.setAttribute("succMsg", "Successfully edited!");
                response.sendRedirect("menu.jsp");
            }else {
                session.setAttribute("succMsg", "Something went wrong");
                response.sendRedirect("edit-food.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
