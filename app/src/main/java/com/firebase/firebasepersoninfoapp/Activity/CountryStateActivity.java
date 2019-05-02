package com.firebase.firebasepersoninfoapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class CountryStateActivity extends AppCompatActivity {

    private Button bDone;
    private Button bCancel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_state);

        bDone = findViewById(R.id.a_country_b_Done);
        bCancel = findViewById(R.id.a_country_b_Cancel);

        initFragment();

        bDone.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = getIntent();
                final String country = intent1.getStringExtra("country");
                Log.i("country==",country);

                CountryListFragment countryFragment = new CountryListFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.container,countryFragment).commit();

                Intent intent = new Intent();
                intent.putExtra("RESULT",country);
                setResult(RESULT_OK,intent);
                finish();
            }
        });


        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    private void initFragment() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction t = manager.beginTransaction();
        CountryListFragment f1= new CountryListFragment();
        t.add(R.id.A_CountryState_frame1,f1);
        t.commit();
    }

    public void getCountry(String s) {
        FragmentManager manager1 = getSupportFragmentManager();
        FragmentTransaction t1 =manager1.beginTransaction();
        StateListFragment p1 = new StateListFragment();
        Bundle b1 = new Bundle();
        b1.putString("s",s);
        p1.setArguments(b1);
        t1.replace(R.id.A_CountryState_frame1,p1);
        t1.commit();
    }

    public void getState(String s) {

    }
}

