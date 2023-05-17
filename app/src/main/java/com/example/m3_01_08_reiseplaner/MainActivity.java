package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //If you see this, your pull was successful
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Navigates to "SignInActivity" by clicking "Sign In" TextView
        TextView SignInText = findViewById(R.id.SignInText);
        SignInText.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignInActivity.class);
            startActivity(intent);
        });

        // Navigates to "SignUpActivity" by clicking "Sign Up" TextView
        TextView SignUpText = findViewById(R.id.SignUpText);
        SignUpText.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }
}