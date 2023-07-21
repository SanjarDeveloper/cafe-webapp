package com.example.cafewebapp.Entity;

import com.example.cafewebapp.DAO.AccessDAO;
import com.example.cafewebapp.DAO.CustomerDAO;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

import java.math.BigDecimal;

public class InitAdmin implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
/*        CustomerDAO customerDAO = new CustomerDAO();
        AccessDAO accessDAO = new AccessDAO();
        Users users = new Users();
        users.setId(1);
        users.setUser_type("admin");
        users.setUsername("admin");
        users.setPassword("admin");
        users.setActive(true);
        boolean isRegisteredUser = accessDAO.registerUser(users);
        if (isRegisteredUser){
            Customers customers = new Customers();
            customers.setId(1);
            customers.setName("Admin");
            customers.setContact("");
            customers.setAddress("");
            customers.setBonus(BigDecimal.ZERO);
            customers.setBanned(false);
            customers.setActive(true);
            customers.setBalance(BigDecimal.ZERO);
            customers.setUser_id(users);
            customerDAO.registerCustomer(customers);
        }*/
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
