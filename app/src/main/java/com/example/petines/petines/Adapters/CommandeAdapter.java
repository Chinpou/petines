package com.example.petines.petines.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.petines.petines.Activites.Commander;
import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.R;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public abstract class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.MyViewHolder> implements Filterable {
    public static List<Commande> petsCommande;
    List<Commande> petsFilter;
    private Context context;
    private RecyclerViewClickListener mListener;
    CustomFilter filter;

    public CommandeAdapter(List<Commande> petscommande, Context context, Adapter.RecyclerViewClickListener listener) {
        this.petsCommande = petscommande;
        this.petsFilter = petsCommande;
        this.context = context;
        this.mListener = (RecyclerViewClickListener) listener;
    }

    public CommandeAdapter() {

    }

    public CommandeAdapter(List<Commande> commandePets, Commander context, RecyclerViewClickListener listener) {
        this.context = context;
        this.mListener = listener;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(view, (RecyclerViewClickListener) mListener);

    }
    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mName.setText(petsCommande.get(position).getPet());
        holder.mDate.setText(petsCommande.get(position).getDate());
        holder.mDesciption.setText(petsCommande.get(position).getDescription());
        holder.mStatut.setText(petsCommande.get(position).getStatus());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);



    }

    public abstract void onBindViewHolder(Adapter.MyViewHolder holder, int position);

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Adapter.RecyclerViewClickListener mListener;
        private CircleImageView mPicture;
        private ImageView imgPetCommande;
        private TextView mName, mDesciption, mDate, mStatut;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View itemView, Adapter.RecyclerViewClickListener listener) {
            super(itemView);
            mPicture = itemView.findViewById(R.id.imgPetCommande);
            mName = itemView.findViewById(R.id.annonce);
            mDesciption = itemView.findViewById(R.id.description);
            mStatut = itemView.findViewById(R.id.statut);
            mDate = itemView.findViewById(R.id.date);
            mRowContainer = itemView.findViewById(R.id.row_container);

            mListener = listener;
            mRowContainer.setOnClickListener(this);

        }

        public MyViewHolder(View view, RecyclerViewClickListener mListener) {
            super(view);
        }


        @Override
        public void onClick(View view) {


        }
    }

    public abstract static class RecyclerViewClickListener {
        protected abstract void onRowClick(View view, int position);
        protected abstract void onLoveClick(View view, int position);
    }
}
