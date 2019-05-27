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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class ListOfUsers extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv;
    FirebaseDatabase database;
    FirebaseFirestore firestore;
    DatabaseReference ref;
    private ArrayList<String> ulist;
    ArrayAdapter<String> uadapter;
    Users user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        user = new Users();
        lv = findViewById(R.id.list_of_users_from_firebase);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference("users");
        ulist = new ArrayList<>();
        uadapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ulist);
        lv.setAdapter(uadapter);
        addValueEventListener();
//
//        firestore.collection("users").document("one").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//                if (task.isSuccessful()) {
//                    DocumentSnapshot documentSnapshot = task.getResult();
//                    if(documentSnapshot.exists() && documentSnapshot != null) {
//                        String firstname = documentSnapshot.getString("firstname");
//                        String lastname = documentSnapshot.getString("lastname");
//                        String age = documentSnapshot.getString("age");
//                        String email = documentSnapshot.getString("email");
//                        String phone = documentSnapshot.getString("phone");
//                        String birthdate = documentSnapshot.getString("birthdate");
//                        String country = documentSnapshot.getString("country");
//                        String state = documentSnapshot.getString("state");
//                    }
//                    else {
//                        Log.d("TAG","Error : "+ task.getException().getLocalizedMessage());
//                    }
//                }
//
//            }
//        });

        /*
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                ulist.add(value);
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
        }); */
//        addValueEventListener();

    }

    private void addValueEventListener() {
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()) {

                    Users value = dataSnapshot.getValue(Users.class);
                    ulist.add(String.valueOf(value));
                    uadapter.notifyDataSetChanged();

                Users   user = ds.getValue(Users.class);
                System.out.println("User==="+user);
                System.out.println("FirstName="+user.getFirstname());

                    ref.push().setValue(user.getFirstname());
                    ref.push().setValue(user.getLastname());
                    ref.push().setValue(user.getAge());
                    ref.push().setValue(user.getEmail());
                    ref.push().setValue(user.getPhone());
                    ref.push().setValue(user.getBirthdate());
                    ref.push().setValue(user.getCountry());
                    ref.push().setValue(user.getState());
                    ulist.add(String.valueOf(user));

                user.setFirstname("ooooo");
                user.setLastname("pppppppppppp");
                user.setAge("15");
                user.setPhone("9878676767");
                user.setEmail("pppppppppppppp@ppp");
                user.setBirthdate("3/9/19");
                user.setCountry("India");
                user.setState("maharashtra");
                   Log.d(TAG,"Users List=="+user.getFirstname());
                   ulist.add(String.valueOf(user));

                    ulist.add(user.getFirstname());
                    ulist.add(user.getLastname());
                    ulist.add(user.getAge());
                    ulist.add(user.getEmail());
                    ulist.add(user.getPhone());
                    ulist.add(user.getBirthdate());
                    ulist.add(user.getCountry());
                    ulist.add(user.getState());

                    Log.i("TAG","List of users== "+ulist);

                    Intent intentForListOfUsers = getIntent();
                    intentForListOfUsers.putExtra(String.valueOf(ulist)
                            ,1001);
                }
                lv.setAdapter(uadapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds: dataSnapshot.getChildren()) {
            Users usr = new Users();
            usr.setFirstname(ds.child(String.valueOf(user)).getValue(Users.class).getFirstname());
            usr.setLastname(ds.child(String.valueOf(user)).getValue(Users.class).getLastname());
            usr.setAge(ds.child(String.valueOf(user)).getValue(Users.class).getAge());
            usr.setEmail(ds.child(String.valueOf(user)).getValue(Users.class).getEmail());
            usr.setPhone(ds.child(String.valueOf(user)).getValue(Users.class).getPhone());
            usr.setBirthdate(ds.child(String.valueOf(user)).getValue(Users.class).getBirthdate());
            usr.setCountry(ds.child(String.valueOf(user)).getValue(Users.class).getCountry());
            usr.setState(ds.child(String.valueOf(user)).getValue(Users.class).getState());

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

}
