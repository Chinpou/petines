package com.example.petines.petines.Adapters;


import android.util.Log;

public class BaseUrlAdapter {
//    Utils utilIPv4 = new Utils();
//    String ipv4 = utilIPv4.getIPAddress(true);
    private final String BASE_URL = "http://192.168.1.16:8080";

    public String getBASE_URL() {
        return BASE_URL;
    }

    public BaseUrlAdapter() {
    }
}
