package com.example.petines.petines.Model;

import com.google.gson.annotations.SerializedName;

public class Pets {

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
    @SerializedName("description")
    private String description;
    @SerializedName("lat")
    private String lat;
    @SerializedName("lon")
    private String lon;
    @SerializedName("uid")
    private String uid;


    public Pets(int id, String name, String species, String breed, int gender, String birth, String picture, Boolean love, String description, String lat, String lon, String uid) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.birth = birth;
        this.picture = picture;
        this.love = false;
        this.description = description;
        this.lat = lat;
        this.lon = lon;
        this.uid = uid;
    }
    public Pets(String name, String species, String breed, int gender, String description, String birth, String picture) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.gender = gender;
        this.birth = null;
        this.picture = picture;
        this.love = false;
        this.description = "description";
        this.lat = null;
        this.lon = null;
        this.uid = null;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
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
    public String getLat() {
        return lat;
    }
    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLon() {
        return lon;
    }
    public void setLon(String lon) {
        this.lon = lon;
    }
    public String getUid() {
        return uid;
    }
    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Pets{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", breed='" + breed + '\'' +
                ", gender='" + gender + '\'' +
                ", birth='" + birth + '\'' +
                ", picture='" + picture + '\'' +
                ", love=" + love +
                ", description='" + description + '\'' +
                '}';
    }
}
