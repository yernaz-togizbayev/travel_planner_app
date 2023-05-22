package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ChooseIconActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooseicon);

        ImageView icon1 = findViewById(R.id.Icon1);
        icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventActivity.setCurrentIcon(R.drawable.museumicon);
                Intent intent = new Intent(ChooseIconActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        ImageView icon2 = findViewById(R.id.Icon2);
        icon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventActivity.setCurrentIcon(R.drawable.houseicon_small);
                Intent intent = new Intent(ChooseIconActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        ImageView icon3 = findViewById(R.id.Icon3);
        icon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventActivity.setCurrentIcon(R.drawable.landpointicon);
                Intent intent = new Intent(ChooseIconActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });

        ImageView icon4 = findViewById(R.id.Icon4);
        icon4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddEventActivity.setCurrentIcon(R.drawable.eatingicon);
                Intent intent = new Intent(ChooseIconActivity.this, AddEventActivity.class);
                startActivity(intent);
            }
        });


    }

    public void ChooseIconGoBackPress(View view) {
        Intent intent = new Intent(ChooseIconActivity.this, AddEventActivity.class);
        startActivity(intent);
    }



}
