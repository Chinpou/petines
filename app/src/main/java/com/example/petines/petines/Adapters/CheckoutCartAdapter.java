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
import android.widget.TextView;

import com.example.petines.petines.Model.OrderItem;
import com.example.petines.petines.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by petines on 6/6/2018.
 */

public class CheckoutCartAdapter extends ArrayAdapter<OrderItem> {
    private String username;

    public CheckoutCartAdapter(@NonNull Context context, @NonNull List<OrderItem> objects) {
        super(context,  R.layout.checkout_cart_custom, objects);

    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater=LayoutInflater.from(getContext());;
        View view = layoutInflater.inflate(R.layout.checkout_cart_custom, parent, false);

        OrderItem c = getItem(position);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");


        TextView gridText = view.findViewById(R.id.checkoutGridText);
        ImageView gridPic = view.findViewById(R.id.checkoutGridPic);

            gridText.setText(c.getProduct().getProductName());
            Picasso.get()
                    .load(c.getProduct().getProdImage())
                    .placeholder(R.drawable.dress)
                    .resize(100, 100)
                    .into(gridPic);
        return view;
    }
}
