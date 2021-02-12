package com.example.petines.petines.Model;

public class Favourite {

    private Integer fid;

    private User user;

    private Pets pets;

    private Boolean liked;

    public Favourite() {
    }

    public Favourite(User user, Pets pets, Boolean liked) {
        this.user = user;
        this.pets = pets;
        this.liked = liked;
    }

    public Integer getFid() {
        return fid;
    }

    public void setF_id(Integer fid) {
        this.fid = fid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Pets getPets() {
        return pets;
    }

    public void setPets(Pets pets) {
        this.pets = pets;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }
}
