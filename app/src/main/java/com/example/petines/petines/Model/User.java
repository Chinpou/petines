package com.example.petines.petines.Model;

import java.util.List;


public class  User  {

    private int id;
    private String username;
    private String password;
    private String emailAddress;
    private String firstName;
    private String residentAddress;
    private String contactNumber;
    private List<Order> orders;
    private String profileimg;



    public User() {
    }

    public User(String username, String password, String emailAddress, String firstName, String residentAddress, String profileimg) {
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.residentAddress = residentAddress;
        this.profileimg = profileimg;
    }

    public User(int id, String username, String password, String emailAddress, String firstName, String residentAddress, String contactNumber, List<Order> orders) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.firstName = firstName;
        this.residentAddress = residentAddress;
        this.contactNumber = contactNumber;
        this.orders = orders;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public int getUid() {
        return id;
    }

    public void setUid(int uid) {
        this.id = uid;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
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

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getResidentAddress() {
        return residentAddress;
    }

    public void setResidentAddress(String residentAddress) {
        this.residentAddress = residentAddress;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", firstName='" + firstName + '\'' +
                ", residentAddress='" + residentAddress + '\'' +
                ", contactNumber='" + contactNumber + '\'' +
                ", orders=" + orders +
                ", profileimg='" + profileimg + '\'' +
                '}';
    }
}
