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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.m3_01_08_reiseplaner.converter.LocalDateConverter;
import com.example.m3_01_08_reiseplaner.enums.ETravelPreference;
import com.example.m3_01_08_reiseplaner.exceptions.UnexpectedInputException;
import com.example.m3_01_08_reiseplaner.inputValidation.DateValidation;
import com.example.m3_01_08_reiseplaner.inputValidation.InputValidation;
import com.example.m3_01_08_reiseplaner.inputValidation.PopUpMessage;
import com.example.m3_01_08_reiseplaner.spinnerInputs.CountryList;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelInformation;

import java.io.InputStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchTravelActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_CALENDAR_DATE= 1001;

    private static final String INTENT_KEY_CALENDER = "CalendarKey";

    private static final String INTENT_KEY_TRAVELINFORMATION = "TravelInformation";

    private static ETravelPreference lastChosenPreference = ETravelPreference.CHEAP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_travel);
        fillTheSpinners();
        setRadioButtons();
    }


    /**
     * Fills both spinners with countryData.
     */
    private void fillTheSpinners(){
        List<String> listOfCountries = CountryList.getCountryLists(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfCountries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Spinner depatureCountryDropdown = findViewById(R.id.depatureSpinner);
        Spinner travelCountryDropdown = findViewById(R.id.travelDestinationSpinner);
        depatureCountryDropdown.setAdapter(adapter);
        travelCountryDropdown.setAdapter(adapter);
    }

    private void setRadioButtons(){
        RadioButton ecoButton = findViewById(R.id.ecoRadioButton);
        RadioButton fastButton = findViewById(R.id.fastRadioButton);
        RadioButton cheapButton = findViewById(R.id.cheapRadioButton);

        if(lastChosenPreference.equals(ETravelPreference.ECOLOGICALLY)){
            ecoButton.setChecked(true);
            return;
        }

        if(lastChosenPreference.equals(ETravelPreference.FAST)){
            fastButton.setChecked(true);
            return;
        }

        cheapButton.setChecked(true);
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

    public void onGoBackButtonPress(View view){
        finish();
    }

    /**
     * Starts to Search for the given Travel Destination
     * @param view
     */
    public void onTravelSearchButtonPress(View view){

        try {
            TravelInformation selectedTravelInformation = setTravelInformation();

            Intent newTravelIntent = new Intent(this, TravelRecommendationActivity.class);
            newTravelIntent.putExtra(INTENT_KEY_TRAVELINFORMATION, selectedTravelInformation);
            startActivity(newTravelIntent);
        }
        catch (UnexpectedInputException e){
            PopUpMessage.showWarnPopUpMessage(e.getMessage(), this);
        }


    }

    /**
     * Returns a Datastructure with all the given Information
     * @return
     * @throws DateTimeException
     */
    private TravelInformation setTravelInformation() throws UnexpectedInputException {
        EditText depatureCityText = findViewById(R.id.depatureCityText);
        Spinner depatureCountrySpinner = findViewById(R.id.depatureSpinner);
        EditText travelDestinationCityText = findViewById(R.id.travelDestinationText);
        Spinner travelDestinationCountrySpinner = findViewById(R.id.travelDestinationSpinner);
        EditText depatureDateText = findViewById(R.id.departureDateText);
        EditText returnDateText = findViewById(R.id.returnDateText);

        String depatureCity = depatureCityText.getText().toString();
        String depatureCountry = (String) depatureCountrySpinner.getSelectedItem();
        String travelDestinationCity = travelDestinationCityText.getText().toString();
        String travelDestinationCountry = (String) travelDestinationCountrySpinner.getSelectedItem();

        String depatureDateString = depatureDateText.getText().toString();
        String returnDateString = returnDateText.getText().toString();

        List<String> inputs = Arrays.asList(
                depatureCity, depatureCountry, travelDestinationCity, travelDestinationCountry, depatureDateString, returnDateString
        );

        InputValidation.checkIfInputsAreEmpty(inputs);

        LocalDate depatureDate = LocalDate.now();
        LocalDate returnDate = LocalDate.now();
        try {
            depatureDate = LocalDateConverter.convertStringToLocalDate(depatureDateString);
            returnDate = LocalDateConverter.convertStringToLocalDate(returnDateString);
        }
        catch (DateTimeException e){
            throw new UnexpectedInputException("Please input your Date in the dd.MM.yyyy Format");
        }

        DateValidation.checkIfDatesMakeSense(depatureDate, returnDate);

        ETravelPreference chosenPreference = selectTravelPreference();

        TravelInformation chosenTravelInformation = new TravelInformation(
                depatureCity,
                depatureCountry,
                travelDestinationCity,
                travelDestinationCountry,
                depatureDate,
                returnDate,
                chosenPreference
                );

        return  chosenTravelInformation;
    }

    /**
     * Returns the Travel Preference Enum that was chosen in RadioButtonGroup
     * @return
     */
    private ETravelPreference selectTravelPreference(){
        RadioButton ecoButton = findViewById(R.id.ecoRadioButton);
        RadioButton fastButton = findViewById(R.id.fastRadioButton);

        if(ecoButton.isChecked()){
            lastChosenPreference = ETravelPreference.ECOLOGICALLY;
            return ETravelPreference.ECOLOGICALLY;
        }
        if(fastButton.isChecked()){
            lastChosenPreference = ETravelPreference.FAST;
            return  ETravelPreference.FAST;
        }
        lastChosenPreference = ETravelPreference.CHEAP;
        return ETravelPreference.CHEAP;
    }
}