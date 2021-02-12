package com.example.petines.petines.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.petines.petines.Activites.ApiInterface;

import com.example.petines.petines.Activites.NavigationActivity;
import com.example.petines.petines.Activites.Test2Activity;
import com.example.petines.petines.Adapters.Adapter;
import com.example.petines.petines.Adapters.CommandeAdapter;
import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CommandeFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private CommandeAdapter adapter;
    private List<Commande> commandePets;
    ApiInterface apiInterface;
    CommandeAdapter.RecyclerViewClickListener listener;
    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CommandeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CommandeFragment.
     */
    // TODO: Rename and change types and number of parameters
    /*public static CommandeFragment newInstance(String param1, String param2) {
        CommandeFragment fragment = new CommandeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

    String username;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
        Bundle bundle = this.getArguments();
        if (bundle != null) {

            username = bundle.getString("username");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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