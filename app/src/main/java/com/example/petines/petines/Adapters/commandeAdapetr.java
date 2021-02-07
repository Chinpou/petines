package com.example.petines.petines.Adapters;
import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.petines.petines.Activites.Commander;
import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class commandeAdapetr extends RecyclerView.Adapter<Adapter.MyViewHolder> implements Filterable{
    public List<Commande> petsCommande;

    private Context context;
    private Adapter.RecyclerViewClickListener mListener;
    CustomFilter filter;

    public commandeAdapetr(List<Commande> commandePets, Commander commander, Adapter.RecyclerViewClickListener listener) {
        this.petsCommande = commandePets;
        this.context = context;
        this.mListener = listener;
        
    }

    @Override
    public Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(Adapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public abstract static class RecyclerViewClickListener {
        public abstract void onRowClick(View view, int position);

        public abstract void onLoveClick(View view, int position);
    }
}
