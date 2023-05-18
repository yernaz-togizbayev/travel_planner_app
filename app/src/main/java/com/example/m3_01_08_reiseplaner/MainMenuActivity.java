package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
      //  setUpActivity();
    }

    public void newTravelButtonPress(View view){
        Intent travelIntent = new Intent(this, SearchTravelActivity.class);
        startActivity(travelIntent);
    }

    /**
     * Initializes the Activity with everything needed when opening this activity
     */
    private void setUpActivity(){
        setUpTravelButton();
    }


    /**
     * Sets up the background Color of the New Travel Button
     */
    private void setUpTravelButton(){
        Button travelButton = findViewById(R.id.newTravelButton);
        travelButton.setBackgroundColor(Color.rgb(204,18,55));
    }
}