package com.example.petines.petines.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.petines.petines.Activites.ApiInterface;

import com.example.petines.petines.Activites.NavigationActivity;
import com.example.petines.petines.Adapters.CommandeAdapter;
import com.example.petines.petines.Model.Commande;

import com.example.petines.petines.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommandeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private List<Commande> commandePets;
    ApiInterface apiInterface;
    CommandeAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;
    String username;



    public CommandeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {

            username = bundle.getString("username");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_commande, container, false);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


        progressBar = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recyclerView);
        listener = new CommandeAdapter.RecyclerViewClickListener() ;

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        return view;

    }
    public void getCommandesByUser(String username){
        Call<List<Commande>> call = apiInterface.getCommandesByUser(username);
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                progressBar.setVisibility(View.GONE);
                commandePets = response.body();
                Log.i(NavigationActivity.class.getSimpleName(), response.body().toString());
                CommandeAdapter adapter = new CommandeAdapter(commandePets, getContext(), listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }



            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t)
            {Toast.makeText(getContext(), "rp :"+
                            t.getMessage().toString(),
                    Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void onResume() {
        super.onResume();
        getCommandesByUser(username);
    }
}
