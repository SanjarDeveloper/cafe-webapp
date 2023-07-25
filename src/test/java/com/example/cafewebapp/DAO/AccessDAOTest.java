package com.example.cafewebapp.DAO;

import com.example.cafewebapp.Entity.Users;
import org.junit.jupiter.api.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import static org.junit.jupiter.api.Assertions.*;

class AccessDAOTest {
    AccessDAO accessDAO = new AccessDAO();

    @Test
    void registerUserTest() {
        Users users = new Users();
        users.setId(2);
        users.setUser_type("user");
        users.setUsername("user");
        users.setPassword("user");
        users.setActive(true);
        assertTrue(accessDAO.registerUser(users));
        accessDAO.deleteUser(users.getId());
    }
    @Test
    void getUserByIdTest(){
        Users users = new Users();
        users.setId(3);
        users.setUser_type("user");
        users.setUsername("user");
        users.setPassword("user");
        users.setActive(true);
        accessDAO.registerUser(users);
        assertEquals(users.getId(), accessDAO.getUserById(3).getId());
        assertEquals(users.getUser_type(), accessDAO.getUserById(3).getUser_type());
        assertEquals(users.getUsername(), accessDAO.getUserById(3).getUsername());
        assertEquals(users.getPassword(), accessDAO.getUserById(3).getPassword());
        assertEquals(users.isActive(), accessDAO.getUserById(3).isActive());
        accessDAO.deleteUser(users.getId());
    }

    @Test
    void updateUserTest(){
        Users users = new Users();
        users.setId(4);
        users.setUser_type("user");
        users.setUsername("user");
        users.setPassword("user");
        users.setActive(true);
        accessDAO.registerUser(users);
        users.setUser_type("admin");
        users.setActive(false);
        assertTrue(accessDAO.updateUser(users));
        accessDAO.deleteUser(users.getId());
    }

    @Test
    void deleteUserTest(){
        Users users = new Users(5,"user","user","user",false);
        accessDAO.registerUser(users);
        assertTrue(accessDAO.deleteUser(users.getId()));
    }
}