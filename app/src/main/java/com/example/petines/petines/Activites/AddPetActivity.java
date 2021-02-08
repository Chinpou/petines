package com.example.petines.petines.Activites;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petines.petines.Model.Pets;
import com.example.petines.petines.Model.User;
import com.example.petines.petines.R;
import com.example.petines.petines.Services.PetService;
import com.example.petines.petines.Services.UserService;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddPetActivity extends AppCompatActivity
                        implements AdapterView.OnItemSelectedListener {

    ApiInterface apiInterface;

    String name;
    String description;
    String specie;
    int gender;
    String breed;
    String birth;
    String pictures = null;

    Button submit;
    User currentUser;
    EditText editName;
    EditText editDescription;
    Spinner editSpecie;
    EditText editBreed;
    EditText editBirth;
    RadioGroup radioGroup;
    RadioButton radMale, radFemale;
    ImageView editImage;


    String fPath;
    private Uri filePath;

    private final int PICK_IMAGE_REQUEST = 71;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_pet);

        radMale = findViewById(R.id.radMale);
        radFemale = findViewById(R.id.radFemale);
        editName = findViewById(R.id.editName);
        editDescription = findViewById(R.id.editDesc);
        editBirth = findViewById(R.id.editBirth);
        editBreed = findViewById(R.id.editBreed);
        submit = findViewById(R.id.submit);
        editImage = findViewById(R.id.editImage);
        editSpecie = findViewById(R.id.spinnerSpecie);

        ArrayAdapter<CharSequence> spinAdapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.spinnerSpecie, android.R.layout.simple_spinner_item);
        spinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        editSpecie.setAdapter(spinAdapter);
        editSpecie.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                specie = editSpecie.getSelectedItem().toString();
                switch (specie){
                    case "Chien":
                        editImage.setImageResource(R.drawable.def_chien);
                        break;
                    case "Chat":
                        editImage.setImageResource(R.drawable.def_chat);
                        break;
                    case "Cheval":
                        editImage.setImageResource(R.drawable.def_cheval);
                        break;
                    case "Oiseau":
                        editImage.setImageResource(R.drawable.def_oiseau);
                        break;
                    case "Poisson":
                        editImage.setImageResource(R.drawable.def_poisson);
                        break;
                    case "Lapin":
                        editImage.setImageResource(R.drawable.def_lapin);
                        break;
                    default:
                        editImage.setImageResource(R.drawable.def_autres);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getApplicationContext(),
                        "Select a specie.",
                        Toast.LENGTH_LONG).show();
            }
        });


        editImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {Toast.makeText(getApplicationContext(),
                    "Upload a picture.",
                    Toast.LENGTH_LONG).show();
            chooseImage();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(radMale.isChecked()) gender = 0;
                else if(radFemale.isChecked()) gender = 1;
                else gender = -1;

                name = editName.getText().toString();
                description = editDescription.getText().toString();
                birth = editBirth.getText().toString();
                breed = editBreed.getText().toString();
                if (name != null && breed != null && gender != -1) {
                    // required fields : name, breed, gender and species.
                    // need to add : price, upload local images to db.
                    Pets pet = new Pets(name,
                                        specie,
                                        breed,
                                        gender,
                                        description,
                                        birth,
                                        editImage.getResources().toString());
                    Log.i("Submit Click", pet.toString());

                    sendPet(pet);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Remplissez toutes les cases obligatoires(*).",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void sendPet(Pets pet) {

        PetService petService = ApiClient.getApiClient().create(PetService.class);
        Call<Pets> call = petService.addNewPet(pet);
        call.enqueue(new Callback<Pets>(){
            @Override
            public void onResponse(Call<Pets> call, Response<Pets> response) {
                Log.i("OnResponse:", response.body().toString());
                Toast.makeText(getApplicationContext(),
                        "New pet added :"+response.body().getName(),
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<Pets> call, Throwable t) {
                Toast.makeText(getApplicationContext(),
                        "Something went wrong while uploading the new Pet.-->"+t.toString(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }


    public void chooseImage(){
        Intent intent = new Intent();
        intent.setType("/image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void uploadPictures() {
        MultipartBody.Part image1 = prepareImagePart(fPath, "cover");
        MultipartBody.Part image2 = prepareImagePart(fPath, "additionnalImage1");

        Retrofit retrofit = ApiClient.getApiClient();
        ApiInterface apiInterface = retrofit.create(ApiInterface.class);
        Call<ResponseBody> uploader = apiInterface.uploadPictures(image1, image2);
        uploader.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.i("uploadPictures", "Success");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("uploadPictures", "Failure");

            }
        });
    }
    public MultipartBody.Part prepareImagePart(String path, String partName){
        File file = new File(path);
        RequestBody requestBody = RequestBody.create(MediaType.parse(getContentResolver().getType(Uri.fromFile(file))), file);
        return MultipartBody.Part.createFormData(partName, file.getName(), requestBody);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
