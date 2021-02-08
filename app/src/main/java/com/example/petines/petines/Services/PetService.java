package com.example.petines.petines.Services;

import com.example.petines.petines.Adapters.BaseUrlAdapter;
import com.example.petines.petines.Model.OrderItem;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface PetService {

    BaseUrlAdapter adapterUrl= new BaseUrlAdapter();
    String BASE_URL = adapterUrl.getBASE_URL();

    @POST("petties")
    Call<Pets> addNewPet(@Body Pets pet);

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
}
