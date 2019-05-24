package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.firebasepersoninfoapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListOfUsers extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv;
    FirebaseDatabase database;
    DatabaseReference ref;
    private ArrayList<String> ulist;
    ArrayAdapter<String> uadapter;
    Users user;
    private String userID;
//    FirebaseListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        user = new Users();
        lv = findViewById(R.id.list_of_users_from_firebase);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference().child("users");
        ulist = new ArrayList<>();
        uadapter = new ArrayAdapter<String>(this,R.layout.users,R.id.first_name,ulist);
//        userID = database.getUId();
        addValueEventListener();



       /* lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                ref.push().setValue(user.getFirstname());
                ref.push().setValue(user.getLastname());
                ref.push().setValue(user.getAge());
                ref.push().setValue(user.getEmail());
                ref.push().setValue(user.getPhone());
                ref.push().setValue(user.getBirthdate());
                ref.push().setValue(user.getCountry());
                ref.push().setValue(user.getState());
            }
        });
        Log.i("TAG","ref list==="+ref);

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Log.i("TAG","User info=="+user.getFirstname());
                Log.i("TAG","USER=="+user);

                user = dataSnapshot.getValue(Users.class);
                Log.i("TAG","User info=="+user.getFirstname());
                ulist.add(user.getFirstname());
                ulist.add(user.getLastname());
                ulist.add(user.getAge());
                ulist.add(user.getEmail());
                ulist.add(user.getPhone());
                ulist.add(user.getBirthdate());
                ulist.add(user.getCountry());
                ulist.add(user.getState());

                lv.setAdapter(uadapter);

                uadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(Users.class);
                ulist.remove(user);

                uadapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
    }

    private void addValueEventListener() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    ulist.clear();
                   user = ds.getValue(Users.class);
                   ulist.add(user.toString());
//                   user.setFirstname(ds.child(userID).getValue(Users.class).getFirstname());

                    ulist.add(user.getFirstname());
                    ulist.add(user.getLastname());
                    ulist.add(user.getAge());
                    ulist.add(user.getEmail());
                    ulist.add(user.getPhone());
                    ulist.add(user.getBirthdate());
                    ulist.add(user.getCountry());
                    ulist.add(user.getState());

                    Log.i("TAG","List of users"+ulist);

                    Intent intentForListOfUsers = getIntent();
                    intentForListOfUsers.putExtra(String.valueOf(ulist),1001);
                }
                lv.setAdapter(uadapter);
            }

            private void showData(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    Users usr = new Users();
                    usr.setFirstname(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getFirstname());
                    usr.setLastname(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getLastname());
                    usr.setAge(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getAge());
                    usr.setEmail(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getEmail());
                    usr.setPhone(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getPhone());
                    usr.setBirthdate(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getBirthdate());
                    usr.setCountry(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getCountry());
                    usr.setState(dataSnapshot.child(String.valueOf(user)).getValue(Users.class).getState());

                    ArrayList<String> array = new ArrayList<>();
                    array.add(usr.getFirstname().toString());
                    array.add(usr.getLastname().toString());
                    array.add(usr.getAge().toString());
                    array.add(usr.getEmail().toString());
                    array.add(usr.getPhone().toString());
                    array.add(usr.getBirthdate().toString());
                    array.add(usr.getCountry().toString());
                    array.add(usr.getState().toString());

                    lv.setAdapter(uadapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
