package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.*;
import com.example.cafewebapp.Entity.DTO.OrderDetailsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static int orderId = 1;
    public static String banStatus = null;

    public boolean addOrder(Orders order) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO orders(id,customer_id,payment_id,status,feedback,rating,takeaway_time) VALUES(?,?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getCustomer_id().getId());
            statement.setInt(3, order.getPayment_id().getId());
            statement.setString(4, order.getStatus());
            statement.setString(5, order.getFeedback());
            statement.setInt(6, order.getRating());
            statement.setTimestamp(7, order.getTakeaway_time());
            int i = statement.executeUpdate();
            if (i == 1) {
                orderId += 1;
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Orders> getAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            PaymentDAO paymentDAO = new PaymentDAO();
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM orders";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Orders orders = new Orders();
                orders.setId(resultSet.getInt(1));
                Customers customerById = customerDAO.getCustomerById(resultSet.getInt(2));
                orders.setCustomer_id(customerById);
                Payments paymentById = paymentDAO.getPaymentById(resultSet.getInt(3));
                orders.setPayment_id(paymentById);
                orders.setStatus(resultSet.getString(4));
                orders.setTime(resultSet.getTimestamp(5));
                orders.setFeedback(resultSet.getString(6));
                orders.setRating(resultSet.getInt(7));
                orders.setTakeaway_time(resultSet.getTimestamp(8));
                ordersList.add(orders);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public Orders getOrderById(int id) {
        Orders orders = new Orders();
        try {
            CustomerDAO customerDAO = new CustomerDAO();
            PaymentDAO paymentDAO = new PaymentDAO();
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM orders WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.setId(resultSet.getInt(1));
                Customers customerById = customerDAO.getCustomerById(resultSet.getInt(2));
                orders.setCustomer_id(customerById);
                Payments paymentById = paymentDAO.getPaymentById(resultSet.getInt(3));
                orders.setPayment_id(paymentById);
                orders.setStatus(resultSet.getString(4));
                orders.setTime(resultSet.getTimestamp(5));
                orders.setFeedback(resultSet.getString(6));
                orders.setRating(resultSet.getInt(7));
                orders.setTakeaway_time(resultSet.getTimestamp(8));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }


    public boolean createOrderDetails(Orders order, Foods food, int amount) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO order_details (order_id,food_id,amount) VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setInt(2, food.getId());
            statement.setInt(3, amount);
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<OrderDetailsDTO> getOrderDetailsByOrderId(int orderId) {
        List<OrderDetailsDTO> orderDetails = new ArrayList<>();
        try {
            FoodsDAO foodsDAO = new FoodsDAO();
            Connection conn = DBConnect.getConn();
            String sql = "SELECT * FROM order_details WHERE order_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();
                Foods food = foodsDAO.getFoodById(resultSet.getInt(2));
                orderDetailsDTO.setFood(food);
                orderDetailsDTO.setAmount(resultSet.getInt(3));
                orderDetails.add(orderDetailsDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public boolean updateOrderStatus(Orders orders) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String status = orders.getStatus();
            String sql = "UPDATE orders SET status = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, status);
            statement.setInt(2, orders.getId());
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public List<Orders> getAllNotTakenOrders(int customerId) {
        List<Orders> ordersList = new ArrayList<>();
        try {
            for (Orders allOrder : getAllOrders()) {
                if (allOrder.getStatus().equals("Order Not Taken") && allOrder.getCustomer_id().getId() == customerId) {
                    ordersList.add(allOrder);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    public boolean addFeedbackandRating(String feedback,int rating,int orderId) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "UPDATE orders SET feedback = ?, rating = ? WHERE id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, feedback);
            statement.setInt(2, rating);
            statement.setInt(3, orderId);
            int i = statement.executeUpdate();
            if (i == 1) {
                isSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isSuccess;
    }

    public boolean deleteOrder(int id){
        boolean f= false;
        try{
            String sql = "DELETE FROM orders WHERE id = ?";
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
