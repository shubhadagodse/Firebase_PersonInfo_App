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
    String stringFname,stringLname, stringAge, stringPhone, stringEmail, stringCountry, stringState,stringBirthdate;
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

        stringFname = getIntent().getStringExtra("firstname");
        stringLname = getIntent().getStringExtra("lastname");
        stringAge = getIntent().getStringExtra("age");
        stringEmail = getIntent().getStringExtra("email");
        stringPhone = getIntent().getStringExtra("phone");
        stringBirthdate = getIntent().getStringExtra("birthdate");
        stringCountry = getIntent().getStringExtra("country");
        stringState = getIntent().getStringExtra("state");

//        outFname.setText(stringFname);
//        outLname.setText(stringLname);
//        outAge.setText(stringAge);
//        outEmail.setText(stringEmail);
//        outPhone.setText(stringPhone);
//        outBirthdate.setText(stringBirthdate);
//        outCountry.setText(stringCountry);
//        outState.setText(stringState);


//        String uid= getIntent().getStringExtra(MyRecyclerViewAdapter.USER_KEY);
//        Log.i("TAG","USER_ID=="+uid);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
        String USER_KEY = databaseReference.push().getKey();

        Log.i("TAG","jfkdnkc      h jdfjdojfj;oooooooooooooooo"+USER_KEY);
        String uid= databaseReference.getKey();
        Log.i("TAG","Uid in UserDetails = "+uid);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                Log.i("TAG", "User in UserDetails=="+user);
                showData(dataSnapshot);
//                user.setUid(dataSnapshot.getKey());
//                a_user_details_tv_user.setText(user.getFirstname());

/*                outFname.setText(user.getFirstname());
                outLname.setText(user.getLastname());
                outAge.setText(user.getAge());
                outPhone.setText(user.getPhone());
                outBirthdate.setText(user.getBirthdate());
                outEmail.setText(user.getEmail());
                outCountry.setText(user.getCountry());
                outCountry.setText(user.getState());
*/
//                a_user_details_tv_user.setText(user.getFirstname()
//                        +" "+user.getLastname()+
//                        " "+user.getAge()+" "+user.getEmail()+" "+user.getPhone()+
//                        " "+user.getBirthdate()+" "+user.getCountry()+" "+user.getState());

//                Log.i("TAG","User=="+user);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            Users u = new Users();
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("users");
            String USER_KEY = databaseReference.push().getKey();
            Log.i("TAG","USER_KEY == "+USER_KEY);

            String firstname = ds.child("firstname").getValue().toString();
            Log.i("TAG","firstname=="+firstname);
            String lastname = ds.child("lastname").getValue().toString();
            Log.i("TAG","lastname=="+lastname);
            String age= ds.child("age").getValue().toString();
            Log.i("TAG","age=="+age);
            String phone = ds.child("phone").getValue().toString();
            Log.i("TAG","phone=="+phone);
            String email = ds.child("email").getValue().toString();
            Log.i("TAG","email=="+email);
            String birthdate = ds.child("birthdate").getValue().toString();
            Log.i("TAG","birthdate=="+birthdate);
            String country = ds.child("country").getValue().toString();
            Log.i("TAG","country=="+country);
            String state = ds.child("state").getValue().toString();
            Log.i("TAG","state=="+state);

            outFname.setText(ds.child(USER_KEY).getValue(Users.class).getFirstname());
            outLname.setText(ds.child(USER_KEY).getValue(Users.class).getLastname());
            outAge.setText(ds.child(USER_KEY).getValue(Users.class).getAge());
            Log.i("TAG","outFname=="+outFname);
            outPhone.setText(ds.child(USER_KEY).getValue(Users.class).getPhone());


            u.setFirstname(ds.child(USER_KEY).getValue(Users.class).getFirstname());
            u.setLastname(ds.child(USER_KEY).getValue(Users.class).getLastname());
            u.setAge(ds.child(USER_KEY).getValue(Users.class).getAge());
            u.setPhone(ds.child(USER_KEY).getValue(Users.class).getPhone());
            u.setEmail(ds.child(USER_KEY).getValue(Users.class).getEmail());
            u.setCountry(ds.child(USER_KEY).getValue(Users.class).getCountry());
            u.setState(ds.child(USER_KEY).getValue(Users.class).getState());

        }
    }
}
