package com.example.petines.petines.Model;

import java.util.List;


public class Favourite  {

        private Favourite fid;
        private List<Pets> Pets;
        private User user;


    public Favourite(Favourite fid, List<Pets> pets, User user) {
        this.fid = fid;
        this.Pets = pets;
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

    public List<Pets> getpOrderItems() {
        return Pets;
    }

    public void setpOrderItems(List<Pets> pOrderItems) {
        this.Pets = pOrderItems;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
