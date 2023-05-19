package com.example.m3_01_08_reiseplaner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.m3_01_08_reiseplaner.converter.LocalDateConverter;
import com.example.m3_01_08_reiseplaner.spinnerInputs.CountryList;

import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;

public class SearchTravelActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CALENDAR_DATE= 1001;

    private static final String INTENT_KEY_CALENDER = "CalendarKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_travel);
        fillTheSpinners();
    }


    private void fillTheSpinners(){
        List<String> listOfCountries = CountryList.getCountryLists(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfCountries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner depatureCountryDropdown = findViewById(R.id.depatureSpinner);
        Spinner travelCountryDropdown = findViewById(R.id.travelDestinationSpinner);
        depatureCountryDropdown.setAdapter(adapter);
        travelCountryDropdown.setAdapter(adapter);
    }

    /**
     * Fetches the result for the Departure Date after entering the calendar activity
     */
    ActivityResultLauncher<Intent> setDepatureDateResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                 setDepartureDateText(result.getData());
            }
        }
    });


    /**
     * Sets the Departure Text with the value from the Calendar Activity
     * @param data
     */
    private void setDepartureDateText(Intent data){
        if(data == null){
            return;
        }
        LocalDate departureDate = (LocalDate) data.getSerializableExtra(INTENT_KEY_CALENDER);
        EditText departureDateText = findViewById(R.id.departureDateText);
        String departureDateOutput = LocalDateConverter.localDateToString(departureDate);
        departureDateText.setText(departureDateOutput);
    }


    /**
     * When pressed on the Calendar Button, a Calendar Activity will open,
     * where a Date for Departure can be selected
     * @param view
     */
    public void onDepartureDateButtonPress(View view){
        Intent departureDateCalendarIntent = new Intent(this, CalendarActivity.class);
        setDepatureDateResult.launch(departureDateCalendarIntent);
    }



    /**
     * Fetches the result for the Return Date after entering the calendar activity
     */
    ActivityResultLauncher<Intent> setReturnDateResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                setReturnDateText(result.getData());
            }
        }
    });


    /**
     * Sets the Return Text with the value from the Calendar Activity
     * @param data
     */
    private void setReturnDateText(Intent data){
        if(data == null){
            return;
        }
        LocalDate departureDate = (LocalDate) data.getSerializableExtra(INTENT_KEY_CALENDER);
        EditText departureDateText = findViewById(R.id.returnDateText);
        String departureDateOutput = LocalDateConverter.localDateToString(departureDate);
        departureDateText.setText(departureDateOutput);
    }


    /**
     * When pressed on the Calendar Button, a Calendar Activity will open,
     * where a Date for Return can be selected
     * @param view
     */
    public void onReturnDateButtonPress(View view){
        Intent departureDateCalendarIntent = new Intent(this, CalendarActivity.class);
        setReturnDateResult.launch(departureDateCalendarIntent);
    }
}