package com.example.petines.petines.Fragments;

 import android.content.Context;
 import android.content.Intent;
 import android.content.SharedPreferences;
 import android.os.Bundle;
 import android.support.annotation.Nullable;
 import android.support.design.widget.FloatingActionButton;
 import android.support.v4.app.Fragment;
 import android.support.v4.app.FragmentTransaction;
 import android.support.v7.widget.LinearLayoutManager;
 import android.support.v7.widget.RecyclerView;
 import android.util.Log;
 import android.view.LayoutInflater;
 import android.view.View;
 import android.view.ViewGroup;
 import android.widget.AdapterView;
 import android.widget.ImageView;
 import android.widget.ListView;
 import android.widget.ProgressBar;
 import android.widget.Toast;

 import com.example.petines.petines.Activites.ApiInterface;
 //import com.example.petines.petines.Activites.NavActivity;
 import com.example.petines.petines.Activites.Test2Activity;
 import com.example.petines.petines.Adapters.Adapter;
 import com.example.petines.petines.Adapters.AdapterFavourites;
 import com.example.petines.petines.Model.Favourite;
 import com.example.petines.petines.Model.Pets;
 import com.example.petines.petines.R;

 import java.util.List;

 import retrofit2.Call;
 import retrofit2.Callback;
 import retrofit2.Response;

 import static java.lang.Boolean.TRUE;


public class FavouritesFragment extends Fragment {


    String id;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterFavourites adapter;
    private List<Favourite> petsList;
    ApiInterface apiInterface;
    AdapterFavourites.RecyclerViewClickListener listener;
    ProgressBar progressBar;
    String username;

     @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


         final Bundle bdl = getArguments();
         username = bdl.getString("username");

         inflater = LayoutInflater.from(getContext());
         final View view = inflater.inflate(R.layout.activity_home2, container, false);
         getActivity().setTitle("WishList");
         //inflater = LayoutInflater.from(getContext());
         apiInterface = ApiClient.getApiClient().create(ApiInterface.class);


         progressBar = view.findViewById(R.id.progress);
         recyclerView = view.findViewById(R.id.recyclerView);

         layoutManager = new LinearLayoutManager(getContext());
         recyclerView.setLayoutManager(layoutManager);

         listener = new AdapterFavourites.RecyclerViewClickListener() {
             @Override
             public void onRowClick(View view, final int position) {
/*
                PetDetailsFragment f = new PetDetailsFragment();

                int Id = petsList.get(position).getId();
                Bundle bdl = new Bundle(9);
                bdl.putInt("pet_id", Id);
                f.setArguments(bdl);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();

 */
/*
                GridviewItemFragment f = new GridviewItemFragment();

                String name = petsList.get(position).getName();
                Bundle bdl = new Bundle(4);
                bdl.putString("name_petty", name);
                f.setArguments(bdl);

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left);
                ft.replace(R.id.fragment_container, f);
                ft.addToBackStack(null);
                ft.commit();

 */
                 Intent intent = new Intent(getActivity(), Test2Activity.class);
                 intent.putExtra("id", petsList.get(position).getPets().getId());
                 intent.putExtra("name", petsList.get(position).getPets().getName());
                 intent.putExtra("species", petsList.get(position).getPets().getSpecies());
                 intent.putExtra("breed", petsList.get(position).getPets().getBreed());
                 intent.putExtra("birth", petsList.get(position).getPets().getBirth());
                 intent.putExtra("picture", petsList.get(position).getPets().getPicture());
                 intent.putExtra("gender", petsList.get(position).getPets().getGender());
                 intent.putExtra("description",petsList.get(position).getPets().getDescription());
                 intent.putExtra("contactNumber",petsList.get(position).getPets().getUser().getContactNumber());
                 intent.putExtra("EmailPet",petsList.get(position).getPets().getUser().getEmailAddress());
                 startActivity(intent);
             }

             @Override
             public void onLoveClick(View view, int position) {

                 //final int id = petsList.get(position).getPets().getId();
                 final int id = petsList.get(position).getFid();
                 final Boolean love = petsList.get(position).getLiked();
                 final ImageView mLove = view.findViewById(R.id.love);

                 if (love){
                     mLove.setImageResource(R.drawable.likeof);
                     petsList.get(position).setLiked(false);
                     Toast.makeText(getContext(), "making off",
                             Toast.LENGTH_SHORT).show();
                     updateLove("update_love", id, false);
                     adapter.notifyDataSetChanged();
                 } else {
                     mLove.setImageResource(R.drawable.likeon);
                     petsList.get(position).setLiked(true);
                     updateLove("update_love", id, true);
                     adapter.notifyDataSetChanged();
                 }
             }
         };
         FloatingActionButton fab = view.findViewById(R.id.fab);
         fab.setVisibility(View.INVISIBLE);
         return view;

     }

    public void getMyPetWhishList(String username){
        Call<List<Favourite>> call = apiInterface.getMyPetWhishList(username);
        call.enqueue(new Callback<List<Favourite>>() {
            @Override
            public void onResponse(Call<List<Favourite>> call, Response<List<Favourite>> response) {
                progressBar.setVisibility(View.GONE);
                petsList = response.body();
                Log.i("Taaaaag", response.body().toString());
                adapter = new AdapterFavourites(petsList, getContext(), listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Favourite>> call, Throwable t) {
                Toast.makeText(getContext(), "rp :"+
                                t.getMessage().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onResume() {
        super.onResume();
        getMyPetWhishList(this.username);
    }

/*
    public void updateLove(final String key, final int id, Boolean love){

        Call<Pets> call = apiInterface.updateLove(id, love);

        call.enqueue(new Callback<Pets>() {
            @Override
            public void onResponse(Call<Pets> call, Response<Pets> response) {

                Log.i("TAG", "Response "+response.toString());

                Pets pet1 = response.body();
                Log.i("TAG", response.body().toString());

                if (pet1.getLove() == TRUE){
                    Toast.makeText(getContext(), "Love TRUE", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Love FALSE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Pets> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
 */

    public void updateLove(final String key, final int id, Boolean love){

        Call<Favourite> call = apiInterface.updateLoveFav(id, love);

        call.enqueue(new Callback<Favourite>() {
            @Override
            public void onResponse(Call<Favourite> call, Response<Favourite> response) {

                //Log.i("TAG", "Response "+response.toString());

                Favourite fav1 = response.body();
                //Log.i("TAG", response.body().getPets().getName());

                if (fav1.getLiked() == TRUE){
                    Toast.makeText(getContext(), "Love TRUE", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Love FALSE", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Favourite> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage().toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
