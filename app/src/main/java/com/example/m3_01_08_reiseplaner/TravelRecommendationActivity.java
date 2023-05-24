package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.m3_01_08_reiseplaner.enums.ETravelPreference;
import com.example.m3_01_08_reiseplaner.mockFiller.TravelRecommandationFiller;
import com.example.m3_01_08_reiseplaner.recyclerView.TravelRecommendationRecycleViewAdapter;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelInformation;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelRecommendation;

import java.util.List;

public class TravelRecommendationActivity extends AppCompatActivity {

    private static final String TAG = "TravelRecommendationActivity";

    private TravelInformation currentTravelInformation;

    private List<TravelRecommendation> recommendations;
    private static final String INTENT_KEY_TRAVELINFORMATION = "TravelInformation";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_travel_recommendation);
        loadActivity();
        setUpRecyclerView();
    }

    public void onGoBackButtonPress(View view){
        finish();
    }

    /**
     * Sets the texts of this activity given by travelinformation and generates a mock list of Travel Recommendations
     */
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

        recommendations = TravelRecommandationFiller.initialiseRecommendations(currentTravelInformation);

        Log.d(TAG, recommendations.toString());
    }

    private void setUpRecyclerView(){
        RecyclerView recommendationView = findViewById(R.id.recommendationsRecyclerView);
        TravelRecommendationRecycleViewAdapter adapter = new TravelRecommendationRecycleViewAdapter(this, recommendations);
        recommendationView.setAdapter(adapter);
        recommendationView.setLayoutManager(new LinearLayoutManager(this));
    }

}