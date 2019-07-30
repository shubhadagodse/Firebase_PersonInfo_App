package com.firebase.firebasepersoninfoapp.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;
import com.firebase.firebasepersoninfoapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import java.util.ArrayList;
import java.util.List;

public class ListOfUsers extends AppCompatActivity implements MyRecyclerViewAdapter.MyClickListener {

    FirebaseFirestore database;
    MyRecyclerViewAdapter usersDataAdapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<Users> uArrayList;
    private List<Users> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_users);

        mRecyclerView = findViewById(R.id.a_list_of_users_from_firebase);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        usersDataAdapter = new MyRecyclerViewAdapter((ArrayList<Users>) userList);
        mRecyclerView.setAdapter(mAdapter);
        userList = new ArrayList<>();
        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.keepSynced(true);
        database = FirebaseFirestore.getInstance();
        uArrayList = new ArrayList<>();
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
                            Users user = new Users(querySnapshot.getString("firstname"),
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
                    }
                });
    }

    @Override
    public void onItemClick(int position) {
        uArrayList.get(position);
        Intent intent = new Intent(this,UserDetailsActivity.class);
        intent.putExtra("user", (Parcelable) uArrayList.get(position));
        startActivity(intent);
    }
}
