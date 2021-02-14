package com.example.petines.petines.Model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Pets implements Serializable {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("species")
    private String species;
    @SerializedName("breed")
    private String breed;
    @SerializedName("gender")
    private int gender;
    @SerializedName("birth")
    private String birth;
    @SerializedName("picture")
    private String picture;
    @SerializedName("love")
    private Boolean love;
    //@SerializedName("value")
    //private String value;
    @SerializedName("description")
    private String description;

    private User user;

    public Pets(String name, String species, String breed, int gender, String birth, String picture) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.birth = birth;
        this.picture = picture;
    }
    public Pets(String name, String species, String breed, int gender, String birth, String picture, String description) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.birth = birth;
        this.picture = picture;
        this.description = description;
    }

    public Pets(String name, String species, String breed, int gender, String birth, String picture, String description, User user) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.birth = birth;
        this.picture = picture;
        this.description = description;
        this.user = user;
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

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getLove() {
        return love;
    }

    public void setLove(Boolean love) {
        this.love = love;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
