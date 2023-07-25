package com.example.cafewebapp.DAO;

import com.example.cafewebapp.Entity.Payments;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDAOTest {
    PaymentDAO paymentDAO = new PaymentDAO();
    @Test
    void addPayment() {
        assertTrue(paymentDAO.AddPayment(new Payments(
                1,
                new BigDecimal(100),
                "credit-card",
                Timestamp.valueOf(LocalDateTime.now())
        )));
        paymentDAO.deletePayment(1);
    }
}