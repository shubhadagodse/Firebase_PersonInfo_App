package com.firebase.firebasepersoninfoapp;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DateActivity extends AppCompatActivity {

    private Button bSetDate, bDateDone, bDateCancel;

    private TextView tv_b_date;
    DatePicker bDoneSetDate;
    int year, month , day;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        bDateDone = findViewById(R.id.a_date_bDone);
        bDateCancel = findViewById(R.id.a_date_bCancel);
        //setDate();
        //selectDate();
    }

    /*public void setDate() {
        bDateDone.setOnClickListener();
    }
    */

    public void selectDate() {
        bSetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar = Calendar.getInstance();
                year = calendar.get(calendar.YEAR);
                month = calendar.get(calendar.MONTH);
                day = calendar.get(calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(DateActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker1,int year,int month, int day) {
                                tv_b_date.setText(day+" /"+(month+1)+" /"+year);
                            }
                        },year, month, day);
                datePickerDialog.show();

            }
        });
    }


}
