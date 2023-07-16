package com.example.cafewebapp.Entity.DTO;

import com.example.cafewebapp.Entity.Foods;

public class OrderDetailsDTO {
    private Foods food;
    private int amount;

    public OrderDetailsDTO(Foods food, int amount) {
        this.food = food;
        this.amount = amount;
    }

    public OrderDetailsDTO() {
    }

    public Foods getFood() {
        return food;
    }

    public void setFood(Foods food) {
        this.food = food;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
