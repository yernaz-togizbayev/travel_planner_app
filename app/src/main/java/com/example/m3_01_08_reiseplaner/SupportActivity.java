package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SupportActivity extends AppCompatActivity {

    /**
     * Sets up the Screen, also sets the version text at the bottom of the screen.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        TextView versionText = findViewById(R.id.versionText);
        versionText.setText(MainMenuActivity.version);
    }

    /**
     * Handles the button press for going back to the Info-Screen.
     */
    public void supportGoBackPress(View view){
        Intent infoScreen = new Intent(this, InfoActivity.class);
        startActivity(infoScreen);
    }
}
