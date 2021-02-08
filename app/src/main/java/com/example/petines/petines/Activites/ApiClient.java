package com.example.petines.petines.Activites;

import com.example.petines.petines.Adapters.BaseUrlAdapter;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class ApiClient {
//    BaseUrlAdapter adapterUrl= new BaseUrlAdapter();
//    String adaptedUrl = adapterUrl.getBASE_URL();
    private static final String BASE_URL = "http://192.168.1.14:8080";

    private static Retrofit retrofit;

    static Retrofit getApiClient(){

        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}