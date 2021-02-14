package com.example.petines.petines.Activites;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.CommandeService;
import com.example.petines.petines.Services.PetService;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Livraison extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ApiInterface apiInterface;
    String username;
    String Date;
    String Adresse;
    User Nom_User;
    Pets Nom_Pet;
    String statut;
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




        //BtnOrder.setOnClickListener(new View.OnClickListener() {
            /*@Override
            public void onClick(View view) {
                //Intent intent = new Intent(getBaseContext(), CommandeFragment.class);
                //intent.putExtra("id_pet", namePet);
                //intent.putExtra("Nom_User", );
                //startActivity(intent);
                Fragment fragment = new CommandeFragment();
                Bundle bdl = new Bundle(4);
                bdl.putString("username", username);
                fragment.setArguments(bdl);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });*/


        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Date = date.getText().toString();
                Adresse = adresse.getText().toString();
               /* String Nom_Pet = getIntent().getStringExtra("Nom_Pet");
                String Nom_User = getIntent().getStringExtra("Nom_User");*/

               // Log.i("Submit Click", pet.toString());


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
                //Log.i("OnResponse:", response.body().toString());
                Toast.makeText(getApplicationContext(),
                        "Nouvelle commande ajouté "/*+response.body().getPetName()*/,
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

    //public void validerCommande(View view) {
    //Intent intent = new Intent(Livraison.this, ToActivity.class);
    //startActivity(intent);
    //}





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}