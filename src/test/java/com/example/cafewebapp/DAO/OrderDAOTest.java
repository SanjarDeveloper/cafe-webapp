package com.example.cafewebapp.DAO;

import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Orders;
import com.example.cafewebapp.Entity.Payments;
import com.example.cafewebapp.Entity.Users;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {

    @Test
    void addOrder() {
        OrderDAO orderDAO = new OrderDAO();
        CustomerDAO customerDAO = new CustomerDAO();
        PaymentDAO paymentDAO = new PaymentDAO();
        AccessDAO accessDAO = new AccessDAO();

        Orders order = new Orders();
        order.setId(1);
        Customers customers = new Customers();
        customers.setName("John");
        customers.setContact("Miles");
        customers.setAddress("Something");
        customers.setBonus(BigDecimal.ZERO);
        customers.setBanned(false);
        customers.setActive(true);
        customers.setBalance(new BigDecimal(100));

        Users user = new Users();
        user.setId(1);
        user.setUser_type("user");
        user.setUsername("user");
        user.setPassword("user");
        user.setActive(true);
        accessDAO.registerUser(user);

        customers.setUser_id(accessDAO.getUserById(1));
        customerDAO.registerCustomer(customers);

        order.setCustomer_id(customerDAO.getCustomerById(1));
        paymentDAO.AddPayment(new Payments(
                1,
                new BigDecimal(100),
                "credit-card",
                Timestamp.valueOf(LocalDateTime.now())
        ));
        order.setPayment_id(paymentDAO.getPaymentById(1));

        order.setStatus("New");
        order.setTime(Timestamp.valueOf(LocalDateTime.now()));
        order.setFeedback("smth");
        order.setRating(5);
        order.setTakeaway_time(Timestamp.valueOf(LocalDateTime.now()));
        assertTrue(orderDAO.addOrder(order));
        orderDAO.deleteOrder(1);
        paymentDAO.deletePayment(1);
        customerDAO.deleteCustomer(1);
        accessDAO.deleteUser(1);
    }
}