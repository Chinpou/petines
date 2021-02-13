package com.example.petines.petines.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.petines.petines.Activites.AddActivity;
import com.example.petines.petines.Activites.NavigationActivity;
import com.example.petines.petines.Activites.Test2Activity;
import com.example.petines.petines.Activites.ApiInterface;
import com.example.petines.petines.Adapters.Adapter;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.Boolean.TRUE;

public class Home2Fragment extends Fragment {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private Adapter adapter;
    private List<Pets> petsList;
    ApiInterface apiInterface;
    Adapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.activity_home2, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        listener = new Adapter.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, final int position) {
/*
                PetDetailsFragment f = new PetDetailsFragment();

                int Id = petsList.get(position).getId();
                Bundle bdl = new Bundle(9);
                bdl.putInt("pet_id", Id);
                f.setArguments(bdl);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();

 */
/*
                GridviewItemFragment f = new GridviewItemFragment();

                String name = petsList.get(position).getName();
                Bundle bdl = new Bundle(4);
                bdl.putString("name_petty", name);
                f.setArguments(bdl);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();

 */
                Intent intent = new Intent(getActivity(), Test2Activity.class);
                intent.putExtra("id", petsList.get(position).getId());
                intent.putExtra("name", petsList.get(position).getName());
                intent.putExtra("species", petsList.get(position).getSpecies());
                intent.putExtra("breed", petsList.get(position).getBreed());
                intent.putExtra("birth", petsList.get(position).getBirth());
                intent.putExtra("picture", petsList.get(position).getPicture());
                intent.putExtra("gender", petsList.get(position).getGender());
                intent.putExtra("description",petsList.get(position).getDescription());
                intent.putExtra("contactNumber",petsList.get(position).getUser().getContactNumber());
                intent.putExtra("EmailPet",petsList.get(position).getUser().getEmailAddress());
                startActivity(intent);
            }

            @Override
            public void onLoveClick(View view, int position) {

                final int id = petsList.get(position).getId();
                final Boolean love = petsList.get(position).getLove();
                final ImageView mLove = view.findViewById(R.id.love);

                if (love){
                    Toast.makeText(getContext(), "making love off",
                            Toast.LENGTH_SHORT).show();
                    mLove.setImageResource(R.drawable.likeof);
                    petsList.get(position).setLove(false);
                    updateLove("update_love", id, false);
                    adapter.notifyDataSetChanged();
                } else {
                    mLove.setImageResource(R.drawable.likeon);
                    petsList.get(position).setLove(true);
                    updateLove("update_love", id, true);
                    adapter.notifyDataSetChanged();
                }

            }
        };

        FloatingActionButton fab = view.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                GridviewItemFragment f = new GridviewItemFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();

 */
                Intent intent = new Intent(getActivity(), AddActivity.class);
                /*
                intent.putExtra("id", petsList.get(position).getId());
                intent.putExtra("name", petsList.get(position).getName());
                intent.putExtra("species", petsList.get(position).getSpecies());
                intent.putExtra("breed", petsList.get(position).getBreed());
                intent.putExtra("birth", petsList.get(position).getBirth());
                intent.putExtra("picture", petsList.get(position).getPicture());
                intent.putExtra("gender", petsList.get(position).getGender());
                intent.putExtra("description",petsList.get(position).getDescription());

                 */
                startActivity(intent);
            }
        });
        return view;

    }

    public void getPets(){
        Call<List<Pets>> call = apiInterface.getPets();
        call.enqueue(new Callback<List<Pets>>() {
            @Override
            public void onResponse(Call<List<Pets>> call, Response<List<Pets>> response) {
                progressBar.setVisibility(View.GONE);
                petsList = response.body();
                Log.i(NavigationActivity.class.getSimpleName(), response.body().toString());
                adapter = new Adapter(petsList, getContext(), listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Pets>> call, Throwable t) {
                Toast.makeText(getContext(), "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onResume() {
        super.onResume();
        getPets();
    }

    public void updateLove(final String key, final int id, final Boolean love){

        Call<Pets> call = apiInterface.updateLove(id, love);

        call.enqueue(new Callback<Pets>() {
            @Override
            public void onResponse(Call<Pets> call, Response<Pets> response) {

                Log.i("Home2Fragment", "Response "+response.toString());

                Pets pet1 = response.body();
                Log.i("Home2Fragment", response.body().toString());

                if (pet1.getLove() == TRUE){
                    Toast.makeText(getContext(), "Love TRUE", Toast.LENGTH_SHORT).show();
                } else{
                    Toast.makeText(getContext(), "Love FALSE", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<Pets> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
