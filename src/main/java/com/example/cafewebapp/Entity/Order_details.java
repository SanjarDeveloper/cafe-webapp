package com.example.cafewebapp.Entity;

import jakarta.persistence.ManyToOne;

public class Order_details {
    @ManyToOne
    private Orders order_id;
    @ManyToOne
    private Foods food_id;

    private int amount;
    public Order_details() {
    }

    public Order_details(Orders order_id, Foods food_id, int amount) {
        this.order_id = order_id;
        this.food_id = food_id;
        this.amount = amount;
    }

    public Orders getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Orders order_id) {
        this.order_id = order_id;
    }

    public Foods getFood_id() {
        return food_id;
    }

    public void setFood_id(Foods food_id) {
        this.food_id = food_id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
