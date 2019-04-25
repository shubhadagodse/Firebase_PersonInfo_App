package com.firebase.firebasepersoninfoapp;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private FirebaseFirestore db;
    private String TAG = " ";
    private Button bSetDate;
    private TextView tv_b_date;
    DatePicker bDoneSetDate;
    int year, month , day;
    Calendar calendar;

    private static final String SHARED_PREFS = "shared_preferences";
    private static final String SHARED_PREFS_BTN_SETDATE = "bSetDate";
    private static final String SHARED_PREFS_BTN_DONE_SETDATE = "bdoneSetDate";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setTimestampsInSnapshotsEnabled(true)
                .build();
        firestore.setFirestoreSettings(settings);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        tv_b_date  = findViewById(R.id.a_main_tv_birthdate);
        bSetDate = findViewById(R.id.a_main_bSetDate);
        bSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDateActivity();
            }
        });

        initData();
        addData();
        readData();

    }

    public void saveDate() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        //editor.putInt(SHARED_PREFS_BTN_SETDATE,tv_b_date);
        editor.apply();
        editor.commit();
    }


    public void openDateActivity() {

        Intent intent =new Intent(this, DateActivity.class);
        startActivity(intent);

       /* bSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(calendar.YEAR);
                month = calendar.get(calendar.MONTH);
                day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker1,int year,int month, int day) {
                                tv_b_date.setText(day+" /"+(month+1)+" /"+year);
                            }
                        },year, month, day);
                datePickerDialog.show();

            }
        });
        */
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        tv_b_date.setText(dayOfMonth+" /"+(month+1)+" /"+year);
    }


    private void initData() {

    }

    private void addData()
    {
        // Create a new user with a first and last name
        Map<String, Object> user = new HashMap<>();
        user.put("first", "Ada");
        user.put("last", "Lovelace");
        user.put("born", 1815);

// Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }

    private void readData(){
        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }


  /*  public void setBirthDate(View view) {
        DatePicker datePicker =new DatePicker();
        datePicker.show(getSupportFragmentManager(),"date_picker");

    }
  */
}
