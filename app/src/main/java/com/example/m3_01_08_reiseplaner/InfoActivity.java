package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView versionText = findViewById(R.id.versionText);
        versionText.setText(MainMenuActivity.version);
    }

    public void infoGoBackPress(View view){
        Intent mainScreen = new Intent(this, MainMenuActivity.class);
        startActivity(mainScreen);
    }

    public void newFAQButtonPress(View view){
        Intent FAQScreen = new Intent(this, FAQActivity.class);
        startActivity(FAQScreen);
    }

    public void newSupportButtonPress(View view){
        Intent supportScreen = new Intent(this, SupportActivity.class);
        startActivity(supportScreen);
    }

    public void newAboutButtonPress(View view){
        Intent aboutScreen = new Intent(this, AboutActivity.class);
        startActivity(aboutScreen);
    }
}
