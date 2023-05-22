package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {
    static List<Event> events = new ArrayList<>();
    public static int currentIcon = R.drawable.museumicon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);

        ImageView im = findViewById(R.id.preferedIcon);
        im.setImageResource(currentIcon);
    }

    public void addEventButtonPress(View view){
        Event event = new Event();
        TextView eventName = findViewById(R.id.EventNameEditText);

        TextView eventDate = findViewById(R.id.DateEditText);
        TextView eventRegion = findViewById(R.id.RegionEditText);
        TextView eventStreet = findViewById(R.id.StreetEditText);
        TextView eventTime = findViewById(R.id.TimeEditText);
        TextView eventPostalCode = findViewById(R.id.PostalCodeEditText);
        TextView eventHouseNumber = findViewById(R.id.HouseNumberEditText);

        if(eventName != null && eventName.getText() != null && !eventName.getText().toString().isEmpty())
            event.setName(eventName.getText().toString());

        if(eventDate != null && eventDate.getText() != null && !eventDate.getText().toString().isEmpty())
            event.setDate(eventDate.getText().toString());

        if(eventRegion != null && eventRegion.getText() != null && !eventRegion.getText().toString().isEmpty())
            event.setRegion(eventRegion.getText().toString());

        if(eventStreet != null && eventStreet.getText() != null && !eventStreet.getText().toString().isEmpty())
            event.setStreet(eventStreet.getText().toString());

        if(eventTime.getText() != null && !eventTime.getText().toString().isEmpty())
            event.setTime(eventTime.getText().toString());

        if(eventPostalCode.getText() != null && !eventPostalCode.getText().toString().isEmpty())
            event.setPostalCode(eventPostalCode.getText().toString());

        if(eventHouseNumber.getText() != null && !eventHouseNumber.getText().toString().isEmpty())
            event.setHouseNumber(eventHouseNumber.getText().toString());

        event.setIcon(currentIcon);
        events.add(event);
        Log.d("Event Added", event.getName());
        EventOverviewActivity.addOneEvent(event);

        Intent intent = new Intent(AddEventActivity.this, EventOverviewActivity.class);
        startActivity(intent);
    }

    public void chooseIconButtonPress(View view){
        Intent intent = new Intent(AddEventActivity.this, ChooseIconActivity.class);
        startActivity(intent);

        ImageView im = findViewById(R.id.preferedIcon);
        im.setImageResource(currentIcon);
    }

    public static void setCurrentIcon(int id){
        currentIcon = id;
    }

    public List<Event> getEvents(){
        return events;
    }

}
