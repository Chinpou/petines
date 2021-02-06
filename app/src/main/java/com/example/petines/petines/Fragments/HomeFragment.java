package com.example.petines.petines.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.petines.petines.Adapters.SIngleItem;
import com.example.petines.petines.Model.Product;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.ProductsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class HomeFragment extends Fragment {
    Context context;
    String name;
    double price;
    String image;
    int quantity;
    String selectedProduct;
    int uid;
    SIngleItem gridAdapter;
    View view;
    GridView gridView;
    List<Product> products;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        inflater = LayoutInflater.from(getContext());
        final View view = inflater.inflate(R.layout.activity_home, container, false);

         gridView = view.findViewById(R.id.gridView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsService productsService = retrofit.create(ProductsService.class);
        Call<List<Product>> call = productsService.getProducts();

        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {

                products = response.body();
                gridAdapter = new SIngleItem(getActivity(), products);
                gridView.setAdapter(gridAdapter);


             }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });




        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridviewItemFragment f = new GridviewItemFragment();
                Product product= products.get(position);

                uid = product.getPid();
                Bundle bdl = new Bundle(4);
                bdl.putInt("uid", uid);

                f.setArguments(bdl);

                FragmentTransaction ft= getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;

    }

}
