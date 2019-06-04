package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.firebase.firebasepersoninfoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
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
//    DocumentReference ref;
    DatabaseReference ref;
    RecyclerView listOfUsers;
//    UsersDataAdapter usersDataAdapter;
    MyRecyclerViewAdapter usersDataAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    List<String> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        mRecyclerView = findViewById(R.id.list_of_users_from_firebase);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

        userList = new ArrayList<String>();
        database = FirebaseFirestore.getInstance();
        ref = FirebaseDatabase.getInstance().getReference().child("users");

        initRecyclerview();
        receiveData();
//        readSingleUser();
    }

    private ArrayList<Users> getDataSet() {
    ArrayList result = new ArrayList<Users>();
    for (int index = 0; index <20; index++ ) {
//        Users obj = new Users("users sdvs"+index,"sdsdsefd"+index);
//        result.add(index, obj);
    }
        return result;
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter)mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i("TAG","Clicked on item : "+position);
            }
        });
    }

    private void readSingleUser() {
        DocumentReference u = database.collection("user").document("1rdu6SPnz8wRySFFz7Ql");
        u.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();
                    StringBuilder data =  new StringBuilder("");
                    data.append("First Name :").append(doc.getString("firstname"));
                    data.append("\n");


                }
            }
        });

    }


    private void initRecyclerview() {
        if(usersDataAdapter == null) {
//            usersDataAdapter = new UsersDataAdapter(this,userList);
        }
//        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
//        listOfUsers.setAdapter(usersDataAdapter);
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


//                    ref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
//                        @Override
//                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
//                            documentSnapshot.contains("firstname");
//
//                        }
//                    });

            }
        });

    }

}
