package com.example.cafewebapp.Servlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "GetTakeawayTimeServlet", value = "/get-takeaway-time")
public class GetTakeawayTimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String time = request.getParameter("takeaway-time");
        session.setAttribute("takeaway-time",time);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String time = request.getParameter("takeaway-time");
        session.setAttribute("takeaway-time",time);
    }
}
