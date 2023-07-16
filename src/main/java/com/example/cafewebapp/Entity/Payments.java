package com.example.cafewebapp.Entity;

import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Payments {
    private int id;
    private BigDecimal amount;
    private String type;

    private Timestamp time;

    public Payments() {
    }

    public Payments(int id, BigDecimal amount, String type, Timestamp time) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }
}
