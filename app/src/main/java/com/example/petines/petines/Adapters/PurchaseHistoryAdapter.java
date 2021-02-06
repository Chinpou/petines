package com.example.petines.petines.Adapters;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.petines.petines.Model.Order;
import com.example.petines.petines.Model.OrderItem;
import com.example.petines.petines.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by petines on 5/27/2018.
 */

public class PurchaseHistoryAdapter extends ArrayAdapter<OrderItem> {

    String id;
    Order specificPi;
    List<String> dates = new ArrayList<>();
    ProgressBar pgsBar;

    public PurchaseHistoryAdapter(@NonNull Context context,  @NonNull List<OrderItem> objects, List<String> dates) {
        super(context, R.layout.purchase_histrory_custom , objects);
        this.dates = dates;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(getContext());;
        View view = inflater.inflate(R.layout.purchase_histrory_custom, parent, false);

        TextView purchaseProduct = view.findViewById(R.id.purchaseProduct);
        TextView purchasePrice = view.findViewById(R.id.purchasePrice);
        TextView purchaseQuantity = view.findViewById(R.id.purchaseQuantity);
        ImageView purchaseImage = view.findViewById(R.id.purchaseImage);
        TextView purchaseTime = view.findViewById(R.id.purchaseTime);
        pgsBar = view.findViewById(R.id.pBar3);


        //Button cartBuy = view.findViewById(R.id.cartBuy);

        final OrderItem pi=getItem(position);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("uid", "");

//        cancelOrder.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                    pi.setStatus("Cancelled");
//                    pi.save();
//
//                    Products p=Products.findById(Products.class, pi.getC().getP().getId());
//                    p.setQuantity(p.getQuantity()+pi.getC().getNumberOfItems());
//
//                    Toast.makeText(getContext(), "Order Cancelled", Toast.LENGTH_SHORT).show();
//                    p.save();
//                remove(pi);
//
//            }
//        });


            purchaseProduct.setText(pi.getProduct().getProductName());
            purchasePrice.setText("$" + String.valueOf(pi.getProduct().getPrice()));
            purchaseQuantity.setText("Quantity : " + String.valueOf(pi.getQuantity()));
            try {
                purchaseTime.setText("Purchase Date : " +dates.get(position));

            }catch(Exception e) {
                e.printStackTrace();
            }
        pgsBar.setVisibility(View.GONE);


            Picasso.get()
                        .load(pi.getProduct().getProdImage())
                        .placeholder(R.drawable.dress)
                        .resize(200, 300)
                        .into(purchaseImage);



        return view;

    }
}
