package com.example.petines.petines.Activites;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import com.example.petines.petines.Adapters.Adapter;
import com.example.petines.petines.Fragments.CancelledOrders;
import com.example.petines.petines.Fragments.ContactUsFragment;
//import com.example.petines.petines.Fragments.FavouritesFragment;
import com.example.petines.petines.Fragments.Home2Fragment;
import com.example.petines.petines.Fragments.TabMain;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.github.clans.fab.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.petines.petines.Fragments.CartFragment;
import com.example.petines.petines.Fragments.HomeFragment;
import com.example.petines.petines.Fragments.ManageAccountFragment;
import com.example.petines.petines.Fragments.PurchaseHistoryFragment;
//import com.example.petines.petines.Fragments.ViewCart;
//import com.example.petines.petines.Model.tabPager;
import com.github.clans.fab.FloatingActionMenu;
import com.orm.SugarRecord;
import com.squareup.picasso.Picasso;

public class NavigationActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String uid;
    Bundle bundle;
    //FloatingActionMenu fam;
    //FloatingActionButton fabFavs, fabCart, fabOrders, fabProfile, fabShipping;
    Adapter adapter;
    Adapter.RecyclerViewClickListener listener;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);

        Fragment fragment = new Home2Fragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();

       // sharedPreferences = getSharedPreferences("loginDetails", Context.MODE_PRIVATE);
      //String id = sharedPreferences.getString("uid", "");
      //final User userData = SugarRecord.findById(User.class, Long.parseLong(id));

        //cast the serializable object to a User type object after getting it throug  the intent

        //fam = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
        //fabCart = (FloatingActionButton) findViewById(R.id.fabCart);
        //fabOrders = (FloatingActionButton) findViewById(R.id.fabOrders);
        //fabProfile =(FloatingActionButton) findViewById(R.id.fabProfile);
        //fabFavs =  (FloatingActionButton)findViewById(R.id.fabHome);
        //fabShipping =  (FloatingActionButton)findViewById(R.id.fabShipping);

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
 /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        MenuItem searchMenuItem = menu.findItem(R.id.action_search);

        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())
        );
        searchView.setQueryHint("Search Pet...");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {

                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter.getFilter().filter(newText);
                return false;
            }
        });

        searchMenuItem.getIcon().setVisible(false, false);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

  */

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
            /*
            Fragment fragment = new Home2Fragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
            fam.close(true);
             */
        }
        if (id == R.id.nav_mywishlist) {

        }
        if (id == R.id.nav_myorders) {
            /*
            Fragment fragment = new Home2Fragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fragment_container, fragment);
            ft.addToBackStack(null);
            ft.commit();
            fam.close(true);
             */
        }
        if (id == R.id.nav_contactUs) {

        }
        if (id == R.id.nav_logout) {
            /*
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();

             */
        }

        displaySettingsScreen(id);
        return true;
    }


}