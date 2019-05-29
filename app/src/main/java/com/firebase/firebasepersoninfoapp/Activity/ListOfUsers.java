package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.firebasepersoninfoapp.R;
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

    private static final String TAG = "";
    ListView lv;
    FirebaseFirestore database;
    DocumentReference ref;
    private ArrayList<String> ulist;
    ArrayAdapter<String> uadapter;
    Users user;
    RecyclerView listOfUsers;
    UsersDataAdapter usersDataAdapter;

    List<Users> userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        userList = new ArrayList<>();
        usersDataAdapter = new UsersDataAdapter(getApplicationContext(),userList);

        database = FirebaseFirestore.getInstance();
        ref = database.collection("users").document();

        listOfUsers = findViewById(R.id.a_users_rv_ulist);
//        listOfUsers.setHasFixedSize(true);
//        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
//        listOfUsers.setAdapter(usersDataAdapter);
        Log.i("TAG","List Of Users == "+listOfUsers);

        usersDataAdapter.startListening();

//        ulist = new ArrayList<>();
//        uadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ulist);
//        lv.setAdapter(uadapter);

        receiveData();
//
//        Intent intentForListOfUsers = getIntent();
//        intentForListOfUsers.putExtra("listOfUsers", String.valueOf(listOfUsers));
//        startActivityForResult(intentForListOfUsers,1001);


    }

    private void receiveData() {
        database.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()) {
                    if(dc.getType() == DocumentChange.Type.ADDED) {
                        String userId = dc.getDocument().getId();
                        Users u = dc.getDocument().toObject(Users.class).withId(userId);
                        userList.add(u);
                        Log.i("TAG","user info ="+userList.size());

                        usersDataAdapter.notifyDataSetChanged();
                    }
                }

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


                }


                    ref.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                        @Override
                        public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                            documentSnapshot.contains("firstname");

                        }
                    });

            }
        });

    }
}
