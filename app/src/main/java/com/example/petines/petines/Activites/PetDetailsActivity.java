package com.example.petines.petines.Activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Activites.ApiClient;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetDetailsActivity extends AppCompatActivity {

    int id;
    ApiInterface apiInterface;
    String contactNumber;
    TextView petDescription, namePet, speciesPet, breedPet, GenderPet, BirthPet;
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.petdetails2);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        namePet = (TextView)findViewById(R.id.namePet);
        speciesPet = (TextView)findViewById(R.id.speciesPet);
        breedPet = (TextView)findViewById(R.id.breedPet);
        GenderPet = (TextView)findViewById(R.id.GenderPet);
        BirthPet = (TextView)findViewById(R.id.BirthPet);
        petDescription = (TextView)findViewById(R.id.petDescription);

        // receive data with intent
        Intent intent = getIntent();
        this.id = intent.getIntExtra("id", 1);
        //Toast.makeText(this, this.placeId, Toast.LENGTH_LONG).show();
        getPetsById(id);


    }

    public void getPetsById(int id){
        Call<Pets> call = apiInterface.getPetById(id);
        call.enqueue(new Callback<Pets>() {
            @Override
            public void onResponse(Call<Pets> call, Response<Pets> response) {
                //progressBar.setVisibility(View.GONE);
                Pets pet = response.body();
                Log.i(NavigationActivity.class.getSimpleName(), response.body().toString());
                namePet.setText(pet.getName());
                speciesPet.setText(pet.getSpecies());
                breedPet.setText(pet.getBreed());
                GenderPet.setText(pet.getGender());
                BirthPet.setText(pet.getBirth());
                petDescription.setText(pet.getDescription());
                contactNumber = pet.getUser().getContactNumber();
            }

            @Override
            public void onFailure(Call<Pets> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onClickCall(View view)
    {

        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        } else {
            callPhone();
        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    callPhone();
                }
            }
        }
    }

    private void callPhone()
    {

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + contactNumber));
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        }
    }


}
