package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView versionText = findViewById(R.id.versionText);
        versionText.setText(MainMenuActivity.version);
    }

    public void aboutGoBackPress(View view){
        Intent infoScreen = new Intent(this, InfoActivity.class);
        startActivity(infoScreen);
    }
}
