package com.example.petines.petines.Fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Activites.ApiInterface;
import com.example.petines.petines.Activites.NavigationActivity;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PetDetailsFragment extends Fragment {

    int id;
    ApiInterface apiInterface;
    String contactNumber;


    TextView petDescription, namePet, speciesPet, breedPet, GenderPet, BirthPet;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final Bundle bdl = getArguments();
        id = bdl.getInt("pet_id");


        //SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        //final String USERNAME = sharedPreferences.getString("username", "");

        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.petdetails, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        namePet = (TextView)view.findViewById(R.id.namePet);
        speciesPet = (TextView)view.findViewById(R.id.speciesPet);
        breedPet = (TextView)view.findViewById(R.id.breedPet);
        GenderPet = (TextView)view.findViewById(R.id.GenderPet);
        BirthPet = (TextView)view.findViewById(R.id.BirthPet);
        petDescription = (TextView)view.findViewById(R.id.petDescription);

        getPetsById(id);

        return view;
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
                //contactNumber = pet.getUser().getContactNumber();
            }

            @Override
            public void onFailure(Call<Pets> call, Throwable t) {
                Toast.makeText(getContext(), "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

/*
    public void onResume() {
        super.onResume();
        getPetsById(this.id);
    }
 */

}
