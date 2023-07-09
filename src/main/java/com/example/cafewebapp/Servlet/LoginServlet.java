package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.AccessDAO;
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
            Users user = new Users();
            HttpSession session = req.getSession();

            AccessDAO dao = new AccessDAO();

            boolean isMatch = false;

            for (Users allUser : dao.getAllUsers()) {
                if (username.equals(allUser.getUsername()) && password.equals(allUser.getPassword())) {
                    isMatch = true;
                    session.setAttribute("userobj", user);
                    user.setUser_type(allUser.getUser_type());
                    if (user.getUser_type().equals("admin")){
                        resp.sendRedirect("admin.jsp");
                    }else {
                        resp.sendRedirect("menu.jsp");
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
