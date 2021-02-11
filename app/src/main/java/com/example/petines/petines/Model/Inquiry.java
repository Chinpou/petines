package com.example.petines.petines.Model;

public class Inquiry {


    private int rid;
     private String review;
     private String date;


    Pets pet_id;


    User user;

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Pets getPet_id() {
        return pet_id;
    }

    public void setPet_id(Pets pet_id) {
        this.pet_id = pet_id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
