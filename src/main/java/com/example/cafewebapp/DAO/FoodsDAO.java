package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.Foods;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class FoodsDAO {
    public List<Foods> getAllFoods() {
        List<Foods> foods = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConn();
            Statement statement = conn.createStatement();
            String sql = "SELECT * FROM foods";
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Foods food = new Foods();
                food.setId(resultSet.getInt(1));
                food.setName(resultSet.getString(2));
                food.setDetails(resultSet.getString(3));
                food.setCategory(resultSet.getString(4));
                food.setPrice(resultSet.getBigDecimal(5));
                food.setActive(resultSet.getBoolean(6));
                foods.add(food);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return foods;
    }

    public boolean createFood(Foods food) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO foods(name,details,category,price,active) VALUES (?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, food.getName());
            statement.setString(2, food.getDetails());
            statement.setString(3, food.getCategory());
            statement.setBigDecimal(4, food.getPrice());
            statement.setBoolean(5, food.isActive());
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public Foods getFoodById(int id) {
        Foods food = null;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM foods WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                food = new Foods();
                food.setId(resultSet.getInt(1));
                food.setName(resultSet.getString(2));
                food.setDetails(resultSet.getString(3));
                food.setCategory(resultSet.getString(4));
                food.setPrice(resultSet.getBigDecimal(5));
                food.setActive(resultSet.getBoolean(6));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return food;
    }

    public boolean updateFood(Foods food){
        boolean f = false;
        try{
            String sql = "UPDATE foods SET name=?,details=?,category=?,price=?,active=? WHERE id=?";
            Connection conn = DBConnect.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,food.getName());
            ps.setString(2,food.getDetails());
            ps.setString(3,food.getCategory());
            ps.setBigDecimal(4, food.getPrice());
            ps.setBoolean(5, food.isActive());
            ps.setInt(6,food.getId());

            int i = ps.executeUpdate();
            if (i == 1){
                f = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }

    public boolean deleteFood(int id){
        boolean f= false;
        try{
            String sql = "DELETE FROM foods WHERE id=?";
            Connection conn = DBConnect.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);

            int i = ps.executeUpdate();
            if(i == 1){
                f = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return f;
    }
}
