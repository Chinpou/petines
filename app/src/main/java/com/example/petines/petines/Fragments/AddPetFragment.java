package com.example.petines.petines.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petines.petines.Activites.MainActivity;
import com.example.petines.petines.Activites.TestActivity;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;

import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPetFragment extends Fragment
                 implements AdapterView.OnItemSelectedListener {

    String name;
    String description;
    String specie;
    String breed;
    Date birth;
    String pictures;

    Button submit;
    User currentUser;
    EditText editName;
    EditText editDescription;
    EditText editSpecie;
    EditText editBreed;
    EditText editBirth;
    ImageView editImage;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddPetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddPetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddPetFragment newInstance(String param1, String param2) {
        AddPetFragment fragment = new AddPetFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_pet, container, false);
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // handle your fragment events here
        Spinner editSpecie = (Spinner) view.findViewById(R.id.spinnerSpecie);
        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(getContext(),
                R.array.spinnerSpecie, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSpecie.setAdapter(spinAdapter);
        editSpecie.setOnItemSelectedListener(this);
        Toast.makeText(getContext(),
                       "Specie is "+ editSpecie,
                      Toast.LENGTH_LONG).show();
        EditText editName = (EditText)view.findViewById(R.id.editName);
        EditText editDesc = (EditText)view.findViewById(R.id.editDesc);

        EditText editBirth = (EditText)view.findViewById(R.id.editBirth);
        EditText editBreed = (EditText)view.findViewById(R.id.editBreed);
        Button btn = (Button)view.findViewById(R.id.submit);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(checkRequiredFields()==true){}
//                else {
//                Toast.makeText(TestActivity.this,
//                        "Remplissez les cases obligatoires.",
//                        Toast.LENGTH_LONG).show();
            }
        });

    }

    private boolean checkRequiredFields(){
            return true;
        }

}