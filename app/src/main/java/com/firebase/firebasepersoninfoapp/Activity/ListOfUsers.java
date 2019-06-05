package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.firebase.firebasepersoninfoapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class ListOfUsers extends AppCompatActivity {

    FirebaseFirestore database;
    MyRecyclerViewAdapter usersDataAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;

    List<Users> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        mRecyclerView = findViewById(R.id.list_of_users_from_firebase);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        userList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot usr: dataSnapshot.getChildren()) {
                        Users u = usr.getValue(Users.class);
                        userList.add(u);
                    }
                    mAdapter = new MyRecyclerViewAdapter(ListOfUsers.this,userList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        database = FirebaseFirestore.getInstance();
        receiveData();
    }

    private void receiveData() {
        database.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()) {
                    if(dc.getType() == DocumentChange.Type.ADDED) {
                        String userId = dc.getDocument().getId();
//                        String u = dc.getDocument().toObject(Users.class).withId(userId);
//                        userList.add(u);
                        Log.i("TAG","user info ="+userList.size());

//                        usersDataAdapter.notifyDataSetChanged();
                    }
                }
                Log.i("TAG","User list size = "+userList.size());


                for(DocumentSnapshot ds: queryDocumentSnapshots) {
                    String fname = ds.getString("firstname");
                    String lname = ds.getString("lastname");
                    String age = ds.getString("age");
                    String email = ds.getString("email");
                    String phone = ds.getString("phone");
                    String birthdate = ds.getString("birthdate");
                    String country = ds.getString("country");
                    String state = ds.getString("state");

                    Log.i("TAG","User info== "+fname+" "+lname+" "+age+" "+email+" "+phone+" "+birthdate+" "+country+" "+state);
                    String s =String.valueOf(Users.class);
//                    return +fname+" "+lname+" "+age+" "+email+" "+phone+" "+birthdate+" "+country+" "+state;
//                    return;

                }

            }
        });

    }

}
