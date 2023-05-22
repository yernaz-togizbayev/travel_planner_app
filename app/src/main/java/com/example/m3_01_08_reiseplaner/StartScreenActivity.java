package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class StartScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If you see this, your pull was successful
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startscreen);

        // Navigates to "SignInActivity" by clicking "Sign In" TextView
        TextView SignInText = findViewById(R.id.SignInText);
        SignInText.setOnClickListener(view -> {
            Intent intent = new Intent(StartScreenActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        // Navigates to "SignUpActivity" by clicking "Sign Up" TextView
        TextView SignUpText = findViewById(R.id.SignUpText);
        SignUpText.setOnClickListener(view -> {
            Intent intent = new Intent(StartScreenActivity.this, SignUpActivity.class);
            startActivity(intent);
        });

        TextView AddTripText = findViewById(R.id.AddTripStart);
        AddTripText.setOnClickListener(view -> {
            Intent intent = new Intent(StartScreenActivity.this, EventOverviewActivity.class);
            startActivity(intent);
        });
    }
}