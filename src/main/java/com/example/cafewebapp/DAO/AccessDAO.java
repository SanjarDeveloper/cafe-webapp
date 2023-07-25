package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.Foods;
import com.example.cafewebapp.Entity.Users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

    public List<Users> getAllUsers(){
        List<Users> users = new ArrayList<>();
        try{
            Connection connection = DBConnect.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
            while (resultSet.next()){
                Users user = new Users();
                user.setId(resultSet.getInt(1));
                user.setUser_type(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setActive(resultSet.getBoolean(5));
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public List<Users> getAllActiveUsers(){
        List<Users> users = new ArrayList<>();
        try{
            Connection connection = DBConnect.getConn();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users WHERE active = true");
            while (resultSet.next()){
                Users user = new Users();
                user.setId(resultSet.getInt(1));
                user.setUser_type(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setActive(resultSet.getBoolean(5));
                users.add(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }
    public Users getUserById(int id) {
        Users user = null;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = new Users();
                user.setId(resultSet.getInt(1));
                user.setUser_type(resultSet.getString(2));
                user.setUsername(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setActive(resultSet.getBoolean(5));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


    public boolean updateUser(Users user) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "UPDATE users SET user_type = ?, username = ?, password = ?, active = ? WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, user.getUser_type());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            statement.setBoolean(4, user.isActive());
            statement.setInt(5, user.getId());
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
    public boolean deleteUser(int id){
        boolean isSuccess= false;
        try{
            String sql = "DELETE FROM users WHERE id=?";
            Connection conn = DBConnect.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            int i = ps.executeUpdate();
            if(i == 1){
                isSuccess = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }
}
