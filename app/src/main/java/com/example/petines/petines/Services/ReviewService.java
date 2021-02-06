package com.example.petines.petines.Services;

import com.example.petines.petines.Model.Inquiry;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ReviewService {


    String BASE_URL = "http://192.168.1.9:8080/";
    @POST("reviews")
    Call<Inquiry> addReview(@Body Inquiry review);

    @GET("reviews")
    Call<List<Inquiry>> getAllInquiries();

    @GET("/reviews/{pid}")
    Call<List<Inquiry>> getReview(@Path("pid") int pid);

}
