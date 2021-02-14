package com.example.petines.petines.Activites;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.petines.petines.Adapters.Adapter;
import com.example.petines.petines.Fragments.CommandeFragment;
import com.example.petines.petines.Fragments.FavouritesFragment;
import com.example.petines.petines.Fragments.Home2Fragment;
import com.example.petines.petines.R;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petines.petines.Fragments.ManageAccountFragment;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String uid;
    //SharedPreferences sharedPreferences;
    String username;
    TextView welcomeUserTextview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        //welcomeUserTextview = (TextView)findViewById(R.id.welcomeUserTextview);

        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        uid = intent.getStringExtra("user_id");

        //welcomeUserTextview.setText("Welcome "+ username);


        Fragment fragment = new Home2Fragment();
        Bundle bdl = new Bundle(4);
        bdl.putString("username", username);
        fragment.setArguments(bdl);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();

       //sharedPreferences = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
      //String id = sharedPreferences.getString("uid", "");
      //final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        displaySettingsScreen(R.id.home);
        setTitle("Home");

        //fam.setClosedOnTouchOutside(true);

//         fab.setOnTouchListener(NavigationActivity.this);

/*
        fabFavs.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences sharedPreferences=getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
                sharedPreferences.edit().clear().commit();
                Intent intent = new Intent(NavigationActivity.this, MainActivity.class);
                startActivity(intent);
                setTitle("Login");
                fam.close(true);
            }
        });

 */
//
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.


        View header_view = navigationView.getHeaderView(0);
        TextView user_name_text_view = header_view.findViewById(R.id.welcomeUserTextview);
        TextView user_id = header_view.findViewById(R.id.textView);

        //CircleImageView profile_img = header_view.findViewById(R.id.imageView);

        user_name_text_view.setText("Welcome "+ username);
        user_id.setText("User ID : "+ uid);

        //Picasso.get().load(Prevalent.online_user.getImage()).placeholder(R.drawable.profile).into(profile_img);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void displaySettingsScreen(int id) {
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Fragment fragment = new Home2Fragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (id == R.id.nav_manageAccount) {
            Fragment fragment = new ManageAccountFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (id == R.id.nav_mypets) {
            Intent intent = new Intent(getApplicationContext(), AddActivity.class);
            intent.putExtra("username", username);
            startActivity(intent);
        }
        if (id == R.id.nav_mywishlist) {
            Fragment fragment = new FavouritesFragment();
            Bundle bdl = new Bundle(4);
            bdl.putString("username", username);
            fragment.setArguments(bdl);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (id == R.id.nav_myorders) {
            Fragment fragment = new CommandeFragment();
            Bundle bdl = new Bundle(4);
            bdl.putString("username", username);
            fragment.setArguments(bdl);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
        }
        if (id == R.id.nav_contactUs) {
        }
        if (id == R.id.nav_logout) {
            /*
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
             */
        }
        displaySettingsScreen(id);
        return true;
    }


}