package com.example.m3_01_08_reiseplaner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.m3_01_08_reiseplaner.converter.LocalDateConverter;

import java.io.InputStream;
import java.time.LocalDate;

public class SearchTravelActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CALENDAR_DATE= 1001;

    private static final String INTENT_KEY_CALENDER = "CalendarKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_travel);
    }

    ActivityResultLauncher<Intent> setDepatureDateResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                 setDepartureDateText(result.getData());
            }
        }
    });


    private void setDepartureDateText(Intent data){
        if(data == null){
            return;
        }
        LocalDate departureDate = (LocalDate) data.getSerializableExtra(INTENT_KEY_CALENDER);
        EditText departureDateText = findViewById(R.id.departureDateText);
        String departureDateOutput = LocalDateConverter.localDateToString(departureDate);
        departureDateText.setText(departureDateOutput);
    }



    public void onDepartureDateButtonPress(View view){
        Intent departureDateCalendarIntent = new Intent(this, CalendarActivity.class);
        setDepatureDateResult.launch(departureDateCalendarIntent);
    }
}