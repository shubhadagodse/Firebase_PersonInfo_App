package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserDetailsActivity extends AppCompatActivity {
    TextView outFname, outLname, outAge, outEmail, outPhone, outBirthdate, outCountry, outState;
    TextView a_user_details_tv_user;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        a_user_details_tv_user = findViewById(R.id.a_userDetails_tv_user);

        outFname = findViewById(R.id.a_userdetails_tv_f_name);
        outLname = findViewById(R.id.a_userdetails_tv_l_name);
        outAge = findViewById(R.id.a_userdetails_tv_age);
        outEmail = findViewById(R.id.a_userdetails_tv_email);
        outPhone = findViewById(R.id.a_userdetails_tv_phone);
        outBirthdate = findViewById(R.id.a_userdetails_tv_bdate);
        outCountry = findViewById(R.id.a_userdetails_tv_country);
        outState = findViewById(R.id.a_userdetails_tv_state);

        String uid= getIntent().getStringExtra(MyRecyclerViewAdapter.USER_KEY);
        Log.i("TAG","USER_ID=="+uid);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users").child("uid");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                Log.i("TAG", "User in UserDetails=="+user);
//                user.setUid(dataSnapshot.getKey());
//                a_user_details_tv_user.setText(user.getFirstname());

                outFname.setText(user.getFirstname());
                outLname.setText(user.getLastname());
                outAge.setText(user.getAge());
                outPhone.setText(user.getPhone());
                outBirthdate.setText(user.getBirthdate());
                outEmail.setText(user.getEmail());
                outCountry.setText(user.getCountry());
                outCountry.setText(user.getState());
                
                a_user_details_tv_user.setText(user.getFirstname()
                        +" "+user.getLastname()+
                        " "+user.getAge()+" "+user.getEmail()+" "+user.getPhone()+
                        " "+user.getBirthdate()+" "+user.getCountry()+" "+user.getState());

                Log.i("TAG","User=="+user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
