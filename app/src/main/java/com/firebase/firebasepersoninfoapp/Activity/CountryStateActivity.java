package com.firebase.firebasepersoninfoapp.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.Fragment.CountryListFragment;
import com.firebase.firebasepersoninfoapp.Fragment.StateListFragment;
import com.firebase.firebasepersoninfoapp.R;

public class CountryStateActivity extends AppCompatActivity {

    private Button bDone;
    private Button bCancel;
    private TextView country1, state;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_state);

        bDone = findViewById(R.id.a_country_b_Done);
        bCancel = findViewById(R.id.a_country_b_Cancel);

        initFragment();
//        getCountry(country1);
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
        final CountryListFragment f1= new CountryListFragment();
        t.add(R.id.A_CountryState_frame1,f1);
        t.commit();

        bDone.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent1 = getIntent();
                        final String country = intent1.getStringExtra("country");
                        // Log.i("country==",country);

                        CountryListFragment countryFragment = new CountryListFragment();
                        getSupportFragmentManager().beginTransaction().add(R.id.container,countryFragment).commit();

                        Intent intent = new Intent();
                        intent.putExtra("RESULT",country);
                        setResult(RESULT_OK,intent);
                        finish();
                    }
                });


    }

    public void getCountry(String s) {
        final ListView countryList =findViewById(R.id.country_list);
        countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 0) {
                    FragmentManager manager0 =getSupportFragmentManager();
                    FragmentTransaction t0 = manager0.beginTransaction();
                    StateListFragment s1 =new StateListFragment();
                    Toast.makeText(getApplicationContext(),"State from country "+position+"is selected",Toast.LENGTH_SHORT).show();
                 }
                Log.i("Country at position ",position+"is selected");
                Toast.makeText(getApplicationContext(),"Country at "+position+"is selected",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void getState(String s) {
        FragmentManager manager1 = getSupportFragmentManager();
        FragmentTransaction t1 =manager1.beginTransaction();
        StateListFragment p1 = new StateListFragment();
        Bundle b1 = new Bundle();
        b1.putString("country1",s);
        p1.setArguments(b1);
        t1.replace(R.id.A_CountryState_frame1,p1);
        t1.commit();
    }
}

