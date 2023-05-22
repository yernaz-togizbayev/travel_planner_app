package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class EventOverviewActivity extends AppCompatActivity {
    static List<Event> events = new ArrayList<>();
    static int rangeForEvents = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventsoverview);
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }


    public void EventOverviewGoBackButtonPress(View view){
        Intent intent = new Intent(EventOverviewActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }

    public void AddEventButtonPress(View view){
        Intent intent = new Intent(EventOverviewActivity.this, AddEventActivity.class);
        startActivity(intent);
    }
    public void setEventPictureFirst(){
        ImageView icon = findViewById(R.id.iconEventOverview);
        TextView eventName = findViewById(R.id.NameEventOverview);
        TextView eventRegion = findViewById(R.id.regionEventOverview);
        TextView eventDate = findViewById(R.id.dateEventOverview);
        TextView eventTime = findViewById(R.id.timeEventOverview);

        ImageView dateImage = findViewById(R.id.dateImage);
        ImageButton button = findViewById(R.id.editButtonEventOverview);
        Button button2 = findViewById(R.id.EventOverviewButton);

        if(events.size()>(0+rangeForEvents)){
            Event event = events.get(0+rangeForEvents);
            icon.setImageResource(event.getIcon());
            eventName.setText(event.getName());
            eventRegion.setText(event.getRegion());
            eventDate.setText(event.getDate());
            eventTime.setText(event.getTime());
            dateImage.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
        }else{
            icon.setVisibility(View.GONE);
            eventName.setVisibility(View.GONE);
            eventRegion.setVisibility(View.GONE);
            eventDate.setVisibility(View.GONE);
            eventTime.setVisibility(View.GONE);
            dateImage.setVisibility(View.GONE);
            button.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.GONE);
        }
    }

    public void setEventPictureSecond(){
        ImageView icon = findViewById(R.id.iconEventOverview2);
        TextView eventName = findViewById(R.id.NameEventOverview2);
        TextView eventRegion = findViewById(R.id.regionEventOverview2);
        TextView eventDate = findViewById(R.id.dateEventOverview2);
        TextView eventTime = findViewById(R.id.timeEventOverview2);

        ImageView dateImage = findViewById(R.id.dateImage2);
        ImageButton button = findViewById(R.id.editButtonEventOverview2);
        Button button2 = findViewById(R.id.EventOverviewButton2);
        if(events.size()>(1+rangeForEvents)){
            Event event = events.get(1+rangeForEvents);
            icon.setImageResource(event.getIcon());
            eventName.setText(event.getName());
            eventRegion.setText(event.getRegion());
            eventDate.setText(event.getDate());
            eventTime.setText(event.getTime());
            icon.setVisibility(View.VISIBLE);
            eventName.setVisibility(View.VISIBLE);
            eventRegion.setVisibility(View.VISIBLE);
            eventDate.setVisibility(View.VISIBLE);
            eventTime.setVisibility(View.VISIBLE);
            dateImage.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
        }else{
            icon.setVisibility(View.GONE);
            eventName.setVisibility(View.GONE);
            eventRegion.setVisibility(View.GONE);
            eventDate.setVisibility(View.GONE);
            eventTime.setVisibility(View.GONE);
            dateImage.setVisibility(View.GONE);
            button.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.GONE);
        }
    }

    public void setEventPictureThird(){
        ImageView icon = findViewById(R.id.iconEventOverview3);
        TextView eventName = findViewById(R.id.NameEventOverview3);
        TextView eventRegion = findViewById(R.id.regionEventOverview3);
        TextView eventDate = findViewById(R.id.dateEventOverview3);
        TextView eventTime = findViewById(R.id.timeEventOverview3);

        ImageView dateImage = findViewById(R.id.dateImage3);
        ImageButton button = findViewById(R.id.editButtonEventOverview3);
        Button button2 = findViewById(R.id.EventOverviewButton3);

        if(events.size()>(2+rangeForEvents)){
            Event event = events.get(2+rangeForEvents);
            icon.setImageResource(event.getIcon());
            eventName.setText(event.getName());
            eventRegion.setText(event.getRegion());
            eventDate.setText(event.getDate());
            eventTime.setText(event.getTime());
            Log.d("Event Overview", "third Picture changed");
            icon.setVisibility(View.VISIBLE);
            eventName.setVisibility(View.VISIBLE);
            eventRegion.setVisibility(View.VISIBLE);
            eventDate.setVisibility(View.VISIBLE);
            eventTime.setVisibility(View.VISIBLE);
            dateImage.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            button2.setVisibility(View.VISIBLE);
        }else{
            icon.setVisibility(View.GONE);
            eventName.setVisibility(View.GONE);
            eventRegion.setVisibility(View.GONE);
            eventDate.setVisibility(View.GONE);
            eventTime.setVisibility(View.GONE);
            dateImage.setVisibility(View.GONE);
            button.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.GONE);
        }
    }

    public void GoLeftButtonPress(View view){
        Log.d("EventOverview", "LeftButtonPress");
        if(rangeForEvents>=3) {
            rangeForEvents -= 3;
        }
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
        Log.d("EventOverview range", rangeForEvents+"");
        Log.d("EventOverview events size", events.size()+"");
    }

    public void GoRightButtonPress(View view){
        Log.d("EventOverview", "RightButtonPress");
        if(rangeForEvents+3<events.size()) {
            rangeForEvents+=3;
        }
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
        Log.d("EventOverview range", rangeForEvents+"");
        Log.d("EventOverview events size", events.size()+"");
    }
    public static void addOneEvent(Event e){
        events.add(e);
    }

    public void deleteButton3Pressed(View view){
        events.remove(2+rangeForEvents);
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }

    public void deleteButton2Pressed(View view){
        events.remove(1+rangeForEvents);
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }

    public void deleteButton1Pressed(View view){
        events.remove(rangeForEvents);
        if(rangeForEvents>=3) {
            rangeForEvents -= 3;
        }
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }
}
