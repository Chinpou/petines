package com.example.petines.petines.Activites;

import com.example.petines.petines.Model.Favourite;
import com.example.petines.petines.Model.Pets;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface ApiInterface {

    @GET("petties")
    Call<List<Pets>> getPets();

    @GET("petties/connected/{username}")
    public Call<List<Object>> getAllPetsForConnectedUser(@Path("username") String username);

    @GET("favourites/user/{username}")
    Call<List<Favourite>> getMyPetWhishList(@Path("username") String username);

    @POST("petties/add")
    Call<Pets> insertPet(@Body Pets p);


    @POST("petties/{Id}")
    Call<Pets> updatePet(
            @Path("id") int Id, @Body Pets petty);

    @POST("/favourites/{fav_Id}")
    Call<Favourite> updateLoveFav(
            @Path("fav_Id") int fav_Id,
            @Body boolean love);

    @DELETE("petties/delete/{Id}")
    Call<Pets> deletePet(
            @Path("id") int Id);


    @POST("/petties/updateLove/{Id}")
    Call<Pets> updateLove(
            @Path("Id") int Id,
            @Body boolean love);

    @GET("/petties/petty/Id}")
    Call<Pets> getPetById(
            @Path("Id") int Id);

    @Multipart
    @POST("uploadPictures")
    Call<ResponseBody> uploadPictures(
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);

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