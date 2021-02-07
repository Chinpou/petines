package com.example.petines.petines.Services;

import com.example.petines.petines.Model.OrderItem;
import com.example.petines.petines.Model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ProductsService {

    String BASE_URL = "http://192.168.1.104:8080/";

    @GET("products")
    Call<List<Product>> getProducts();


    @GET("products/{productId}")
    Call<Product> getProduct(@Path("productId") int productId);

    @POST("oitems")
    Call<OrderItem> addToCart(@Body OrderItem orderItem);

    @PUT("products")
    Call<Void> updateProducts(@Body OrderItemWrapper orderItemWrapper);
}
