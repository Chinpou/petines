package com.example.petines.petines.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.UserService;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageAccountFragment extends Fragment {
    boolean showingFirst = true;
    String password;
    String username;
    String email;
    String id;
    Context context;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflater = LayoutInflater.from(getContext());
        SharedPreferences sharedPreferences=getActivity().getSharedPreferences("loginDetails", Context.MODE_PRIVATE);

        final String USERNAME=sharedPreferences.getString("username","");

        getActivity().setTitle("Profile");

        final View view = inflater.inflate(R.layout.manage_account, container, false);
        final EditText usernameText=view.findViewById(R.id.usernameText);
        final EditText passwordText=view.findViewById(R.id.passwordText);
        final EditText emailText=view.findViewById(R.id.emailText);
        final ImageView profilePic=view.findViewById(R.id.profilePic);
        final Button editProfile=view.findViewById(R.id.editProfile);
        final Button saveChanges=view.findViewById(R.id.saveChanges);



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final UserService userService = retrofit.create(UserService.class);

        Call<User> call = userService.getUser(USERNAME);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User u = response.body();

                usernameText.setHint(u.getUsername());
                passwordText.setHint("*******");
                emailText.setHint(u.getEmailAddress());
                saveChanges.setEnabled(false);


                Picasso.get()
                        .load(u.getProfileimg())
                        .placeholder(R.drawable.pc)
                        .into(profilePic);





            }

            @Override
            public void onFailure(Call<User> call, Throwable throwable) {

            }
        });



        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showingFirst == true) {
                    passwordText.setEnabled(true);
                    emailText.setEnabled(true);
                    saveChanges.setEnabled(true);
                    editProfile.setEnabled(false);
                }
            }
            });

        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editProfile.setText("EDIT PROFILE");
                username = usernameText.getText().toString();
                password = passwordText.getText().toString();
                email = emailText.getText().toString();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl(UserService.BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                final UserService userService = retrofit.create(UserService.class);

                Call<List<User>> userCall = userService.getAllUsers();


                userCall.enqueue(new Callback<List<User>>() {
                    @Override
                    public void onResponse(Call<List<User>> call, Response<List<User>> response) {

                        List<User> users = response.body();
                        int count= 0;
                        final User newUserDetails = new User();

                        for(User u: users) {

                            if (!u.getUsername().equalsIgnoreCase(username) && u.getEmailAddress().equalsIgnoreCase(email)) {
                                count++;
                            }
                        }

                            if(count == 0) {

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl(UserService.BASE_URL)
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build();

                                final UserService userService = retrofit.create(UserService.class);

                                Call<User> userCall = userService.getUser(USERNAME);

                                userCall.enqueue(new Callback<User>() {
                                    @Override
                                    public void onResponse(Call<User> call, Response<User> response) {
                                        User existingUser = response.body();

                                        existingUser.setUsername(username);
                                        existingUser.setPassword(password);
                                        existingUser.setEmailAddress(email);

                                        Retrofit retrofit = new Retrofit.Builder()
                                                .baseUrl(UserService.BASE_URL)
                                                .addConverterFactory(GsonConverterFactory.create())
                                                .build();

                                        final UserService userService = retrofit.create(UserService.class);

                                        Call<User> userCall = userService.updateUser(USERNAME, existingUser);

                                        userCall.enqueue(new Callback<User>() {
                                            @Override
                                            public void onResponse(Call<User> call, Response<User> response) {
                                                Toast.makeText(getActivity(), "User Updated!", Toast.LENGTH_LONG).show();


                                            }

                                            @Override
                                            public void onFailure(Call<User> call, Throwable throwable) {
                                                Toast.makeText(getActivity(), throwable.getMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        });

                                    }

                                    @Override
                                    public void onFailure(Call<User> call, Throwable throwable) {

                                    }
                                });





                            saveChanges.setEnabled(false);
                            usernameText.setEnabled(false);
                            passwordText.setEnabled(false);
                            emailText.setEnabled(false);
                            editProfile.setEnabled(true);
                            Toast.makeText(getActivity(), "Change Successfull!", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getActivity(),"Username/Email already in use!",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<User>> call, Throwable throwable) {

                    }
                });



            }

        });




return view;


    }



}
