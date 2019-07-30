package com.firebase.firebasepersoninfoapp.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import com.firebase.firebasepersoninfoapp.Fragment.CountryListFragment;
import com.firebase.firebasepersoninfoapp.R;

public class CountryStateActivity extends AppCompatActivity implements CountryListFragment.FragmentCountryStateListener {
    private TextView tvCountry, tvState;
    public static FragmentTransaction transaction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_state);
        tvCountry = findViewById(R.id.a_countryState_tv_country);
        tvState = findViewById(R.id.a_countryState_tv_state);
        initFragment();
    }

    private void initFragment() {
         FragmentManager manager = getSupportFragmentManager();
         transaction = manager.beginTransaction();
         CountryListFragment f1= new CountryListFragment();
         transaction.add(R.id.a_CountryState_frame1,f1);
         transaction.commit();
    }

    @Override
    public void onInputCountryStateSent(String country, String state) {
        Intent intent = new Intent();
        intent.putExtra("message country",country);
        intent.putExtra("message state",state);
        setResult(Activity.RESULT_OK,intent);
        finish();
        tvCountry.setText(country);
        tvState.setText(state);
    }
}

