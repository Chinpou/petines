
package com.example.petines.petines.Services;

import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CommandeService {
    String BASE_URL = "http://192.168.11.102:8080/";

    @GET("commandes/{username}")
    Call<List<Commande>>getCommandesByUser(@Path("username") String username);

    @POST("commandes/add/{username}")
    Call<Commande> addNewCommande(@Path("username") String username , @Body Commande commande);


    //@GET("login")
    //Call<List<User>> getAllUsers();

   // @POST("/livraison")
    //Call<Commande> addCommande(@Body Commande C);

    //@PUT("/users/{username}")
    //Call<User> updateUser(@Path("username") String username, @Body User user );
}
