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

        // Initial user 1
        User MaxMustermann = new User (
                "Max",
                "Mustermann",
                "01.01.2000",
                "Wahringer Strasse",
                "29",
                "Vienna",
                "1090",
                "Austria",
                "max.mustermann@gmail.com",
                "Max123"
        );

        // Initial user 2
        User ErikaMustermann = new User (
                "Erika",
                "Mustermann",
                "01.01.2000",
                "Wahringer Strasse",
                "29",
                "Vienna",
                "1090",
                "Austria",
                "erika.mustermann@gmail.com",
                "Erika123"
        );

        // Initial user 3
        User JohnDoe = new User (
                "John",
                "Doe",
                "01.01.2000",
                "Miami Beach",
                "5",
                "Florida",
                "33109",
                "United States",
                "john.doe@gmail.com",
                "John123"
        );

        // Add all 3 users to a list of users
        SignUpActivity.getUserList().add(MaxMustermann);
        SignUpActivity.getUserList().add(ErikaMustermann);
        SignUpActivity.getUserList().add(JohnDoe);

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
    }
}