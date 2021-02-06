package com.example.petines.petines.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

public class CommandeAdapter extends ArrayAdapter<String> {
    public CommandeAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
    }
}
