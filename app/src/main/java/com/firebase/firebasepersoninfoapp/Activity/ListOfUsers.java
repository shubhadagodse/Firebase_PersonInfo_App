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
    FirebaseFirestore firestore;
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
        usersDataAdapter = new UsersDataAdapter(userList);

        user = new Users();
        lv = findViewById(R.id.list_of_users_from_firebase);
        database = FirebaseFirestore.getInstance();
        ref = database.collection("users").document();

        listOfUsers = findViewById(R.id.a_users_rv_ulist);

//        listOfUsers.setHasFixedSize(true);
//        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
//        listOfUsers.setAdapter(usersDataAdapter);

        ulist = new ArrayList<>();
        uadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ulist);
        lv.setAdapter(uadapter);

        receiveData();


//    private void showData(DataSnapshot dataSnapshot) {
//        for(DataSnapshot ds: dataSnapshot.getChildren()) {
//            Users usr = new Users();
//            usr.setFirstname(ds.child(String.valueOf(user)).getValue(Users.class).getFirstname());
//            usr.setLastname(ds.child(String.valueOf(user)).getValue(Users.class).getLastname());
//            usr.setAge(ds.child(String.valueOf(user)).getValue(Users.class).getAge());
//            usr.setEmail(ds.child(String.valueOf(user)).getValue(Users.class).getEmail());
//            usr.setPhone(ds.child(String.valueOf(user)).getValue(Users.class).getPhone());
//            usr.setBirthdate(ds.child(String.valueOf(user)).getValue(Users.class).getBirthdate());
//            usr.setCountry(ds.child(String.valueOf(user)).getValue(Users.class).getCountry());
//            usr.setState(ds.child(String.valueOf(user)).getValue(Users.class).getState());
//
//            ArrayList<String> array = new ArrayList<>();
//            array.add(usr.getFirstname().toString());
//            array.add(usr.getLastname().toString());
//            array.add(usr.getAge().toString());
//            array.add(usr.getEmail().toString());
//            array.add(usr.getPhone().toString());
//            array.add(usr.getBirthdate().toString());
//            array.add(usr.getCountry().toString());
//            array.add(usr.getState().toString());
//
//            lv.setAdapter(uadapter);
//        }
//    }

    }

    private void receiveData() {
        database.collection("users").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                for (DocumentChange dc: queryDocumentSnapshots.getDocumentChanges()) {
                    if(dc.getType() == DocumentChange.Type.ADDED) {
                        Users u = dc.getDocument().toObject(Users.class);
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
