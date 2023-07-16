package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Foods;
import com.example.cafewebapp.Entity.Users;
import jakarta.servlet.http.HttpSession;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public boolean registerCustomer(Customers customer) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO customers(name,contact,address,bonus,banned,active,balance,user_id) VALUES (?,?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getContact());
            statement.setString(3, customer.getAddress());
            statement.setBigDecimal(4, customer.getBonus());
            statement.setBoolean(5, customer.isBanned());
            statement.setBoolean(6, customer.isActive());
            statement.setBigDecimal(7, customer.getBalance());
            statement.setInt(8, customer.getUser_id().getId());
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Customers> getAllCustomers() {
        List<Customers> customers = new ArrayList<>();
        try {
            AccessDAO accessDAO = new AccessDAO();
            Connection conn = DBConnect.getConn();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM customers";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Customers customer = new Customers();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setContact(resultSet.getString(3));
                customer.setAddress(resultSet.getString(4));
                customer.setBonus(resultSet.getBigDecimal(5));
                customer.setBanned(resultSet.getBoolean(6));
                customer.setActive(resultSet.getBoolean(7));
                customer.setBalance(resultSet.getBigDecimal(8));
                int id = resultSet.getInt(9);
                Users user = accessDAO.getUserById(id);
                if (user != null) {
                    customer.setUser_id(user);
                }
                customers.add(customer);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }

    public Customers getCustomerById(int id) {
        Customers customer = null;
        try {
            AccessDAO accessDAO = new AccessDAO();
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM customers WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customer = new Customers();
                customer.setId(resultSet.getInt(1));
                customer.setName(resultSet.getString(2));
                customer.setContact(resultSet.getString(3));
                customer.setAddress(resultSet.getString(4));
                customer.setBonus(resultSet.getBigDecimal(5));
                customer.setBanned(resultSet.getBoolean(6));
                customer.setActive(resultSet.getBoolean(7));
                customer.setBalance(resultSet.getBigDecimal(8));
                int userid = resultSet.getInt(9);
                Users user = accessDAO.getUserById(userid);
                customer.setUser_id(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return customer;
    }

    public boolean updateCustomer(Customers customer) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "UPDATE customers SET name = ?, contact = ?, address = ?, bonus = ?, banned = ?, active = ?, balance = ?, user_id = ? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getContact());
            statement.setString(3, customer.getAddress());
            statement.setBigDecimal(4, customer.getBonus());
            statement.setBoolean(5, customer.isBanned());
            statement.setBoolean(6, customer.isActive());
            statement.setBigDecimal(7, customer.getBalance());
            statement.setInt(8, customer.getUser_id().getId());
            statement.setInt(9, customer.getId());
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean deleteCustomer(int id) {
        boolean isSuccess = false;
        try {
            String sql = "DELETE FROM customers WHERE id=?";
            AccessDAO accessDAO = new AccessDAO();
            Customers customerById = getCustomerById(id);

            Connection conn = DBConnect.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int i = ps.executeUpdate();
            if (i == 1) {
                isSuccess = true;
                boolean isUserDeleted = accessDAO.deleteUser(customerById.getUser_id().getId());
                if (!isUserDeleted){
                    isSuccess = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    public Customers getCustomerByUserId(int id){
        Customers customers = null;
        try{
            AccessDAO accessDAO = new AccessDAO();
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM customers WHERE user_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                customers = new Customers();
                customers.setId(resultSet.getInt(1));
                customers.setName(resultSet.getString(2));
                customers.setContact(resultSet.getString(3));
                customers.setAddress(resultSet.getString(4));
                customers.setBonus(resultSet.getBigDecimal(5));
                customers.setBanned(resultSet.getBoolean(6));
                customers.setActive(resultSet.getBoolean(7));
                customers.setBalance(resultSet.getBigDecimal(8));
                customers.setUser_id(accessDAO.getUserById(resultSet.getInt(9)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    }
    public void makeCustomerBanned(int customerId){
        try {
            Customers customerById = getCustomerById(customerId);
            if (customerById != null){
                customerById.setBanned(true);
                updateCustomer(customerById);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void minusAmountFromBalance(int customerId, Double amount) {
        try {
            Customers customerById = getCustomerById(customerId);
            if (customerById != null) {
                BigDecimal bonus = customerById.getBonus();
                customerById.setBonus(BigDecimal.valueOf(Double.parseDouble(String.valueOf(bonus)) - amount));
                updateCustomer(customerById);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
