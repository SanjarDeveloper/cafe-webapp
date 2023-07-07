package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.Customers;
import com.example.cafewebapp.Entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
            if (i == 1){
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
