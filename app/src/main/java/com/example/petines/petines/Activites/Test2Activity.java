package com.example.petines.petines.Activites;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Activites.ApiClient;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test2Activity extends AppCompatActivity {

    TextView textview4;
    TextView textview5;

    String name, species;

    ApiInterface apiInterface;
    String contactNumber;
    int id;
    TextView petDescription, namePet, speciesPet, breedPet, GenderPet, BirthPet, ContactNumberPet, EmailPet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test2);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        //textview4 = (TextView) findViewById(R.id.textView4);
        //textview5 = (TextView) findViewById(R.id.textView5);

        namePet = (TextView)findViewById(R.id.namePet);
        speciesPet = (TextView)findViewById(R.id.speciesPet);
        breedPet = (TextView)findViewById(R.id.breedPet);
        GenderPet = (TextView)findViewById(R.id.GenderPet);
        BirthPet = (TextView)findViewById(R.id.BirthPet);
        petDescription = (TextView)findViewById(R.id.petDescription);
        ContactNumberPet = (TextView)findViewById(R.id.ContactNumberPet);
        EmailPet = (TextView)findViewById(R.id.EmailPet);


        Intent intent = getIntent();
        namePet.setText(intent.getStringExtra("name"));
        speciesPet.setText(intent.getStringExtra("species"));
        GenderPet.setText(intent.getStringExtra("gender"));
        BirthPet.setText(intent.getStringExtra("birth"));
        breedPet.setText(intent.getStringExtra("breed"));
        petDescription.setText(intent.getStringExtra("description"));
        ContactNumberPet.setText(intent.getStringExtra("contactNumber"));
        EmailPet.setText(intent.getStringExtra("EmailPet"));

    }
}