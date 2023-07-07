package com.example.cafewebapp.Entity;

import com.example.cafewebapp.Entity.Enum.UserType;

public class Users {
    private int id;
    private String user_type;
    private String username;
    private String password;
    private boolean isActive;

    public Users() {
    }

    public Users(int id, String user_type, String username, String password, boolean isActive) {
        this.id = id;
        this.user_type = user_type;
        this.username = username;
        this.password = password;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
