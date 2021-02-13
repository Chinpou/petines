package com.example.petines.petines.Activites;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    EditText usernameTaken;
    EditText passwordTaken;
    Button button;
    Button signup;
    Integer uid;
    User selectedUser;
    boolean x=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
           setContentView(R.layout.activity_main);
           setTitle("Login");

        button= findViewById(R.id.button);
        usernameTaken= findViewById(R.id.editText);
        passwordTaken= findViewById(R.id.editText3);
        signup= findViewById(R.id.button3);

        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            sendMessage(usernameTaken.getText().toString(),
                passwordTaken.getText().toString());
            }
        });

        signup.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    signup();
                }
            });
    }

    public void sendMessage(final String username , final String password) {

        Retrofit retrofit = ApiClient.getApiClient();

        UserService userService = retrofit.create(UserService.class);
        Call<User> call = userService.getUser(username);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                selectedUser = response.body();
                Log.i("onResponse MainActivity", "Uid: "+selectedUser.getUid());
                Log.i("onResponse MainActivity", selectedUser.toString());

                if(selectedUser.getPassword().equals(password))
                {
                    /*
                    SharedPreferences sharedPreferences = getSharedPreferences("Session", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("username", selectedUser.getUsername());
                    editor.putInt("id", selectedUser.getUid());
                    editor.apply();
                     */

                    Intent intent = new Intent(MainActivity.this, NavigationActivity.class);
                    intent.putExtra("username", username);
                    intent.putExtra("user_id", selectedUser.getUid());
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Welcome "+selectedUser.getUsername() + "!", Toast.LENGTH_LONG).show();
                }
             }
            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void signup() {
        Intent intent=new Intent(MainActivity.this, Registration.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
    }
}
