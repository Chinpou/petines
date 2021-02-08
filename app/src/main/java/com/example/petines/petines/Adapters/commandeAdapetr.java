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

import java.text.BreakIterator;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petscommandes, parent, false);
        return null;
    }

    @Override
    public void onBindViewHolder( final MyViewHolder holder, int position) {
        holder.mName.setText(petsCommande.get(position).getPet());
        holder.mDate.setText(petsCommande.get(position).getDate());
        holder.mDescription.setText(petsCommande.get(position).getDescription());
        holder.mStatuts.setText(petsCommande.get(position).getStatus());


        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(petsCommande.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);

    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {






        private Adapter.RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private TextView mStatuts;
        private ImageView mLove;
        private TextView mName, mType, mDate, mDescription;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, Adapter.RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.imgPetCommande);
            mName = itemView.findViewById(R.id.annonce);
            mDate = itemView.findViewById(R.id.date);
            mDescription = itemView.findViewById(R.id.description);
            mStatuts = itemView.findViewById(R.id.statut);


            mListener = listener;
            mRowContainer.setOnClickListener(this);

        }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

        @Override
        public void onClick(View view) {

        }

        public abstract static class RecyclerViewClickListener {
        public abstract void onRowClick(View view, int position);

        public abstract void onLoveClick(View view, int position);
    }
}
