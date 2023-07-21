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
import java.util.List;

@WebServlet(name = "SignUpServlet", value = "/register")
public class SignUpServlet extends HttpServlet {
    int userId = 1;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String name = request.getParameter("name");
            String contact = request.getParameter("phone");
            String address = request.getParameter("address");
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            HttpSession session1 = request.getSession();
            String byAdmin = (String) session1.getAttribute("byAdmin");

            Customers customer = new Customers();
            Users user = new Users();

            AccessDAO accessDAO = new AccessDAO();
            CustomerDAO customerDAO = new CustomerDAO();

            List<Users> allUsers = accessDAO.getAllUsers();

            boolean isUniqueUsername = false;

            HttpSession session = request.getSession();
            if (!allUsers.isEmpty()) {
                for (Users allUser : allUsers) {
                    if (allUser.getUsername().equals(username)) {
                        isUniqueUsername = false;
                        break;
                    } else {
                        isUniqueUsername = true;
                    }
                }
            }else {
                isUniqueUsername = true;
            }
            if (isUniqueUsername) {
                if (!name.replace(" ", "").isEmpty() && !contact.replace(" ", "").isEmpty()
                        && !address.replace(" ", "").isEmpty() && !username.replace(" ", "").isEmpty()
                        && !password.replace(" ", "").isEmpty()) {
                    userId = userId + 1;
                    user.setId(userId);
                    user.setUser_type("user");
                    user.setUsername(username);
                    user.setPassword(password);
                    user.setActive(true);
                    if (accessDAO.registerUser(user)) {
                        customer.setName(name);
                        customer.setContact(contact);
                        customer.setAddress(address);
                        customer.setBonus(BigDecimal.valueOf(0));
                        customer.setBalance(BigDecimal.valueOf(0));
                        customer.setBanned(false);
                        customer.setActive(true);
                        customer.setUser_id(user);
                        boolean isSuccess = customerDAO.registerCustomer(customer);
                        if (isSuccess) {
                            if ("true".equals(byAdmin)) {
                                session.setAttribute("succMsg", "Successfully created!");
                                response.sendRedirect("customers.jsp");
                            } else {
                                session.setAttribute("succMsg", "Successfully registered! Please Log in");
                                response.sendRedirect("login.jsp");
                            }
                        }else {
                            userId = userId -1;
                        }
                    } else {
                        userId = userId-1;
                        throw new RuntimeException();
                    }
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
