package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.m3_01_08_reiseplaner.recyclerView.PlannedTripsRecycleViewAdapter;
import com.example.m3_01_08_reiseplaner.staticDataStorer.StoredTravels;
import com.example.m3_01_08_reiseplaner.travelDataStructures.PlannedTrip;

import java.util.List;

public class MainMenuActivity extends AppCompatActivity {

    public static final String version = "Reiseplaner alpha 1.0.0";
    private List<PlannedTrip> savedTrips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        setUpActivity();
    }

    public void newTravelButtonPress(View view){
        Intent travelIntent = new Intent(this, SearchTravelActivity.class);
        startActivity(travelIntent);
    }

    public void newInfoButtonPress(View view){
        Intent infoIntent = new Intent(this, InfoActivity.class);
        startActivity(infoIntent);
    }

    public void newSettingsButtonPress(View view){
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }

    /**
     * Initializes the Activity with everything needed when opening this activity
     */
    private void setUpActivity(){
        savedTrips = StoredTravels.getTrips();
        setUpRecyclerView();
    }

    private void setUpRecyclerView(){
        RecyclerView travelsView = findViewById(R.id.travelsRecyclerView);
        PlannedTripsRecycleViewAdapter adapter = new PlannedTripsRecycleViewAdapter(this, savedTrips);
        travelsView.setAdapter(adapter);
        travelsView.setLayoutManager(new LinearLayoutManager(this));
    }
}