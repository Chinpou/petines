package com.example.petines.petines.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.petines.petines.Model.Product;

import com.example.petines.petines.R;
//import com.example.petines.petines.Services.ProdService;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by petines on 4/16/2018.
 */

public class SIngleItem extends ArrayAdapter<Product> implements Filterable {

    List<Product> products;
    ProgressBar pgsBar;

    public SIngleItem(Context aContext, List<Product> objects) {
        super(aContext, R.layout.custom_layout, objects);
        objects = this.products;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Product p=getItem(position);
        View view;

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());


        view = layoutInflater.inflate(R.layout.custom_layout, parent, false);
        //   Products product= products.get(position);

//
//        SharedPreferences sharedPreferences= getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
//        id=sharedPreferences.getString("uid","");

        TextView prodText =  view.findViewById(R.id.productText);
        TextView priceText = view.findViewById(R.id.priceText);
        TextView descriptionText = view.findViewById(R.id.descriptionText);
        ImageView gridFav = view.findViewById(R.id.gridFav);

        pgsBar = view.findViewById(R.id.pBar1);





        ImageView imageViewPhoto =  view.findViewById(R.id.productIcon);
        imageViewPhoto.setVisibility(View.GONE);

        String price = String.valueOf(p.getPrice());
        prodText.setText(p.getProductName());
        priceText.setText("$" + price);
        descriptionText.setText(p.getQuantity() + " available ");

        Picasso.get()
                .load(p.getProdImage())
                .resize(200 ,300)
                .into(imageViewPhoto);

        imageViewPhoto.setVisibility(View.VISIBLE);

        return view;

    }

}
