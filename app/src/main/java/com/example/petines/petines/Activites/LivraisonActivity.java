package com.example.petines.petines.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.CommandeService;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LivraisonActivity extends AppCompatActivity{

    String username;
    String Date;
    String Adresse;
    Button  BtnOrder;
    Pets pet;



    EditText date;
    EditText adresse;
    Button valider;


    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livraison);
        date = findViewById(R.id.DateLivrer);
        adresse = findViewById(R.id.adresse);
        valider = findViewById(R.id.valider);
        BtnOrder = findViewById(R.id.commande);
        username = getIntent().getStringExtra("username");
        pet = (Pets) getIntent().getSerializableExtra("pet");






        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Date = date.getText().toString();
                Adresse = adresse.getText().toString();


                Commande commande = new Commande(pet,"en cours",Date, Adresse);
                sendCommande( username, commande);

            }

        });

    }
    public void sendCommande(String username, Commande commande ) {

        CommandeService commandeService = ApiClient.getApiClient().create(CommandeService.class);
        Call<Commande> call = commandeService.addNewCommande(username,commande);
        call.enqueue(new Callback<Commande>(){
            @Override
            public void onResponse(Call<Commande> call, Response<Commande> response) {

                Toast.makeText(getApplicationContext(),
                        "New order added ",
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Commande> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "fail"+t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }



}