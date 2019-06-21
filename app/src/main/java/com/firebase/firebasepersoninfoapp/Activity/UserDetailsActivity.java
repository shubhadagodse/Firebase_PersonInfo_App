package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsActivity extends AppCompatActivity {
    TextView outFname, outLname, outAge, outEmail, outPhone, outBirthdate, outCountry, outState;
    TextView a_user_details_tv_user;
    String stringFname, stringLname, stringAge, stringPhone, stringEmail, stringCountry, stringState, stringBirthdate;
    FirebaseFirestore database;
    List<String> uArrayList = new ArrayList<String>();
    RecyclerView recyclerView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        database = FirebaseFirestore.getInstance();
        recyclerView =findViewById(R.id.list_of_users_from_firebase);
        a_user_details_tv_user = findViewById(R.id.a_userDetails_tv_user);

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

        Log.i("TAG", "StringFname==" + stringFname);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        final String USER_KEY = databaseReference.push().getKey();

        Log.i("TAG", "jfkdnkc      h jdfjdojfj;oooooooooooooooo" + USER_KEY);
        final String uid = databaseReference.getKey();
        Log.i("TAG", "Uid in UserDetails = " + uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull final DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                Log.i("TAG", "User in UserDetails==" + user);
//                showData(dataSnapshot);

                database.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {

                        for (DocumentSnapshot ds : queryDocumentSnapshots) {

//                            for (int x = recyclerView.getChildCount(), i = 0; i < x; ++i) {
//                                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));


                                String fname = ds.getString("firstname");
                                String lname = ds.getString("lastname");
                                String age = ds.getString("age");
                                String email = ds.getString("email");
                                String phone = ds.getString("phone");
                                String birthdate = ds.getString("birthdate");
                                String country = ds.getString("country");
                                String state = ds.getString("state");
                                Log.i("TAG", "fname=" + fname);

                                outFname.setText(fname);
                                outLname.setText(lname);
                                outAge.setText(age);
                                outEmail.setText(email);
                                outPhone.setText(phone);
                                outBirthdate.setText(birthdate);
                                outCountry.setText(country);
                                outState.setText(state);

                            }
//                        }
                }
        });


    }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
    });
    }

}

