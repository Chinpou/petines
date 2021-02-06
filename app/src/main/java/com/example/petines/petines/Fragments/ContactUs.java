package com.example.petines.petines.Fragments;

import com.orm.SugarRecord;


public class ContactUs extends SugarRecord<ContactUs> {

    String sender;
    String recipient;
    String date;

    public ContactUs() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
