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

import com.example.petines.petines.Model.Commande;
import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.R;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class CommandeAdapter extends RecyclerView.Adapter<CommandeAdapter.MyViewHolder> {
    public static List<Commande> commandePets;

    private Context context;
    private RecyclerViewClickListener mListener;




    public CommandeAdapter(List<Commande> commandePets, Context context, RecyclerViewClickListener listener) {
        this.context = context;
        this.commandePets = commandePets;

        this.mListener = listener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.petscommandes, parent, false);
        return new MyViewHolder(view, (RecyclerViewClickListener) mListener);

    }
    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {

        holder.mName.setText(commandePets.get(position).getPetName());
        holder.mDate.setText(commandePets.get(position).getDate());
        holder.mDesciption.setText(commandePets.get(position).getDescription());
        holder.mStatut.setText(commandePets.get(position).getStatus());

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.skipMemoryCache(true);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.NONE);
        requestOptions.placeholder(R.drawable.logo);
        requestOptions.error(R.drawable.logo);

        Glide.with(context)
                .load(commandePets.get(position).getPicture())
                .apply(requestOptions)
                .into(holder.mPicture);



    }




    @Override
    public int getItemCount() {
        return commandePets.size();
    }




    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private CircleImageView mPicture;

        private TextView mName, mDesciption, mDate, mStatut;
        private RelativeLayout mRowContainer;

        public MyViewHolder(View view, RecyclerViewClickListener mListener) {

            super(view);
            mPicture = view.findViewById(R.id.imgPetCommande);
            mName = view.findViewById(R.id.annonce);
            mDesciption = view.findViewById(R.id.description);
            mStatut = view.findViewById(R.id.statut);
            mDate = view.findViewById(R.id.date);
            mRowContainer = view.findViewById(R.id.row_container);
            RecyclerViewClickListener listener = null;
            mListener = listener;
            mRowContainer.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {


        }
    }

    public  static class RecyclerViewClickListener {




    }
}
