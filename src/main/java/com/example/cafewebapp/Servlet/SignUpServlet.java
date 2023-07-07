package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.AccessDAO;
import com.example.cafewebapp.DAO.CustomerDAO;
import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(name = "SignUpServlet", value = "/register")
public class SignUpServlet extends HttpServlet {
    int userId = 0;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("signup.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String contact = request.getParameter("phone");
            String address = request.getParameter("address");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            Customers customer = new Customers();
            Users user = new Users();

            AccessDAO accessDAO = new AccessDAO();
            CustomerDAO customerDAO = new CustomerDAO();



            if(!name.isEmpty() && !contact.isEmpty() && !address.isEmpty() && !username.isEmpty() && !password.isEmpty()){
                userId = userId + 1;
                user.setId(userId);
                user.setUser_type("user");
                user.setUsername(username);
                user.setPassword(password);
                user.setActive(true);
                if (accessDAO.registerUser(user)){
                    customer.setName(name);
                    customer.setContact(contact);
                    customer.setAddress(address);
                    customer.setBonus(BigDecimal.valueOf(0));
                    customer.setBalance(BigDecimal.valueOf(0));
                    customer.setBanned(false);
                    customer.setActive(true);
                    customer.setUser_id(user);
                    boolean isSuccess = customerDAO.registerCustomer(customer);
                    if (isSuccess){
                    HttpSession session = request.getSession();
                    session.setAttribute("succMsg","Successfully registered! Please Log in");
                    response.sendRedirect("login.jsp");
                    }
                }else {
                    throw new RuntimeException();
                }
                }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
