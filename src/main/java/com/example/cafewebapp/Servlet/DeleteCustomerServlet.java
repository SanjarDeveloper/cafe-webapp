package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.CustomerDAO;
import com.example.cafewebapp.DAO.FoodsDAO;
import com.example.cafewebapp.Entity.Customers;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "DeleteCustomerServlet", value = "/delete-customer")
public class DeleteCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            CustomerDAO customerDAO = new CustomerDAO();
            boolean isSuccess = customerDAO.deleteCustomer(Integer.parseInt(id));
            HttpSession session = request.getSession();
            if (isSuccess){
                session.setAttribute("succMsg", "Customer Successfully Deleted!");
                response.sendRedirect("customers.jsp");
            }else {
                session.setAttribute("succMsg", "Something went wrong!");
                response.sendRedirect("customers.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
