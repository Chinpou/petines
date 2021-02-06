package com.example.petines.petines.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;
import android.widget.Toolbar;

 import com.example.petines.petines.Adapters.SIngleItem;
import com.example.petines.petines.Adapters.InquiryAdapter;
import com.example.petines.petines.Model.Inquiry;
import com.example.petines.petines.Model.Product;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.ProductsService;
import com.example.petines.petines.Services.ReviewService;
import com.example.petines.petines.Services.UserService;
import com.squareup.picasso.Picasso;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GridviewItemFragment extends Fragment {

    int position;
    String id;


    Toolbar toolbar;
    TextView priceText;
    TextView productText;
    ImageView productImage;
    Button cartButton;

    Button buyButton;
    Button shareButton;
    TextView productQuantity;
    TextView descriptionText;
    TextView sendText;

    ListView inquiryList;
    ToggleButton favourite;
    RatingBar ratingbar;
    Button sendButton;
    ProgressBar pgsBar;


    Product selectedProduct;


    CartFragment cartFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        final Bundle bdl = getArguments();
        position = bdl.getInt("uid");


        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        final String USERNAME = sharedPreferences.getString("username", "");

        inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.gridview_item, container, false);


        priceText = view.findViewById(R.id.productPrice);
        productText = view.findViewById(R.id.productTexts);
        productImage = view.findViewById(R.id.productImage);
         //Button cartButton = view.findViewById(R.id.cartButton);
        buyButton = view.findViewById(R.id.buyButton);
        shareButton = view.findViewById(R.id.shareButton);
        productQuantity = view.findViewById(R.id.quantity);
        descriptionText = view.findViewById(R.id.DescriptionText);
        sendText = view.findViewById(R.id.edittext_chatbox);
        sendButton = view.findViewById(R.id.button_chatbox_send);
        inquiryList = view.findViewById(R.id.inquiryList);
        favourite = view.findViewById(R.id.favourites);
        ratingbar = view.findViewById(R.id.ratingBar);

        pgsBar = view.findViewById(R.id.pBar);
        pgsBar.setVisibility(View.VISIBLE);


        getProduct();


        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Petines Product:";
                String shareSub = "Petines Sub";
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, shareSub);
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, " Share using"));

            }
        });

        buyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cartFragment = new CartFragment();
                Bundle bundle = new Bundle();
                bundle.putInt("pid", position);
                cartFragment.setArguments(bundle);
                cartFragment.show(getFragmentManager(), "buyFragment");
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final ReviewService reviewService = retrofit.create(ReviewService.class);

        Call<List<Inquiry>> call2 = reviewService.getReview(position);
        call2.enqueue(new Callback<List<Inquiry>>() {
            @Override
            public void onResponse(Call<List<Inquiry>> call, Response<List<Inquiry>> response) {
                List<Inquiry> reviews = response.body();
                InquiryAdapter omegaAdapter = new InquiryAdapter(getContext(), reviews);
                inquiryList.setAdapter(omegaAdapter);

            }

            @Override
            public void onFailure(Call<List<Inquiry>> call, Throwable throwable) {

            }
        });





        inquiryList.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                return false;
            }
        });


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "Review placed!", Toast.LENGTH_LONG).show();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final UserService userService = retrofit.create(UserService.class);

                Call<User> call = userService.getUser(USERNAME);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            User user = response.body();

                            Product product = new Product();
                            product.setPid(position);

                            Date c = Calendar.getInstance().getTime();

                            Inquiry i = new Inquiry();

                            i.setReview(sendText.getText().toString());
                            i.setUser(user);
                            i.setDate(c.toString());
                            i.setProd_id(product);


                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl(UserService.BASE_URL)
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            final ReviewService reviewService = retrofit.create(ReviewService.class);

                            Call<Inquiry> call2 = reviewService.addReview(i);
                            call2.enqueue(new Callback<Inquiry>() {
                                @Override
                                public void onResponse(Call<Inquiry> call, Response<Inquiry> response) {


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl(UserService.BASE_URL)
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    final ReviewService reviewService = retrofit.create(ReviewService.class);

                                    Call<List<Inquiry>> call2 = reviewService.getAllInquiries();
                                    call2.enqueue(new Callback<List<Inquiry>>() {
                                        @Override
                                        public void onResponse(Call<List<Inquiry>> call, Response<List<Inquiry>> response) {
                                            List<Inquiry> reviews = response.body();
                                            InquiryAdapter omegaAdapter = new InquiryAdapter(getContext(), reviews);
                                            inquiryList.setAdapter(omegaAdapter);
                                            Toast.makeText(getActivity(), "Review placed!", Toast.LENGTH_LONG).show();
                                        }

                                        @Override
                                        public void onFailure(Call<List<Inquiry>> call, Throwable throwable) {
                                            Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();

                                        }
                                    });
                                }

                                @Override
                                public void onFailure(Call<Inquiry> call, Throwable throwable) {

                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable throwable) {

                        }
                    });

            }
        });




        return view;


    }

    public Product getProduct() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsService productsService = retrofit.create(ProductsService.class);
        Call<Product> call = productsService.getProduct(position);

        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                selectedProduct = response.body();

                priceText.setText("Price: $" + selectedProduct.getPrice());
                productText.setText(selectedProduct.getProductName());
                descriptionText.setText(selectedProduct.getProd_desc());
                productQuantity.setText("Quantity :" + selectedProduct.getQuantity());
                Picasso.get()
                        .load(selectedProduct.getProdImage())
                        .into(productImage);

                pgsBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
        return selectedProduct;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Product Details");

    }

}
