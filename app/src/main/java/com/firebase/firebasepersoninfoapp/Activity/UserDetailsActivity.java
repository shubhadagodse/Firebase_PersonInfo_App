package com.firebase.firebasepersoninfoapp.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

public class UserDetailsActivity extends AppCompatActivity {
    private TextView tvFname, tvLname, tvAge, tvEmail, tvPhone, tvBirthdate, tvCountry, tvState;
    private String stringFname, stringLname, stringAge, stringPhone, stringEmail, stringCountry, stringState, stringBirthdate;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        tvFname = findViewById(R.id.a_userdetails_tv_f_name);
        tvLname = findViewById(R.id.a_userdetails_tv_l_name);
        tvAge = findViewById(R.id.a_userdetails_tv_age);
        tvEmail = findViewById(R.id.a_userdetails_tv_email);
        tvPhone = findViewById(R.id.a_userdetails_tv_phone);
        tvBirthdate = findViewById(R.id.a_userdetails_tv_bdate);
        tvCountry = findViewById(R.id.a_userdetails_tv_country);
        tvState = findViewById(R.id.a_userdetails_tv_state);

        stringFname = getIntent().getStringExtra("firstname");
        stringLname = getIntent().getStringExtra("lastname");
        stringAge = getIntent().getStringExtra("age");
        stringEmail = getIntent().getStringExtra("email");
        stringPhone = getIntent().getStringExtra("phone");
        stringBirthdate = getIntent().getStringExtra("birthdate");
        stringCountry = getIntent().getStringExtra("country");
        stringState = getIntent().getStringExtra("state");

        tvFname.setText(stringFname);
        tvLname.setText(stringLname);
        tvAge.setText(stringAge);
        tvEmail.setText(stringEmail);
        tvPhone.setText(stringPhone);
        tvBirthdate.setText(stringBirthdate);
        tvCountry.setText(stringCountry);
        tvState.setText(stringState);

    }

}

