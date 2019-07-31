package com.firebase.firebasepersoninfoapp.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
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
    private Button bSetDate, bSetCountry;
    private Button bDone, bCancel;
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
    private static final String SHARED_PREFS = "shared_preferences";
    private static final String SHARED_PREFS_FIRSTNAME ="firstName";
    private static final String SHARED_PREFS_LASTNAME ="lastName";
    private static final String SHARED_PREFS_AGE ="age";
    private static final String SHARED_PREFS_EMAIL ="email";
    private static final String SHARED_PREFS_PHONE ="phone";
    private static final String SHARED_PREFS_DATEOFBIRTH ="dateOfBirth";
    private static final String SHARED_PREFS_COUNTRY ="country";
    private static final String SHARED_PREFS_STATE ="state";
    private AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
        loadData();
        final FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .build();
        firestore.setFirestoreSettings(settings);
        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();
        initData();
        openDateActivity();
        openCountryStateActivity();
        validation();
        textWatcherMethod();
        saveData();

        bDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()) {
                    CollectionReference dbUsers = db.collection("users");
                    Users users = new Users(
                            tvFirstName.getText().toString(),
                            tvLastName.getText().toString(),
                            tvAge.getText().toString(),
                            tvEmail.getText().toString(),
                            tvPhone.getText().toString(),
                            tvBirthDate.getText().toString(),
                            tvCountry.getText().toString(),
                            tvState.getText().toString()
                    );
                    dbUsers.add(users)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Toast.makeText(PersonActivity.this,R.string.toast_message,Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(PersonActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                        }
                    });
                }
                else {
                    Toast.makeText(PersonActivity.this,R.string.toast_error_message,Toast.LENGTH_SHORT).show();
                }

            }
        });
        saveData();
    }

    @Override
    protected void onPause() {
        Log.i("TAG","onPause");
        loadData();
        saveData();
        super.onPause();
    }

    @Override
    protected void onResume() {
        saveData();
        Log.i("TAG","onResume");
        loadData();
        super.onResume();
    }

    @Override
    protected void onStop() {
        Log.i("TAG","onStop");
        loadData();
        saveData();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("TAG","onDestroy");
        loadData();
        saveData();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.listOfUsers) {
            Toast.makeText(this,R.string.toat_list_of_users,Toast.LENGTH_SHORT).show();
            Intent intentForListOfUsers = new Intent(PersonActivity.this,ListOfUsers.class);
            startActivityForResult(intentForListOfUsers,1001);
        }
        else {
            Intent intent2 = new Intent(PersonActivity.this,PersonActivity.class);
            startActivityForResult(intent2,3);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SHARED_PREFS_FIRSTNAME, tvFirstName.getText().toString());
        outState.putString(SHARED_PREFS_LASTNAME, tvLastName.getText().toString());
        outState.putString(SHARED_PREFS_AGE, tvAge.getText().toString());
        outState.putString(SHARED_PREFS_EMAIL, tvEmail.getText().toString());
        outState.putString(SHARED_PREFS_PHONE, tvPhone.getText().toString());
        outState.putString(SHARED_PREFS_DATEOFBIRTH, tvBirthDate.getText().toString());
        outState.putString(SHARED_PREFS_COUNTRY, tvCountry.getText().toString());
        outState.putString(SHARED_PREFS_STATE, tvState.getText().toString());
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

        tvFirstName.setText(firstName);
        tvLastName.setText(lastName);
        tvAge.setText(age);
        tvEmail.setText(email);
        tvPhone.setText(phone);
        tvBirthDate.setText(dateOfBirth);
        tvCountry.setText(country);
        tvState.setText(state);

    }

    private void validation() {
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_fname,R.string.validation,R.string.err_fname);
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_lname,R.string.validation,R.string.err_lname);
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_age,R.string.number_validation,R.string.err_age);
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_email, Patterns.EMAIL_ADDRESS,R.string.err_email);
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_phone, Patterns.PHONE,R.string.err_phone);
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_country, RegexTemplate.NOT_EMPTY,R.string.err_country);
        awesomeValidation.addValidation(PersonActivity.this,R.id.a_person_et_state, RegexTemplate.NOT_EMPTY,R.string.err_state);
    }

    private void textWatcherMethod() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    String fname = tvFirstName.getText().toString().trim();
                    String lname = tvLastName.getText().toString().trim();
                    String age = tvAge.getText().toString().trim();
                    String email = tvEmail.getText().toString().trim();
                    String phone = tvPhone.getText().toString();
                    String birthdate = tvBirthDate.getText().toString().trim();
                    String country = tvCountry.getText().toString().trim();
                    String state = tvState.getText().toString().trim();

                    bDone.setEnabled(!fname.isEmpty() && !lname.isEmpty() && !age.isEmpty() && !email.isEmpty()
                            && !phone.isEmpty() && !birthdate.isEmpty() && !country.isEmpty() && !state.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        tvFirstName.addTextChangedListener(textWatcher);
        tvLastName.addTextChangedListener(textWatcher);
        tvAge.addTextChangedListener(textWatcher);
        tvEmail.addTextChangedListener(textWatcher);
        tvPhone.addTextChangedListener(textWatcher);
        tvBirthDate.addTextChangedListener(textWatcher);
        tvCountry.addTextChangedListener(textWatcher);
        tvState.addTextChangedListener(textWatcher);
    }

    private void initData() {
        tvFirstName = findViewById(R.id.a_person_et_fname);
        Log.i("TAG","firstname from init data =" +tvFirstName.getText().toString());
        tvLastName = findViewById(R.id.a_person_et_lname);
        tvAge = findViewById(R.id.a_person_et_age);
        tvEmail =findViewById(R.id.a_person_et_email);
        tvPhone = findViewById(R.id.a_person_et_phone);
        tvBirthDate = findViewById(R.id.a_person_et_birthdate);
        tvBirthDate.setEnabled(false);
        tvCountry = findViewById(R.id.a_person_et_country);
        tvCountry.setEnabled(false);
        tvState = findViewById(R.id.a_person_et_state);
        tvState.setEnabled(false);
        bDone = findViewById(R.id.a_person_bSubmit);
        bCancel = findViewById(R.id.a_person_bCancel);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        firstName = tvFirstName.getText().toString();
        Log.i("TAG","fname savedata == "+firstName);
        lastName = tvLastName.getText().toString();
        age = tvAge.getText().toString();
        email = tvEmail.getText().toString();
        phone = tvPhone.getText().toString();
        dateOfBirth = tvBirthDate.getText().toString();
        country = tvCountry.getText().toString();
        state = tvState.getText().toString();

        editor.putString(SHARED_PREFS_FIRSTNAME, firstName);
        Log.i("TAG","Shared pref fname"+SHARED_PREFS_FIRSTNAME);
        editor.putString(SHARED_PREFS_LASTNAME, lastName);
        editor.putString(SHARED_PREFS_AGE, age);
        editor.putString(SHARED_PREFS_EMAIL, email);
        editor.putString(SHARED_PREFS_PHONE, phone);
        editor.putString(SHARED_PREFS_DATEOFBIRTH, dateOfBirth);
        editor.putString(SHARED_PREFS_COUNTRY, country);
        editor.putString(SHARED_PREFS_STATE, state);
        editor.apply();
        editor.commit();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        firstName = sharedPreferences.getString(SHARED_PREFS_FIRSTNAME,firstName);
        lastName = sharedPreferences.getString(SHARED_PREFS_LASTNAME, lastName);
        age = sharedPreferences.getString(SHARED_PREFS_AGE, age);
        email = sharedPreferences.getString(SHARED_PREFS_EMAIL, email);
        phone = sharedPreferences.getString(SHARED_PREFS_PHONE,phone);
        dateOfBirth = sharedPreferences.getString(SHARED_PREFS_DATEOFBIRTH, dateOfBirth);
        country = sharedPreferences.getString(SHARED_PREFS_COUNTRY,country);
        state = sharedPreferences.getString(SHARED_PREFS_STATE,state);
    }

    public void openCountryStateActivity() {
        tvCountry = findViewById(R.id.a_person_et_country);
        tvState = findViewById(R.id.a_person_et_state);
        bSetCountry = findViewById(R.id.a_main_bSetCountry);
        bSetCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonActivity.this,CountryStateActivity.class);
                intent.putExtra("country",country);
                intent.putExtra("state",state);
                startActivityForResult(intent,2);
                tvCountry.setText(country);
                tvState.setText(state);
            }
        });
    }

    public void openDateActivity() {
        tvBirthDate  = findViewById(R.id.a_person_et_birthdate);
        bSetDate = findViewById(R.id.a_person_bSetDate);
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
                tvBirthDate.setText(" "+birthdate);
            }
            if (resultCode == RESULT_CANCELED) {
                tvBirthDate.setText(R.string.nothing_selected);
            }
        }

        //CountryStateActivity
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                String country = data.getStringExtra("message country");
                String state = data.getStringExtra("message state");
                tvCountry.setText(" "+country);
                tvState.setText(state);
            }
            if (resultCode == RESULT_CANCELED) {
                tvCountry.setText(R.string.nothing_selected);
                tvState.setText(R.string.nothing_selected);
            }
        }
    }

    @Override
    public void onInputCountryStateSent(String country, String state) {
        tvCountry.setText(country);
        tvState.setText(state);
    }
}
