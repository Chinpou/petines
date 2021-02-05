package com.example.petines.petines.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.petines.petines.Activites.MainActivity;
import com.example.petines.petines.Activites.NavActivity;
//import com.example.petines.petines.Activites.NavigationActivity;
import com.example.petines.petines.R;

public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT=1000;
    String sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_splash2);

        new Handler().postDelayed(new Runnable(){

            @Override
            public void run() {

                SharedPreferences sharedPreferences=getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
                sp= sharedPreferences.getString("username","");


                if(sp.length()==0){

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(SplashActivity.this, NavActivity.class);
                    startActivity(intent);
                }

            }
        },SPLASH_TIME_OUT);




    }





}
