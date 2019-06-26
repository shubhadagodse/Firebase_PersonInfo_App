package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.firebasepersoninfoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
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

public class ListOfUsers extends AppCompatActivity implements MyRecyclerViewAdapter.MyClickListener {

    FirebaseFirestore database;
    MyRecyclerViewAdapter usersDataAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Users> uArrayList;

    List<Users> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        mRecyclerView = findViewById(R.id.list_of_users_from_firebase);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersDataAdapter = new MyRecyclerViewAdapter((ArrayList<Users>) userList);
        mRecyclerView.setAdapter(mAdapter);

        userList = new ArrayList<>();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        String uid = ref.push().getKey();
        Log.i("TAG","User id =="+uid);
        ref.keepSynced(true);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {
                Users user = dataSnapshot.getValue(Users.class);
//                user.setUid(dataSnapshot.getKey());
                user.setUid(ref.getKey());

                uArrayList.add(user);
                usersDataAdapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @android.support.annotation.Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /**************************************************************************************************************************************************************/
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()) {
                    for(DataSnapshot usr: dataSnapshot.getChildren()) {
                        Users u = usr.getValue(Users.class);
                        u.setUid(dataSnapshot.getKey());
                        userList.add(u);
                        usersDataAdapter.notifyDataSetChanged();
                    }
                    mAdapter = new MyRecyclerViewAdapter(ListOfUsers.this, (ArrayList<Users>) userList);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        database = FirebaseFirestore.getInstance();
        uArrayList = new ArrayList<>();
        receiveData();
        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        if(uArrayList.size() > 0)
            uArrayList.clear();

        database.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot querySnapshot: task.getResult()) {
                            Users user = new Users(querySnapshot.getString("firtsname"),
                                    querySnapshot.getString("lastname"),
                                    querySnapshot.getString("age"),
                                    querySnapshot.getString("email"),
                                    querySnapshot.getString("phone"),
                                    querySnapshot.getString("birthdate"),
                                    querySnapshot.getString("country"),
                                    querySnapshot.getString("state"));

                            uArrayList.add(user);
                        }
                        usersDataAdapter = new MyRecyclerViewAdapter(ListOfUsers.this,uArrayList);
                        mRecyclerView.setAdapter(usersDataAdapter);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListOfUsers.this,"TAG",Toast.LENGTH_SHORT).show();
                        Log.i("TAG",e.getMessage());
                    }
                });
    }

    private void receiveData() {
        database.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()) {
                    if(dc.getType() == DocumentChange.Type.ADDED) {
                        String userId = dc.getDocument().getId();
                        Log.i("TAG","user info ="+userList.size());

                        usersDataAdapter.notifyDataSetChanged();
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
                    return;

                }

            }
        });

    }

    /*@Override
    public void onItemClick(int position) {

        uArrayList.get(position);
        Intent intent = new Intent(this,UserDetailsActivity.class);
        intent.putExtra("user", (Parcelable) uArrayList.get(position));
        startActivity(intent);
//        uArrayList.get(position);
        Log.i("TAG","OnUserClick: clicked!"+position);
    }*/

    @Override
    public void onItemClick(View view, int position, boolean isLongClick) {
        uArrayList.get(position);
        Intent intent = new Intent(this,UserDetailsActivity.class);
        intent.putExtra("user", (Parcelable) uArrayList.get(position));
        startActivity(intent);
//        uArrayList.get(position);
        Log.i("TAG","OnUserClick: clicked!"+position);
    }
}
