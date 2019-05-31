package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.firebase.firebasepersoninfoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
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


    DatabaseReference reference;
    RecyclerView recyclerView;
    ArrayList<Users> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

//       recyclerView = findViewById(R.id.list_of_users_from_firebase);
//        recyclerView.setLayoutManager(new LinearLayoutManager(ListOfUsers.this));
//        list = new ArrayList<Users>();
//        reference = FirebaseDatabase.getInstance().getReference().child("users");
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for(DataSnapshot dataSnapshot1:dataSnapshot.getChildren()) {
//                    Users u = dataSnapshot1.getValue(Users.class);
//                    list.add(u);
//                    Log.i("TAG","List="+list);
//                }
//
//                usersDataAdapter = new UsersDataAdapter(ListOfUsers.this,list);
//                recyclerView.setAdapter(usersDataAdapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                Toast.makeText(ListOfUsers.this,"Something went wrong...Cancelled",Toast.LENGTH_SHORT).show();
//            }
//        });


        userList = new ArrayList<>();
        usersDataAdapter = new UsersDataAdapter(getApplicationContext(), userList);

        database = FirebaseFirestore.getInstance();
        ref = database.collection("users").document();

        listOfUsers = findViewById(R.id.list_of_users_from_firebase);
        listOfUsers.setHasFixedSize(true);
        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
        listOfUsers.setAdapter(usersDataAdapter);
        Log.i("TAG","List Of Users == "+listOfUsers);

//        usersDataAdapter.startListening();


        initRecyclerview();
        receiveData();
        readSingleUser();
//        getData();

//        Intent intentForListOfUsers = getIntent();
//        intentForListOfUsers.putExtra("listOfUsers", String.valueOf(listOfUsers));
//        startActivityForResult(intentForListOfUsers,1);


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

//    private void getData() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        CollectionReference usersCollectionRef = db.collection("users");
//        Query usersQuery = usersCollectionRef
//                .whereEqualTo("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
//
//        usersQuery.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                if(task.isSuccessful()) {
//                    for(QueryDocumentSnapshot document : task.getResult()) {
//                        Users user = document.toObject(Users.class);
//                        userList.add(user);
//                    }
//                    usersDataAdapter.notifyDataSetChanged();
//                }
//                else {
////                    makeSnackBarMessages("Query Failed... Check logs");
//                }
//            }
//        });
//    }

    private void initRecyclerview() {
        if(usersDataAdapter == null) {
            usersDataAdapter = new UsersDataAdapter(this,userList);
        }
        listOfUsers.setLayoutManager(new LinearLayoutManager(this));
        listOfUsers.setAdapter(usersDataAdapter);
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
