package com.example.petines.petines.Adapters;

import android.widget.Filter;

import com.example.petines.petines.Model.Favourite;
import com.example.petines.petines.Model.Pets;

import java.util.ArrayList;

public class CustomFilterFavourite extends Filter {

    AdapterFavourites adapter;
    ArrayList<Favourite> filterList;

    public CustomFilterFavourite(ArrayList<Favourite> filterList, AdapterFavourites adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }

    //FILTERING OCURS
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Pets> filteredPets=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getPets().getName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPets.add(filterList.get(i).getPets());
                }
            }

            results.count=filteredPets.size();
            results.values=filteredPets;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {

        adapter.pets= (ArrayList<Favourite>) results.values;

        //REFRESH
        adapter.notifyDataSetChanged();

    }
}