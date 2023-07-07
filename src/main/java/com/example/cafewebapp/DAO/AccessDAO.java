package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AccessDAO {
    public boolean registerUser(Users user) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO users(id,user_type, username, password, active) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,user.getId());
            statement.setString(2, user.getUser_type());
            statement.setString(3, user.getUsername());
            statement.setString(4, user.getPassword());
            statement.setBoolean(5, user.isActive());
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
