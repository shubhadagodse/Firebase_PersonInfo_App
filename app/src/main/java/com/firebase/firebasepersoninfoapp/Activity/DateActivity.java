package com.firebase.firebasepersoninfoapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import com.firebase.firebasepersoninfoapp.R;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateActivity extends AppCompatActivity {
    private Button bDone, bCancel;
    private CalendarView calendar;
    private static final String SHARED_PREFS_DATEOFBIRTH ="dateOfBirth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date);

        bDone = findViewById(R.id.a_date_btn_done);
        bCancel = findViewById(R.id.a_date_btn_cancel);
        calendar = findViewById(R.id.a_date_calendar_view);

        bCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancelIntent = new Intent();
                cancelIntent.putExtra(SHARED_PREFS_DATEOFBIRTH,SHARED_PREFS_DATEOFBIRTH);
                setResult(RESULT_CANCELED,cancelIntent);
                finish();
            }
        });

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, final int year, final int month, final int dayOfMonth) {
                bDone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        if( calendar.isAttachedToWindow()) {
                            String birthdate = dayOfMonth+"/ "+(month+1)+"/ "+year;
                            Intent resultIntent =new Intent();
                            resultIntent.putExtra(SHARED_PREFS_DATEOFBIRTH,birthdate);
                            setResult(RESULT_OK,resultIntent);
                            finish();
                        }
                        else {
                            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                            String selectedDate = sdf.format(new Date(calendar.getDate()));
                            Intent defaultDateIntent =new Intent();
                            defaultDateIntent.putExtra(SHARED_PREFS_DATEOFBIRTH,String.valueOf(selectedDate));
                            setResult(RESULT_OK,defaultDateIntent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}


