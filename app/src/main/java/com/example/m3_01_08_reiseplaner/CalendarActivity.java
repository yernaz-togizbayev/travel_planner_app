package com.example.m3_01_08_reiseplaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

import java.time.LocalDate;

public class CalendarActivity extends AppCompatActivity {

    private  static  final String TAG = "CalendarActivity";
    private static final String INTENT_KEY = "CalendarKey";
    private LocalDate chosenDate = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        chooseDate();
    }

    /**
     *Adds an Listener that selects the current ChosenDate
     */
    private void chooseDate(){
        CalendarView calendar = findViewById(R.id.calendarView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Log.d(TAG,"Year: "+ year + " ,Month: "+  month +" ,Day: " + dayOfMonth);
                chosenDate = LocalDate.of(year,month +1,dayOfMonth);
            }
        });
    }

    /**
     * By a button press the result with the Key "CalendarKey" will be set with the chosenDate.
     * If no Date is chosen, the User will be notified via a Toast
     * @param view
     */
    public void enterDate(View view){
        if(chosenDate == null){
            Toast.makeText(CalendarActivity.this, "Please enter a Date!", Toast.LENGTH_LONG).show();
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra(INTENT_KEY, chosenDate);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

}