package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.Fragment.CountryListFragment;
import com.firebase.firebasepersoninfoapp.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

public class PersonActivity extends AppCompatActivity implements CountryListFragment.FragmentCountryStateListener {

    private FirebaseFirestore db;
    private String TAG = " ";
    private Button bSetDate, bSetCountry;
    private Button b_a_PersonActivity_Done, b_a_PersonActivity_Cancel;
    private TextView tvFirstName, tvLastName, tvAge, tvEmail, tvPhone, tvBirthDate, tvCountry, tvState;
    private String firstName;
    private String lastName;
    private String age;
    private String phone;
    private String email;
    private String dateOfBirth;
    private String country;
    private String state;
    private String birthdate;

    private TextView country1,state1;

    private static final String SHARED_PREFS = "shared_preferences";
    private static final String SHARED_PREFS_FIRSTNAME ="firstName";
    private static final String SHARED_PREFS_LASTNAME ="lastName";
    private static final String SHARED_PREFS_AGE ="age";
    private static final String SHARED_PREFS_EMAIL ="email";
    private static final String SHARED_PREFS_PHONE ="phone";
    private static final String SHARED_PREFS_DATEOFBIRTH ="dateOfBirth";
    private static final String SHARED_PREFS_COUNTRY ="country";
    private static final String SHARED_PREFS_STATE ="state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        loadData();

        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        initData();
        openDateActivity();
        openCountryStateActivity();

        b_a_PersonActivity_Done = findViewById(R.id.bSubmit);
        b_a_PersonActivity_Done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CollectionReference dbUsers = db.collection("users");
                Users users = new Users(
                        SHARED_PREFS_FIRSTNAME,
                        SHARED_PREFS_LASTNAME,
                        SHARED_PREFS_AGE,
                        SHARED_PREFS_PHONE,
                        SHARED_PREFS_DATEOFBIRTH,
                        SHARED_PREFS_EMAIL,
                        SHARED_PREFS_COUNTRY,
                        SHARED_PREFS_STATE
                );
                dbUsers.add(users)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(PersonActivity.this,"User added to firebase",Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(PersonActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

//        Intent listOfUsers = new Intent(PersonActivity.this,ListOfUsers.class);
//        startActivityForResult(listOfUsers,0);

//        b_aPerson_Cancel.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });

//        b_aPerson_Done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                saveData();
//            }
//        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 1 :
                Intent intent = new Intent(PersonActivity.this,PersonActivity.class);
                startActivityForResult(intent,3);
                break;

            case 2:
                Intent intent1 = new Intent(PersonActivity.this,ListOfUsers.class);
                startActivityForResult(intent1,4);
                break;

            default:
                Intent intent2 = new Intent(PersonActivity.this,PersonActivity.class);
                startActivityForResult(intent2,3);
        }

        return super.onOptionsItemSelected(item);
    }

    private void initData() {
        String firstName = findViewById(R.id.a_main_tv_fname).toString();
        String lastName = findViewById(R.id.a_main_tv_lname).toString();
        String age = findViewById(R.id.a_main_tv_age).toString();
        String email =findViewById(R.id.a_main_tv_email).toString();
        String phone = findViewById(R.id.a_main_tv_phone).toString();
        String dateOfBirth = findViewById(R.id.a_main_tv_birthdate).toString();
        String country = findViewById(R.id.a_main_tv_country).toString();
        String state = findViewById(R.id.a_main_tv_state).toString();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SHARED_PREFS_FIRSTNAME,firstName);
        outState.putString(SHARED_PREFS_LASTNAME, lastName);
        outState.putString(SHARED_PREFS_AGE, age);
        outState.putString(SHARED_PREFS_EMAIL, email);
        outState.putString(SHARED_PREFS_PHONE,phone);
        outState.putString(SHARED_PREFS_DATEOFBIRTH, dateOfBirth);
        outState.putString(SHARED_PREFS_COUNTRY,country);
        outState.putString(SHARED_PREFS_STATE,state);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        firstName = savedInstanceState.getString(SHARED_PREFS_FIRSTNAME);
        lastName = savedInstanceState.getString(SHARED_PREFS_LASTNAME);
        age = savedInstanceState.getString(SHARED_PREFS_AGE);
        email = savedInstanceState.getString(SHARED_PREFS_EMAIL);
        phone = savedInstanceState.getString(SHARED_PREFS_PHONE);
        dateOfBirth = savedInstanceState.getString(SHARED_PREFS_DATEOFBIRTH);
        country = savedInstanceState.getString(SHARED_PREFS_COUNTRY);
        state = savedInstanceState.getString(SHARED_PREFS_STATE);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();

        editor.putString(SHARED_PREFS_FIRSTNAME,firstName);
        editor.putString(SHARED_PREFS_LASTNAME,lastName);
        editor.putString(SHARED_PREFS_AGE,age);
        editor.putString(SHARED_PREFS_EMAIL,email);
        editor.putString(SHARED_PREFS_PHONE,phone);
        editor.putString(SHARED_PREFS_DATEOFBIRTH,dateOfBirth);
        editor.putString(SHARED_PREFS_COUNTRY,country);
        editor.putString(SHARED_PREFS_STATE,state);
        editor.apply();
        editor.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        firstName = sharedPreferences.getString(SHARED_PREFS_FIRSTNAME,"");
        lastName = sharedPreferences.getString(SHARED_PREFS_LASTNAME, "");
        age = sharedPreferences.getString(SHARED_PREFS_AGE, "");
        email = sharedPreferences.getString(SHARED_PREFS_EMAIL, "");
        phone = sharedPreferences.getString(SHARED_PREFS_PHONE,"");
        dateOfBirth = sharedPreferences.getString(SHARED_PREFS_DATEOFBIRTH, "");
        country = sharedPreferences.getString(SHARED_PREFS_COUNTRY,"");
        state = sharedPreferences.getString(SHARED_PREFS_STATE,"");

    }

    public void openCountryStateActivity() {
        tvCountry = findViewById(R.id.a_main_tv_country);
        tvState = findViewById(R.id.a_main_tv_state);
        bSetCountry = findViewById(R.id.a_main_bSetCountry);

        bSetCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this,CountryStateActivity.class);
                intent.putExtra("country",country);
                intent.putExtra("state",state);
                startActivityForResult(intent,2);
//                intent.putExtra("country",country);

//                String country = intent.getStringExtra("country");
                tvCountry.setText(country);
                tvState.setText(state);
                saveData();
            }
        });

    }

    public void openDateActivity() {
        tvBirthDate  = findViewById(R.id.a_main_tv_birthdate);
        bSetDate = findViewById(R.id.a_main_bSetDate);
        bSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, DateActivity.class);
                startActivityForResult(intent,1);

                birthdate = intent.getStringExtra("birthdate");
                tvBirthDate.setText(birthdate);
                saveData();
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //DateActivity
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                String birthdate = data.getStringExtra("RESULT");
                Log.i("bdate==",birthdate);
                tvBirthDate.setText(" "+birthdate);

            }
            if (resultCode == RESULT_CANCELED) {
                tvBirthDate.setText("Nothing selected");
            }
        }

        //CountryStateActivity
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                String country = data.getStringExtra("message country");
                String state = data.getStringExtra("message state");
                tvCountry.setText(" "+country);
                tvState.setText(state);
                Log.i("country==",country);

            }
            if (resultCode == RESULT_CANCELED) {
                tvCountry.setText("Nothing selected");
                tvState.setText("Nothing selected");
            }
        }

    }

    @Override
    public void onInputCountryStateSent(String country, String state) {
        tvCountry.setText(country);
        tvState.setText(state);

    }
}
