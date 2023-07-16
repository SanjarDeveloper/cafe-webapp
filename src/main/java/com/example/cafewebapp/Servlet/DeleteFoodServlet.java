package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.FoodsDAO;
import com.example.cafewebapp.Entity.Foods;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteFoodServlet", value = "/delete-food")
public class DeleteFoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            FoodsDAO foodsDAO = new FoodsDAO();
            boolean isSuccess = foodsDAO.deleteFood(Integer.parseInt(id));
            HttpSession session = request.getSession();
            if (isSuccess){
                session.setAttribute("succMsg", "Food Successfully Deleted!");
                response.sendRedirect("menu.jsp");
            }else {
                session.setAttribute("succMsg", "Something went wrong!");
                response.sendRedirect("menu.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HIIIIII");
    }
}
