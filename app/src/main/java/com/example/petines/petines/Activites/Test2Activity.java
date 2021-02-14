package com.example.petines.petines.Activites;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Activites.ApiClient;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Test2Activity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    TextView textview4;
    TextView textview5;

    String name, species;

    ApiInterface apiInterface;
    String contactNumber;
    int id;
    String emailproprio, phoneNumber;
    TextView petDescription, namePet, speciesPet, breedPet, GenderPet, BirthPet, ContactNumberPet, EmailPet;
    Button emailBtn, callBtn, livraisonBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Confirmer la commande");
        setContentView(R.layout.activity_test2);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        namePet = (TextView) findViewById(R.id.namePet);
        speciesPet = (TextView) findViewById(R.id.speciesPet);
        breedPet = (TextView) findViewById(R.id.breedPet);
        GenderPet = (TextView) findViewById(R.id.GenderPet);
        BirthPet = (TextView) findViewById(R.id.BirthPet);
        petDescription = (TextView) findViewById(R.id.petDescription);
        ContactNumberPet = (TextView) findViewById(R.id.ContactNumberPet);
        EmailPet = (TextView) findViewById(R.id.EmailPet);

        Intent intent = getIntent();
        namePet.setText(intent.getStringExtra("name"));
        speciesPet.setText(intent.getStringExtra("species"));
        GenderPet.setText(intent.getStringExtra("gender"));
        BirthPet.setText(intent.getStringExtra("birth"));
        breedPet.setText(intent.getStringExtra("breed"));
        petDescription.setText(intent.getStringExtra("description"));
        //phoneNumber = intent.getStringExtra("contactNumber");
        ContactNumberPet.setText(intent.getStringExtra("contactNumber"));
        emailproprio = intent.getStringExtra("EmailPet");
        EmailPet.setText(emailproprio);

        emailBtn = (Button) findViewById(R.id.emailBtn);
        emailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //String bodyText = "My email content";

                String mailto = "mailto:" + emailproprio;

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse(mailto));

                try {
                    startActivity(emailIntent);
                } catch (ActivityNotFoundException e) {
                    //TODO: Handle case where no email app is available
                }
            }
        });

        callBtn = (Button) findViewById(R.id.callBtn);
        callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //callphone();
                onClickCallProprio(v);
            }
        });

        livraisonBtn = (Button) findViewById(R.id.livraisonBtn);
        livraisonBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Livraison ACTION", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Test2Activity.this, TEMP_LocationActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callphone();
                }
            }
        }
    }

    public void onClickCallProprio(View view)
    {
        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callphone();
        }
    }

    void callphone(){

        phoneNumber=ContactNumberPet.getText().toString();
        Intent intent2 = new Intent(Intent.ACTION_CALL);
        intent2.setData(Uri.parse("tel:" + phoneNumber));
        try {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                startActivity(intent2);
            }
        } catch (ActivityNotFoundException e) {
            //TODO: Handle case where no email app is available
        }
    }

}