package com.example.petines.petines.Activites;

import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.Model.Product;
import com.example.petines.petines.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {
/*
    @POST("get_pets.php")
    Call<List<Pets>> getPets();
 */

    @GET("petties")
    Call<List<Pets>> getPets();
    @GET("petties")
    Call<List<Commande>> getCommande();

    @POST("petties")
    Call<Pets> insertPet(@Body Pets p);

    @POST("petties/{Id}")
    Call<Pets> updatePet(
            @Path("id") int Id, @Body Pets petty);

    @DELETE("petties/delete/{Id}")
    Call<Pets> deletePet(
            @Path("id") int Id);

/*
    @GET("pets/{petsId}")
    Call<Pets> getPet(@Path("petsId") int petsId);

 */

/*
    @FormUrlEncoded
    @POST("add_pet.php")
    Call<Pets> insertPet(
            @Field("key") String key,
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed,
            @Field("gender") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_pet.php")
    Call<Pets> updatePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("name") String name,
            @Field("species") String species,
            @Field("breed") String breed,
            @Field("gender") int gender,
            @Field("birth") String birth,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("delete_pet.php")
    Call<Pets> deletePet(
            @Field("key") String key,
            @Field("id") int id,
            @Field("picture") String picture);

    @FormUrlEncoded
    @POST("update_love.php")
    Call<Pets> updateLove(
            @Field("key") String key,
            @Field("id") int id,
            @Field("love") boolean love);

 */

}