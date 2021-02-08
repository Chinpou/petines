package com.example.petines.petines.Services;

import com.example.petines.petines.Adapters.BaseUrlAdapter;
import com.example.petines.petines.Model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService {

    BaseUrlAdapter adapterUrl= new BaseUrlAdapter();
    String BASE_URL = adapterUrl.getBASE_URL();

    @GET("login/{username}")
    Call<User> getUser(@Path("username") String username);


    @GET("login")
    Call<List<User>> getAllUsers();

    @POST("/login")
    Call<User> addUser(@Body User u);

    @PUT("/users/{username}")
    Call<User> updateUser(@Path("username") String username, @Body User user );
}
