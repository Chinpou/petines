package com.example.petines.petines.Fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.petines.petines.Model.OrderItem;
import com.example.petines.petines.Model.Product;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.OrderItemService;
import com.example.petines.petines.Services.ProductsService;
import com.example.petines.petines.Services.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WishlistFragment extends AppCompatDialogFragment {

    int cartItem = 0;
    int position;
    double buyPrice;
    double buyTotal;
    double quantityDouble;
    int tempVal;
    private String username;
    private int newQuantity;

    Button confirm;
    NumberPicker quantity;
    Product selectedProduct;
    User selectedUser;
    List<OrderItem> userCart;
    OrderItem tempOrderItem;
    Context mContext;


    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = null;
        builder = new AlertDialog.Builder(getActivity());


        Bundle bundle = getArguments();
        position = bundle.getInt("pid");


        View view = null;
        LayoutInflater inflater = getActivity().getLayoutInflater();
        view = inflater.inflate(R.layout.activity_buy_pop_up, null);

        quantity = view.findViewById(R.id.quantityPicker);
        selectedProduct= retrieveProduct(position);
        builder.setView(view)
                .setTitle("Select Quantity")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })

                .setPositiveButton("Done", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        calculateTotal(quantity.getValue(), position);
                        Toast.makeText(getActivity(), "Added to Cart!", Toast.LENGTH_SHORT).show();
                    }
                });

        return builder.create();
    }

    public void calculateTotal(final int quantity, int position) {

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
        username = sharedPreferences.getString("username", "");

        selectedProduct = retrieveProduct(position);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.getUser(username);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                selectedUser = response.body();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(OrderItemService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                OrderItemService orderItemService = retrofit.create(OrderItemService.class);
                Call<List<OrderItem>> nestedCall = orderItemService.getCartItems(username);

                nestedCall.enqueue(new Callback<List<OrderItem>>() {
                    @Override
                    public void onResponse(Call<List<OrderItem>> call, Response<List<OrderItem>> response) {
                        userCart = response.body();

                        if (userCart.size() >= 1)
                        {
                            for (OrderItem oi : userCart)
                            {
                                if (oi.getProduct().getPid()==(selectedProduct.getPid()))
                                {
                                    tempOrderItem = oi;
                                    newQuantity = tempOrderItem.getQuantity() + quantity;
                                    updateCart(newQuantity, tempOrderItem);
                                    break;
                                }
                                else
                                {
                                    tempOrderItem = new OrderItem();

                                    if (quantity >= 1) {

                                        int oiQuantity= quantity;
                                        int prodQuantity = Integer.parseInt(selectedProduct.getQuantity());

                                        if (oiQuantity > prodQuantity) {
                                            Toast.makeText(getActivity(),"Cant add anymore!", Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            addToCart(quantity, selectedUser, tempOrderItem);
                                        }
                                    }
                                }
                            }
                        }
                        else
                        {
                            tempOrderItem = new OrderItem();

                            if (quantity >= 1) {

                                int oiQuantity= quantity;
                                int prodQuantity = Integer.parseInt(selectedProduct.getQuantity());

                                if (oiQuantity > prodQuantity) {
                                    Toast.makeText(getActivity(),"Cant add anymore!", Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    addToCart(quantity, selectedUser, tempOrderItem);
                                }
                            }
                        }

                    }
                    @Override
                    public void onFailure(Call<List<OrderItem>> call, Throwable throwable) {

                    }
                });

            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

            }
        });
    }

    public Product retrieveProduct(int id) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProductsService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ProductsService productsService = retrofit.create(ProductsService.class);
        Call<Product> call = productsService.getProduct(id);

        call.enqueue(new Callback<Product>() {

            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {

                selectedProduct = response.body();
                int maxVal= Integer.parseInt(selectedProduct.getQuantity());
                if(maxVal > 0) {
                    quantity.setMaxValue(maxVal);
                    quantity.setMinValue(1);
                }
            }
            @Override
            public void onFailure(Call<Product> call, Throwable t) {
            }
        });

        return selectedProduct;
    }

    public void addToCart(int quantity, User user, OrderItem orderItem) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OrderItemService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderItemService orderItemService = retrofit.create(OrderItemService.class);

        Product product = new Product();
        product.setPid(position);

        User tempUser = new User();
        tempUser.setUsername(user.getUsername());
        tempUser.setUid(user.getUid());

        orderItem.setStatus("cart");
        orderItem.setQuantity(quantity);
        orderItem.setProduct(product);
        orderItem.setOi_user(tempUser);

        Call<OrderItem> call = orderItemService.addToCart(orderItem);
        call.enqueue(new Callback<OrderItem>() {
            @Override
            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                Toast.makeText(mContext,"Added to Cart!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OrderItem> call, Throwable throwable) {

            }
        });
    }

    public void updateCart(int quantity, OrderItem orderItem) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(OrderItemService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OrderItemService orderItemService = retrofit.create(OrderItemService.class);

        orderItem.setQuantity(quantity);

        Call<OrderItem> call = orderItemService.updateCartItem(username,quantity,selectedProduct.getPid());
        call.enqueue(new Callback<OrderItem>() {
            @Override
            public void onResponse(Call<OrderItem> call, Response<OrderItem> response) {
                Toast.makeText(mContext,"Added to Cart!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<OrderItem> call, Throwable throwable) {

            }
        });
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

}



