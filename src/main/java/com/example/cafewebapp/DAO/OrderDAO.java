package com.example.cafewebapp.DAO;

import com.example.cafewebapp.DB.DBConnect;
import com.example.cafewebapp.Entity.*;
import com.example.cafewebapp.Entity.DTO.OrderDetailsDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    public static int orderId = 1;

    public boolean addOrder(Orders order) {
        boolean isSuccess = false;
        try {
            Connection conn = DBConnect.getConn();
            String sql = "INSERT INTO orders(id,customer_id,payment_id,status,feedback,rating) VALUES(?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getCustomer_id().getId());
            statement.setInt(3, order.getPayment_id().getId());
            statement.setString(4, order.getStatus());
            statement.setString(5, order.getFeedback());
            statement.setInt(6, order.getRating());
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
            /*if (ordersList.size() == 1) {

            } else if (ordersList.size() == 2) {

            } else if (ordersList.size() >= 3) {

            }*/
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordersList;
    }

}
