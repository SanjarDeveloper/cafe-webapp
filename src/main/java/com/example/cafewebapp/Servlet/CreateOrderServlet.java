package com.example.cafewebapp.Servlet;

import com.example.cafewebapp.DAO.CartDAO;
import com.example.cafewebapp.DAO.CustomerDAO;
import com.example.cafewebapp.DAO.OrderDAO;
import com.example.cafewebapp.DAO.PaymentDAO;
import com.example.cafewebapp.Entity.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.LinkedHashMap;

import static com.example.cafewebapp.DAO.OrderDAO.orderId;
import static com.example.cafewebapp.DAO.PaymentDAO.paymentId;

@WebServlet(name = "CreateOrderServlet", value = "/create-order")
public class CreateOrderServlet extends HttpServlet {
    CustomerDAO customerDAO = new CustomerDAO();
    PaymentDAO paymentDAO = new PaymentDAO();
    OrderDAO orderDAO = new OrderDAO();

    int paymentid = paymentId;
    int orderid = orderId;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess = false;
        try {
            HttpSession session = request.getSession();
            Users userobj = (Users) session.getAttribute("userobj");
            Double total = (Double) session.getAttribute("total");
            String byBonus = request.getParameter("byBonus");
            Customers customer = customerDAO.getCustomerByUserId(userobj.getId());
            if (byBonus == null) {
                boolean isSuccessPayment = withdrawFromBalance(customer, total);
                if (isSuccessPayment) {
                    boolean isSuccessOrder = makeOrder(customer);
                    if (isSuccessOrder) {
                        isSuccess = createOrderDetails();
                    }
                }
            }else {
                boolean isSuccessPayment = withdrawFromBonus(customer, total);
                if (isSuccessPayment){
                    boolean isSuccessOrder = makeOrder(customer);
                    if (isSuccessOrder){
                        isSuccess = createOrderDetails();
                    }
                }
            }
            if (isSuccess) {
                CartDAO.foodsList.clear();
                orderid = orderId;
                paymentid = paymentId;
                session.setAttribute("succMsg", "Order successfully created!");
                response.sendRedirect("menu.jsp");
            } else {
                session.setAttribute("succMsg", "Something went wrong!");
                response.sendRedirect("cart.jsp");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public boolean withdrawFromBalance(Customers customer, double amount) {
        boolean isSuccessPayment = false;
        double balance = Double.parseDouble(String.valueOf(customer.getBalance()));
        if (amount <= balance) {
            customer.setBalance(BigDecimal.valueOf(balance - amount));
            BigDecimal bonus = customer.getBonus();
            customer.setBonus(BigDecimal.valueOf(amount / 100 + Double.parseDouble(String.valueOf(bonus))));
            customerDAO.updateCustomer(customer);
            Payments payment = new Payments();
            payment.setId(paymentid);
            payment.setAmount(BigDecimal.valueOf(amount));
            payment.setType("credit-card");
            isSuccessPayment = paymentDAO.AddPayment(payment);
        }
        return isSuccessPayment;
    }

    public boolean makeOrder(Customers customer) {
        Orders orders = new Orders();
        orders.setId(orderid);
        orders.setCustomer_id(customer);
        Payments paymentById = paymentDAO.getPaymentById(paymentid);
        orders.setPayment_id(paymentById);
        orders.setStatus("New");
        return orderDAO.addOrder(orders);
    }

    public boolean withdrawFromBonus(Customers customer, double amount) {
        boolean isSuccessPayment = false;
        double bonus = Double.parseDouble(String.valueOf(customer.getBonus()));
        if (amount <= bonus) {
            customer.setBonus(BigDecimal.valueOf(bonus - amount));
            customerDAO.updateCustomer(customer);
            Payments payment = new Payments();
            payment.setId(paymentid);
            payment.setAmount(BigDecimal.valueOf(amount));
            payment.setType("bonus-card");
            isSuccessPayment = paymentDAO.AddPayment(payment);

        }
        return isSuccessPayment;
    }

    public boolean createOrderDetails(){
        boolean isSuccess = true;
        LinkedHashMap<Foods, Integer> foodsList = CartDAO.foodsList;
        Orders orderById = orderDAO.getOrderById(orderid);
        for (Foods foods : foodsList.keySet()) {
            isSuccess = orderDAO.createOrderDetails(orderById, foods, foodsList.get(foods));
            if (!isSuccess) {
                break;
            }
        }
        return isSuccess;
    }
}
