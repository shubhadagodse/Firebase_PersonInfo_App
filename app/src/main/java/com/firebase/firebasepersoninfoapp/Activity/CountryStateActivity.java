package com.firebase.firebasepersoninfoapp.Activity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.firebase.firebasepersoninfoapp.Fragment.CountryListFragment;
import com.firebase.firebasepersoninfoapp.R;

public class CountryStateActivity extends AppCompatActivity implements CountryListFragment.FragmentCountryStateListener {
    public static FragmentTransaction transaction;
    private static final String SHARED_PREFS_COUNTRY ="country";
    private static final String SHARED_PREFS_STATE ="state";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_state);
        initFragment();
    }

    private void initFragment() {
         FragmentManager manager = getSupportFragmentManager();
         transaction = manager.beginTransaction();
         CountryListFragment f1= new CountryListFragment();
         transaction.add(R.id.a_country_state_frame,f1);
         transaction.commit();
    }

    @Override
    public void onInputCountryStateSent(String country, String state) {
        Intent intent = new Intent();
        intent.putExtra(SHARED_PREFS_COUNTRY,country);
        intent.putExtra(SHARED_PREFS_STATE,state);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }
}

