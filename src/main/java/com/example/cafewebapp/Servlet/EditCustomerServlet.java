package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.AccessDAO;
import com.example.cafewebapp.DAO.CustomerDAO;
import com.example.cafewebapp.DAO.OrderDAO;
import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Users;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@WebServlet(name = "EditCustomerServlet", value = "/edit-customer")
public class EditCustomerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            HttpSession session = request.getSession();

            int id = (int) session.getAttribute("id");
            String name = request.getParameter("name");
            String contact = request.getParameter("phone");
            String address = request.getParameter("address");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String bonus = request.getParameter("bonus");
            String balance = request.getParameter("balance");
            String active = request.getParameter("active");
            String user_type = request.getParameter("user_type");
            String banned = request.getParameter("banned");


            AccessDAO accessDAO = new AccessDAO();
            CustomerDAO customerDAO = new CustomerDAO();

            List<Users> allUsers = accessDAO.getAllUsers();

            Customers customer = customerDAO.getCustomerById(id);
            Users user = accessDAO.getUserById(customer.getUser_id().getId());

            boolean isUniqueUsername = false;
            if (!username.equals(user.getUsername())) {
                if (!allUsers.isEmpty()) {
                    for (Users allUser : allUsers) {
                        if (allUser.getUsername().equals(username)) {
                            isUniqueUsername = false;
                            break;
                        } else {
                            isUniqueUsername = true;
                        }
                    }
                }
            } else {
                isUniqueUsername = true;
            }
            if (isUniqueUsername) {
                if (!name.replace(" ", "").isEmpty() && !contact.replace(" ", "").isEmpty()
                        && !address.replace(" ", "").isEmpty() && !username.replace(" ", "").isEmpty()
                        && !password.replace(" ", "").isEmpty()) {
                    user.setUser_type(user_type);
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setActive(Boolean.parseBoolean(active));
                    if (accessDAO.updateUser(user)) {
                        customer.setName(name);
                        customer.setContact(contact);
                        customer.setAddress(address);
                        customer.setBonus(BigDecimal.valueOf(Double.parseDouble(bonus)));
                        customer.setBalance(BigDecimal.valueOf(Double.parseDouble(balance)));
                        customer.setBanned(Boolean.parseBoolean(banned));
                        if (banned.equals("true")){
                            OrderDAO.banStatus = null;
                        }
                        customer.setActive(Boolean.parseBoolean(active));
                        customer.setUser_id(user);
                        boolean isSuccess = customerDAO.updateCustomer(customer);
                        if (isSuccess) {
                            session.setAttribute("succMsg", "Successfully edited!");
                            response.sendRedirect("customers.jsp");
                        }
                    }
                } else {
                    throw new RuntimeException();
                }
            } else {
                session.setAttribute("errorMsg", "Username has already taken. Please try again!");
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
