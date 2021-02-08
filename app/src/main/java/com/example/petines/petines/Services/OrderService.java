package com.example.petines.petines.Services;

import com.example.petines.petines.Adapters.BaseUrlAdapter;
import com.example.petines.petines.Model.Order;
import com.example.petines.petines.Model.OrderItem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface OrderService {

    BaseUrlAdapter adapterUrl= new BaseUrlAdapter();
    String BASE_URL = adapterUrl.getBASE_URL();


    @POST("oitems")
    Call<OrderItem> addToCart(@Body OrderItem orderItem);

    @GET("orders/{username}")
    Call<List<Order>> getPurcahsedItems(@Path("username") String username);

    @POST("orders/{username}")
    Call<Void>  orderConfirmed(@Path("username") String username,@Body Order order);

}
