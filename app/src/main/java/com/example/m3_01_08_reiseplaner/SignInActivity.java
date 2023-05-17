package com.example.m3_01_08_reiseplaner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    public void SignInGoBackPress(View view) {
        Intent intent = new Intent(SignInActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }

    public void SignInButtonPress(View view) {
        Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }
}