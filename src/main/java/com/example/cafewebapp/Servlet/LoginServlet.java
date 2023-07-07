package com.example.cafewebapp.Servlet;

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
        getServletContext().getRequestDispatcher("login.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            String em=req.getParameter("em");
            String ps=req.getParameter("ps");
            Users user = new Users();
            HttpSession session = req.getSession();

            if(em.equals("admin@gmail.com") && ps.equals("abcd")){
                session.setAttribute("userobj",user);
                user.setUser_type("admin");
                resp.sendRedirect("admin.jsp");
            } else {
               // resp.sendRedirect("login.jsp");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
