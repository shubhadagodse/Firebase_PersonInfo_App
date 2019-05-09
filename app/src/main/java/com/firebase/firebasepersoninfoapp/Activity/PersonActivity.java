package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import android.view.Menu;

public class PersonActivity extends AppCompatActivity {

    private FirebaseFirestore db;
    private String TAG = " ";
    private Button bSetDate, bSetCountry;
    private Button b_aPerson_Done, b_aPerson_Cancel;
    private TextView tvBirthDate, tvCountry;
    private String firstName;
    private String lastName;
    private String age;
    private String email;
    private String dateOfBirth;
    private String country;
    private String state;

    private static final String SHARED_PREFS = "shared_preferences";
    private static final String SHARED_PREFS_FIRSTNAME ="firstName";
    private static final String SHARED_PREFS_LASTNAME ="lastName";
    private static final String SHARED_PREFS_AGE ="age";
    private static final String SHARED_PREFS_EMAIL ="email";
    private static final String SHARED_PREFS_DATEOFBIRTH ="dateOfBirth";
    private static final String SHARED_PREFS_COUNTRY ="country";
    private static final String SHARED_PREFS_STATE ="state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        loadData();

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        tvBirthDate  = findViewById(R.id.a_main_tv_birthdate);
        bSetDate = findViewById(R.id.a_main_bSetDate);
        bSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, DateActivity.class);
                startActivityForResult(intent,1);

                String birthdate = intent.getStringExtra("birthdate");
                tvBirthDate.setText(birthdate);
                saveData();
            }
        });

        tvCountry = findViewById(R.id.a_main_tv_country);
        bSetCountry = findViewById(R.id.a_main_bSetCountry);

        bSetCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this,CountryStateActivity.class);
                startActivityForResult(intent,2);
//                intent.putExtra("country",country);

                String country = intent.getStringExtra("country");
                tvCountry.setText(country);
                saveData();

                //openCountryStateActivity();
            }
        });


        initData();

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

    private void initData() {
        String firstName = findViewById(R.id.a_main_tv_fname).toString();
        String lastName = findViewById(R.id.a_main_tv_lname).toString();
        String age = findViewById(R.id.a_main_tv_age).toString();
        String email =findViewById(R.id.a_main_tv_email).toString();
        String dateOfBirth = findViewById(R.id.a_main_tv_birthdate).toString();
        String country = findViewById(R.id.a_main_tv_country).toString();
        String state = findViewById(R.id.a_main_tv_state).toString();


    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SHARED_PREFS_FIRSTNAME, firstName);
        outState.putString(SHARED_PREFS_LASTNAME, lastName);
        outState.putString(SHARED_PREFS_AGE, age);
        outState.putString(SHARED_PREFS_EMAIL, email);
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
        dateOfBirth = sharedPreferences.getString(SHARED_PREFS_DATEOFBIRTH, "");
        country = sharedPreferences.getString(SHARED_PREFS_COUNTRY,"");
        state = sharedPreferences.getString(SHARED_PREFS_STATE,"");

    }

    public void openCountryStateActivity() {

        Intent incomingIntent = getIntent();
        String country = incomingIntent.getStringExtra("country");
        tvCountry.setText(country);

        bSetCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this, CountryStateActivity.class);
                startActivityForResult(intent,2);
            }
        });

    }

    public void openDateActivity() {

        Intent incomingIntent = getIntent();
        String birthdate = incomingIntent.getStringExtra("birthdate");
        tvBirthDate.setText(birthdate);

        bSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this,DateActivity.class);
                startActivityForResult(intent,1);
            }
        });


    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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

        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                String country = data.getStringExtra("RESULT");
                tvCountry.setText(" "+country);
                //Log.i("country==",country);

            }
            if (resultCode == RESULT_CANCELED) {
                tvCountry.setText("Nothing selected");
            }
        }
    }
}
