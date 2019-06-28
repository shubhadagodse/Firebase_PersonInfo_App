package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

public class UserDetailsActivity extends AppCompatActivity {
    TextView outFname, outLname, outAge, outEmail, outPhone, outBirthdate, outCountry, outState;
    String stringFname, stringLname, stringAge, stringPhone, stringEmail, stringCountry, stringState, stringBirthdate;
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        recyclerView =findViewById(R.id.list_of_users_from_firebase);

        outFname = findViewById(R.id.a_userdetails_tv_f_name);
        outLname = findViewById(R.id.a_userdetails_tv_l_name);
        outAge = findViewById(R.id.a_userdetails_tv_age);
        outEmail = findViewById(R.id.a_userdetails_tv_email);
        outPhone = findViewById(R.id.a_userdetails_tv_phone);
        outBirthdate = findViewById(R.id.a_userdetails_tv_bdate);
        outCountry = findViewById(R.id.a_userdetails_tv_country);
        outState = findViewById(R.id.a_userdetails_tv_state);

        stringFname = getIntent().getStringExtra("firstname");
        stringLname = getIntent().getStringExtra("lastname");
        stringAge = getIntent().getStringExtra("age");
        stringEmail = getIntent().getStringExtra("email");
        stringPhone = getIntent().getStringExtra("phone");
        stringBirthdate = getIntent().getStringExtra("birthdate");
        stringCountry = getIntent().getStringExtra("country");
        stringState = getIntent().getStringExtra("state");

        outFname.setText(stringFname);
        outLname.setText(stringLname);
        outAge.setText(stringAge);
        outEmail.setText(stringEmail);
        outPhone.setText(stringPhone);
        outBirthdate.setText(stringBirthdate);
        outCountry.setText(stringCountry);
        outState.setText(stringState);

    }

}

