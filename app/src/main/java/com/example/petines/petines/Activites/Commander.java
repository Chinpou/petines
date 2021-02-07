package com.example.petines.petines.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.petines.petines.Adapters.Adapter;
import com.example.petines.petines.Adapters.commandeAdapetr;
import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.R;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Commander extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private commandeAdapetr adapter;
    private List<Commande> commandePets;
    ApiInterface apiInterface;
    commandeAdapetr.RecyclerViewClickListener listener;
    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commander);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        progressBar = findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recyclerView);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        listener = new commandeAdapetr.RecyclerViewClickListener() {

            @Override
            public void onRowClick(View view, int position) {
                Intent intent = new Intent(Commander.this, EditorActivity.class);
                intent.putExtra("id", commandePets.get(position).getId());
                intent.putExtra("Date", commandePets.get(position).getDate());
                intent.putExtra("Pet", (Serializable) commandePets.get(position).getPet());
                intent.putExtra("statut", commandePets.get(position).getStatus());
                intent.putExtra("lacation", commandePets.get(position).getLocation());
                startActivity(intent);

            }

            @Override
            public void onLoveClick(View view, int position) {
                final int id = commandePets.get(position).getId();
                final String statut = commandePets.get(position).getStatus();
                //final ImageView mLove = view.findViewById(R.id.love);

            }

        };


        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Commander.this, EditorActivity.class));
            }
        });
    }

    public void getCommande(){
        Call<List<Commande>> call = apiInterface.getCommande();
        call.enqueue(new Callback<List<Commande>>() {
            @Override
            public void onResponse(Call<List<Commande>> call, Response<List<Commande>> response) {
                progressBar.setVisibility(View.GONE);
                commandePets = response.body();
                Log.i(NavActivity.class.getSimpleName(), response.body().toString());
                adapter = new commandeAdapetr(commandePets, Commander.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }



            @Override
            public void onFailure(Call<List<Commande>> call, Throwable t) {
                Toast.makeText(Commander.this, "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        getCommande();
    }

}