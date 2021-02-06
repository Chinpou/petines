package com.example.petines.petines.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.petines.petines.Adapters.CancelledAdapter;
import com.example.petines.petines.Model.PurchasedItems;
import com.example.petines.petines.R;

import java.util.List;

public class CancelledOrders extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.cancelled_history, container, false);

        ListView listView=view.findViewById(R.id.cancelledList);

        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        String id=sharedPreferences.getString("uid","");

        List<PurchasedItems> piList=PurchasedItems.find(PurchasedItems.class,"status =?", "Cancelled");


        CancelledAdapter receiptAdapter=new CancelledAdapter(getContext(), piList);
        listView.setAdapter(receiptAdapter);



        return view;

    }
}