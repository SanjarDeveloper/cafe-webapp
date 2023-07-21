package com.example.cafewebapp.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.sql.Timestamp;

public class Orders {
    private int id;
    @ManyToOne
    private Customers customer_id;
    @OneToMany
    private Payments payment_id;
    private String status;
    private Timestamp time;
    private String feedback;
    private int rating;
    private Timestamp takeaway_time;

    public Orders() {
    }

    public Orders(int id, Customers customer_id, Payments payment_id, String status, Timestamp time, String feedback, int rating, Timestamp takeaway_time) {
        this.id = id;
        this.customer_id = customer_id;
        this.payment_id = payment_id;
        this.status = status;
        this.time = time;
        this.feedback = feedback;
        this.rating = rating;
        this.takeaway_time = takeaway_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customers getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Customers customer_id) {
        this.customer_id = customer_id;
    }

    public Payments getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(Payments payment_id) {
        this.payment_id = payment_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Timestamp getTakeaway_time() {
        return takeaway_time;
    }

    public void setTakeaway_time(Timestamp takeaway_time) {
        this.takeaway_time = takeaway_time;
    }
}
