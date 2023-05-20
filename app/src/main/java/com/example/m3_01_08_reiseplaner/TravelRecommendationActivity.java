package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.m3_01_08_reiseplaner.enums.ETravelPreference;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelInformation;

public class TravelRecommendationActivity extends AppCompatActivity {

    private TravelInformation currentTravelInformation;
    private static final String INTENT_KEY_TRAVELINFORMATION = "TravelInformation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_recommendation);
        loadActivity();
    }

    private void loadActivity(){
        Intent recieverIntent = getIntent();
        currentTravelInformation = (TravelInformation) recieverIntent.getSerializableExtra(INTENT_KEY_TRAVELINFORMATION);

        TextView preferenceText = findViewById(R.id.preferenceTextView);
        TextView depatureDateText = findViewById(R.id.deptureDateTextView);
        TextView returnDateText = findViewById(R.id.returnDateTextView);

        String depatureDate = currentTravelInformation.getStartDateAsString();
        String returnDate = currentTravelInformation.getBackTravelDateAsString();
        ETravelPreference preference = currentTravelInformation.getPreference();

        depatureDateText.setText(depatureDate);
        returnDateText.setText(returnDate);

        preferenceText.setText(preference.getName());

    }
}