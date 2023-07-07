package com.example.cafewebapp.Entity;

import jakarta.persistence.OneToOne;

import java.math.BigDecimal;

public class Customers {
    private int id;
    private String name;
    private String contact;
    private String address;
    private BigDecimal bonus;
    private boolean isBanned;
    private boolean active;
    private BigDecimal balance;
    @OneToOne
    private Users user_id;

    public Customers() {
    }

    public Customers(int id, String name, String contact, String address, BigDecimal bonus, boolean isBanned, boolean active, BigDecimal balance, Users user_id) {
        this.id = id;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.bonus = bonus;
        this.isBanned = isBanned;
        this.active = active;
        this.balance = balance;
        this.user_id = user_id;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBonus() {
        return bonus;
    }

    public void setBonus(BigDecimal bonus) {
        this.bonus = bonus;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Users getUser_id() {
        return user_id;
    }

    public void setUser_id(Users user_id) {
        this.user_id = user_id;
    }
}
