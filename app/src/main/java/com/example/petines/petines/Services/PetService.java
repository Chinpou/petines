package com.example.petines.petines.Services;

import com.example.petines.petines.Adapters.BaseUrlAdapter;
import com.example.petines.petines.Model.Pets;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PetService {

    BaseUrlAdapter adapterUrl= new BaseUrlAdapter();
    String BASE_URL = adapterUrl.getBASE_URL();

    @POST("petties/add/{username}")
    Call<Pets> addNewPet(@Path("username") String username , @Body Pets pet);

/*
    @Multipart
    @POST("addPet")
    Call<ResponseBody> addPet(
            @Part ("pet") RequestBody pet,
            @Part MultipartBody.Part file
    );

    @Multipart
    @POST("uploadPictures")
    Call<ResponseBody> uploadPictures(
            @Part MultipartBody.Part file1,
            @Part MultipartBody.Part file2);

 */
}
