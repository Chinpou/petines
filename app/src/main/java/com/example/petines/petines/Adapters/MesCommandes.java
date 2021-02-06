package com.example.petines.petines.Adapters;

import android.content.Context;
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

import java.util.List;

public class MesCommandes extends ArrayAdapter<OrderItem> {
    public MesCommandes(@NonNull Context context, @NonNull List<OrderItem> objects) {
        super(context, R.layout.petscommande, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater Inf1 = LayoutInflater.from(getContext());
        View customeView = Inf1.inflate(R.layout.petscommande, parent, false);


        return customeView;
    }

}
