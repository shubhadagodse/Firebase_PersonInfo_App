package com.firebase.firebasepersoninfoapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

public class DateActivity extends AppCompatActivity {

    private String TAG = "DateActivity";
    private Button bDone, bCancel;
    private TextView tv_b_date;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        bDone = findViewById(R.id.a_date_bDone);
        bCancel = findViewById(R.id.a_date_bCancel);
        calendar = findViewById(R.id.a_date_calendar_view);
        tv_b_date =findViewById(R.id.a_main_tv_birthdate);

        Intent intent = getIntent();
        final String birthdate = intent.getStringExtra("birthdate");

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                        String birthdate = dayOfMonth+"/ "+(month+1)+"/ "+year;
                        Log.i(TAG,"onSelectedDayChange "+birthdate);

                        Intent resultIntent =new Intent();
                        resultIntent.putExtra("RESULT",birthdate);
                        setResult(RESULT_OK,resultIntent);
                        finish();

                    }
                });


        bDone.setOnClickListener(new View.OnClickListener() {
            int dayOfMonth, month , year;
            public void onClick(View view) {
                String birthdate = dayOfMonth+"/ "+(month+1)+"/ "+year;
            Log.i(TAG, "date ::"+ birthdate);

            Intent intent = new Intent();
            intent.putExtra("RESULT","birthdate");
            setResult(RESULT_OK,intent);
            finish();

            }
        });


    }

}


