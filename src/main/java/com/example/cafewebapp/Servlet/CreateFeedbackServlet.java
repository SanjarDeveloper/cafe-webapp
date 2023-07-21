package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.OrderDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "CreateFeedbackServlet", value = "/create-feedback")
public class CreateFeedbackServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /*HttpSession session = request.getSession();
        String succMsg = null;
        try {
            OrderDAO orderDAO = new OrderDAO();
            String feedback = request.getParameter("feedback");
            String rating = request.getParameter("rating");
            if (feedback != null) {
                if (rating != null) {
                    boolean isSuccess = orderDAO.addFeedbackandRating(feedback, Integer.parseInt(rating),);
                    if (isSuccess){
                        succMsg = "Success!";
                    }
                } else {
                    succMsg = "Please rate the order!";
                }
            } else {
                succMsg = "Please provide feedback!";
            }
            session.setAttribute("succMsg",succMsg);
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
