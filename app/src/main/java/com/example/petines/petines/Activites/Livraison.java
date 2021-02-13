package com.example.petines.petines.Activites;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.petines.petines.Adapters.Adapter;
import com.example.petines.petines.Fragments.CommandeFragment;
import com.example.petines.petines.Fragments.FavouritesFragment;
import com.example.petines.petines.Fragments.Home2Fragment;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Fragments.ManageAccountFragment;
import com.squareup.picasso.Picasso;

import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class Livraison extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ApiInterface apiInterface;
    String username;
    String DATE;
    String Adresse;
    User Nom_User;
    Pets Nom_Pet;
    String statut;


    EditText date;
    EditText adresse;
    Button valider;
    Button commandes;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_livraison);
        date = findViewById(R.id.DateLivrer);
        adresse = findViewById(R.id.adresse);
        valider = findViewById(R.id.valider);
        commandes = findViewById(R.id.commande);


        Intent intent = getIntent();
        username = intent.getStringExtra("username");

        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                DATE = date.getText().toString();
                Adresse = adresse.getText().toString();
                String Nom_Pet = getIntent().getStringExtra("Nom_Pet");
                String Nom_User = getIntent().getStringExtra("Nom_User");





            }
        });
    }

    //public void validerCommande(View view) {
    //Intent intent = new Intent(Livraison.this, ToActivity.class);
    //startActivity(intent);
    //}



    public void MesCommandes(View view) {
        Intent intent = new Intent(Livraison.this, CommandeFragment.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}