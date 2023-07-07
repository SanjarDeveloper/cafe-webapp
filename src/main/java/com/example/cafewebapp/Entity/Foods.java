package com.example.cafewebapp.Entity;

import java.math.BigDecimal;

public class Foods {
    private int id;
    private String name;
    private String details;
    private String category;
    private BigDecimal price;
    private boolean active;

    public Foods() {
    }

    public Foods(int id, String name, String details, String category, BigDecimal price, boolean active) {
        this.id = id;
        this.name = name;
        this.details = details;
        this.category = category;
        this.price = price;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
