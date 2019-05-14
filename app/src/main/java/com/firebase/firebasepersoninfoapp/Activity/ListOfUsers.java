package com.firebase.firebasepersoninfoapp.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.firebase.firebasepersoninfoapp.R;

//import com.firebase.ui.database.FirebaseListAdapter;
//import com.firebase.ui.database.FirebaseListOptions;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.Query;

public class ListOfUsers extends AppCompatActivity {

    private static final String TAG = "";
    ListView lv;
//    FirebaseListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        lv = findViewById(R.id.list_of_users_from_firebase);

//        Query query = FirebaseDatabase.getInstance().getReference().child("Users");
//        FirebaseListOptions<Users> options = new FirebaseListOptions.Builder<Users>()
//                .setLayout(R.layout.users)
//                .setLifecycleOwner(ListOfUsers.this)
//                .setQuery(query,Users.class)
//                .build();

//        listAdapter = new FirebaseListAdapter(options) {
//            @Override
//            protected void populateView(View v, Object model, int position) {
//                TextView  firstname = v.findViewById(R.id.first_name);
//                TextView lastname = v.findViewById(R.id.last_name);
//                TextView age = v.findViewById(R.id.age);
//                TextView email = v.findViewById(R.id.email);
//                TextView phone = v.findViewById(R.id.phone);
//                TextView birthdate = v.findViewById(R.id.birth_date);
//                TextView country = v.findViewById(R.id.country);
//                TextView state = v.findViewById(R.id.state);
//
//                Users usr = (Users) model;
//                firstname.setText(usr.getFirstname().toString());
//                lastname.setText(usr.getLastname().toString());
//                age.setText(usr.getAge().toString());
//                email.setText(usr.getEmail().toString());
//                phone.setText(usr.getPhone().toString());
//                birthdate.setText(usr.getBirthdate().toString());
//                country.setText(usr.getCountry().toString());
//                state.setText(usr.getState().toString());
//
            }
        };
//        lv.setAdapter(listAdapter);
//
//        initialize();
//        addData();
//        readData();
//    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        listAdapter.startListening();
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        listAdapter.stopListening();
//    }
//
//    public void initialize() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//    }
//
//    private void addData() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//         Create a new user with a first and last name
//        Map<String, Object> user = new HashMap<>();
//        user.put("firstname", "Ada");
//        user.put("lastname", "Lovelace");
//        user.put("age", 25);
//        user.put("email","ada@gmail.com");
//        user.put("phone","8888202071");
//        user.put("birthDate","05/09/1994");
//        user.put("country","US");
//        user.put("state","New York");

// Add a new document with a generated ID
//        db.collection("users")
//                .add(user)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
//                    @Override
//                    public void onSuccess(DocumentReference documentReference) {
//                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//
//    }

//    private void readData() {
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        if (task.isSuccessful()) {
//                            for (QueryDocumentSnapshot document : task.getResult()) {
//                                Log.d(TAG, document.getId() + " => " + document.getData());
//                            }
//                        } else {
//                            Log.w(TAG, "Error getting documents.", task.getException());
//                        }
//                    }
//                });
//    }
//
//}
