package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import com.firebase.firebasepersoninfoapp.R;

public class DateActivity extends AppCompatActivity {

    private String TAG = "DateActivity";
    private Button bDone, bCancel;
    private TextView tvBdate;
    CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        bDone = findViewById(R.id.a_date_bDone);
        bCancel = findViewById(R.id.a_date_bCancel);
        calendar = findViewById(R.id.a_date_calendar_view);
        tvBdate =findViewById(R.id.a_main_tv_birthdate);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView view, final int year, final int month, final int dayOfMonth) {
                        bDone.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String birthdate = dayOfMonth+"/ "+(month+1)+"/ "+year;
                                Log.i(TAG,"onSelectedDayChange "+birthdate);

                                Intent resultIntent =new Intent();
                                resultIntent.putExtra("RESULT",birthdate);
                                setResult(RESULT_OK,resultIntent);
                                finish();

                            }
                        });
                    }
                });

    }

}


