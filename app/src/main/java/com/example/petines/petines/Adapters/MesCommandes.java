package com.example.petines.petines.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

import com.example.petines.petines.Model.OrderItem;

import java.util.List;

public class MesCommandes extends ArrayAdapter<OrderItem> {
    public MesCommandes(@NonNull Context context, int resource, @NonNull List<OrderItem> objects) {
        super(context, resource, objects);
    }
}
