package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.Payments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    public static int paymentId = 1;

    public List<Payments> getAllPayments(){
        List<Payments> payments = new ArrayList<>();
        try {
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM payments";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Payments payment = new Payments();
                payment.setId(resultSet.getInt(1));
                payment.setAmount(resultSet.getBigDecimal(2));
                payment.setType(resultSet.getString(3));
                payments.add(payment);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return payments;
    }

    public boolean AddPayment(Payments payment){
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO payments(id,amount,type) VALUES(?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,payment.getId());
            statement.setBigDecimal(2, payment.getAmount());
            statement.setString(3, payment.getType());
            int i = statement.executeUpdate();
            if (i == 1){
                paymentId += 1;
                isSuccess = true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return isSuccess;
    }

    public Payments getPaymentById(int id){
        Payments payment = new Payments();
        try {
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM payments WHERE id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                payment.setId(resultSet.getInt(1));
                payment.setAmount(resultSet.getBigDecimal(2));
                payment.setType(resultSet.getString(3));
                payment.setTime(resultSet.getTimestamp(4));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return payment;
    }
}
