package com.example.petines.petines.Model;

import java.util.List;


public class Favourite  {

        private Favourite fid;
        private List<Product> products;
        private User user;


    public Favourite(Favourite fid, List<Product> products, User user) {
        this.fid = fid;
        this.products = products;
        this.user = user;
    }

    public Favourite() {
    }

    public Favourite getFid() {
        return fid;
    }

    public void setFid(Favourite fid) {
        this.fid = fid;
    }

    public List<Product> getpOrderItems() {
        return products;
    }

    public void setpOrderItems(List<Product> pOrderItems) {
        this.products = pOrderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
