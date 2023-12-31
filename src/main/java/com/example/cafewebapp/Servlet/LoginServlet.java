package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.AccessDAO;
import com.example.cafewebapp.DAO.CustomerDAO;
import com.example.cafewebapp.DAO.OrderDAO;
import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Users;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "loginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext().getRequestDispatcher("login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            HttpSession session = req.getSession();

            AccessDAO dao = new AccessDAO();
            CustomerDAO customerDAO = new CustomerDAO();

            boolean isMatch = false;

            for (Users allUser : dao.getAllActiveUsers()) {
                if (username.equals(allUser.getUsername()) && password.equals(allUser.getPassword())) {
                    if (allUser.isActive()) {
                        isMatch = true;
                        session.setAttribute("userobj", allUser);
                        Customers customerByUserId = customerDAO.getCustomerByUserId(allUser.getId());
                        if (allUser.getUser_type().equals("admin")) {
                            resp.sendRedirect("admin.jsp");
                            session.setAttribute("customer", customerByUserId);
                            break;
                        } else {
                            OrderDAO orderDAO = new OrderDAO();
                            int sizeNotTakenOrders = orderDAO.getAllNotTakenOrders(customerByUserId.getId()).size();
                            if (sizeNotTakenOrders >= 3) {
                                customerByUserId.setBanned(true);
                                session.setAttribute("customer", customerByUserId);

                                resp.sendRedirect("menu.jsp");
                            } else {
                                resp.sendRedirect("orders.jsp");
                                session.setAttribute("customer", customerByUserId);
                            }
                            break;
                        }
                    }
                } else {
                    isMatch = false;
                }
            }
            if (!isMatch) {
                session.setAttribute("succMsg", "Wrong credentials!");
                resp.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
