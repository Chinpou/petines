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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Model.Favourite;
import com.example.petines.petines.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Test2Activity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    TextView textview4;
    TextView textview5;

    String name, species;

    ApiInterface apiInterface;
    String contactNumber;
    int id;
    List<Favourite> favList;
    String emailproprio, phoneNumber;
    TextView petDescription, namePet, speciesPet, breedPet, GenderPet, BirthPet, ContactNumberPet, EmailPet;
    TextView Textfavid;
    Button emailBtn, callBtn, livraisonBtn;
    ImageView lovePet;
    Boolean liked = Boolean.FALSE;
    int pet_id;
    TextView Textpetid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Confirmer la commande");
        setContentView(R.layout.activity_test2);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        lovePet = (ImageView)findViewById(R.id.lovePet);
        namePet = (TextView) findViewById(R.id.namePet);
        speciesPet = (TextView) findViewById(R.id.speciesPet);
        breedPet = (TextView) findViewById(R.id.breedPet);
        GenderPet = (TextView) findViewById(R.id.GenderPet);
        BirthPet = (TextView) findViewById(R.id.BirthPet);
        petDescription = (TextView) findViewById(R.id.petDescription);
        ContactNumberPet = (TextView) findViewById(R.id.ContactNumberPet);
        EmailPet = (TextView) findViewById(R.id.EmailPet);
        Textfavid = (TextView)findViewById(R.id.Textfavid);
        Textpetid = (TextView)findViewById(R.id.Textpetid);

        Intent intent = getIntent();
        final String petTitle = intent.getStringExtra("name");
        namePet.setText(petTitle);
        pet_id = intent.getIntExtra("id", 1);
        Textpetid.setText(String.valueOf(pet_id));
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
//
        Call<List<Favourite>> call = apiInterface.getMyPetWhishList("zouga");
        call.enqueue(new Callback<List<Favourite>>() {
            @Override
            public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {
                favList = response.body();
                for(int i=0; i<favList.size(); i++){
                    if(favList.get(i).getPets().getName().equals(petTitle)){
                            liked = Boolean.TRUE;
                            lovePet.setImageResource(R.drawable.likeon);
                            Textfavid.setText(favList.get(i).getFid().toString());
                            Toast.makeText(getApplicationContext(), " this user already already add this pet to wishlist",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }

                    else {
                        lovePet.setImageResource(R.drawable.likeof);

                    }
                }
            }
            @Override
            public void onFailure(Call<List<Favourite>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });




//
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

    public void onClickLove(View view) {

        if (liked){
            lovePet.setImageResource(R.drawable.likeof);
            Toast.makeText(getApplicationContext(), "making off",
                    Toast.LENGTH_SHORT).show();
            updateLove("update_love", Integer.parseInt(Textfavid.getText().toString()), false);

        } else {
            lovePet.setImageResource(R.drawable.likeon);
            updateLoveTrue("update_love", pet_id, "zouga");
        }
    }


    public void updateLove(final String key, final int fav_id, Boolean love){

        Call<Favourite> call = apiInterface.updateLoveFav(fav_id, love);

        call.enqueue(new Callback<Favourite>() {
            @Override
            public void onResponse(Call<Favourite> call, Response<Favourite> response) {

                //Log.i("TAG", "Response "+response.toString());

                Favourite fav1 = response.body();
                //Log.i("TAG", response.body().getPets().getName());

                if (fav1.getLiked() == TRUE){
                    Toast.makeText(getApplicationContext(), "Love TRUE", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Love FALSE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Favourite> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void updateLoveTrue(final String key, final int pet_id, String username){

        Call<Favourite> call = apiInterface.AddFav(username, pet_id);

        call.enqueue(new Callback<Favourite>() {
            @Override
            public void onResponse(Call<Favourite> call, Response<Favourite> response) {
                Favourite fav1 = response.body();
                Toast.makeText(getApplicationContext(), "Love TRUE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Favourite> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}