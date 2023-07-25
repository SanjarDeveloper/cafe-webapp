package com.example.cafewebapp.DAO;

import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Users;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {

    @Test
    void registerCustomer() {
        AccessDAO accessDAO = new AccessDAO();
        CustomerDAO customerDAO = new CustomerDAO();

        Customers customers = new Customers();
        customers.setName("John");
        customers.setContact("Miles");
        customers.setAddress("Something");
        customers.setBonus(BigDecimal.ZERO);
        customers.setBanned(false);
        customers.setActive(true);
        customers.setBalance(new BigDecimal(100));

        Users users = new Users();
        users.setId(2);
        users.setUser_type("user");
        users.setUsername("user");
        users.setPassword("user");
        users.setActive(true);
        accessDAO.registerUser(users);
        customers.setUser_id(users);
        assertTrue(customerDAO.registerCustomer(customers));
        customerDAO.deleteCustomer(1);
    }
}