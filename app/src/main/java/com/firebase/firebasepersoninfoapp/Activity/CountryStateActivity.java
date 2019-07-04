package com.firebase.firebasepersoninfoapp.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.Fragment.CountryListFragment;
import com.firebase.firebasepersoninfoapp.R;

public class CountryStateActivity extends AppCompatActivity implements CountryListFragment.FragmentCountryStateListener {
    private TextView country1, state1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_state);
        country1 = findViewById(R.id.a_countryState_tv_country);
        state1 = findViewById(R.id.a_countryState_tv_state);

        initFragment();
        getDataFromCountryListFragment();
    }

    public void getDataFromCountryListFragment() {
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            if(bundle.getString("data")!= null) {
                Toast.makeText(getApplicationContext(),"data"+bundle.getString("data"),Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void initFragment() {
         FragmentManager manager = getSupportFragmentManager();
         FragmentTransaction t = manager.beginTransaction();
         CountryListFragment f1= new CountryListFragment();
        t.add(R.id.A_CountryState_frame1,f1);
        t.commit();
    }

    @Override
    public void onInputCountryStateSent(String country, String state) {
        Intent intent = new Intent();
        intent.putExtra("message country",country);
        intent.putExtra("message state",state);
        setResult(Activity.RESULT_OK,intent);
        finish();
                country1.setText(country);
                state1.setText(state);
           }
}

