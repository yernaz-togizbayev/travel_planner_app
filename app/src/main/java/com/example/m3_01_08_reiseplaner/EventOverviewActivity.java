package com.example.m3_01_08_reiseplaner;


import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class EventOverviewActivity extends AppCompatActivity {
    private static List<Event> events = new ArrayList<>();

    private static Integer id = 0;
    private static Map<Integer, List<Event>> eventMap = new HashMap<>();
    static int rangeForEvents = 0;

    private static String destination = "Vienna";

    //private static final String INTENT_KEY_CALENDER = "CalendarKey";

    MapView mapView = null;

    public static Map<Integer, List<Event>> getEventMap() {
        return eventMap;
    }

    public static void setEventMap(Map<Integer, List<Event>> eventMap) {
        EventOverviewActivity.eventMap = eventMap;
    }

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        EventOverviewActivity.destination = destination;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        EventOverviewActivity.id = id;
    }

    public static List<Event> getEvents() {
        return events;
    }

    public static void setEvents(List<Event> events) {
        EventOverviewActivity.events = events;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_eventsoverview);
        if(getEvents() ==null)
            setEvents(new ArrayList<>());

        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();

        //Set map to point in Vienna

        mapView = (MapView) findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        IMapController mapController = mapView.getController();
        mapController.setZoom(10);
        MapView mMapView;

        if(getDestination() != null && getDestination().length()>3) {
            try {
                mapController.setCenter(getGeoPointFromCityName(getDestination()));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        else {
            try {
                mapController.setCenter(getGeoPointFromCityName("Mexico"));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    private GeoPoint getGeoPointFromCityName(String city) throws InterruptedException {
        Geocoder geo = new Geocoder(getBaseContext(), Locale.getDefault());
        final List<Address>[] address = new List[]{new ArrayList<>()};

        /*try {
            address[0] = geo.getFromLocationName(city, 1);
        } catch (IOException e) {
            Log.d("Exception ignore", e.getMessage());
            return new GeoPoint(48.210033, 16.363449);
        }*/

        new Thread(() -> {
            try {
                address[0] = geo.getFromLocationName(city, 1);
            } catch (IOException e) {
                Log.d("Exception ignore", e.getMessage());
            }
        }).join();

        
        if(address[0] ==null || address[0].isEmpty())
            return new GeoPoint(48.210033, 16.363449);

        Address locationCity = address[0].get(0);
        locationCity.getLatitude();
        locationCity.getLongitude();
        GeoPoint gp = new GeoPoint(locationCity.getLatitude(), locationCity.getLongitude());
        return gp;
    }

    public void EventOverviewGoBackButtonPress(View view){
        getEventMap().put(getId(), getEvents());
        Intent intent = new Intent(EventOverviewActivity.this, MainMenuActivity.class);
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

        if(getEvents().size()>(0+rangeForEvents)){
            Event event = getEvents().get(0+rangeForEvents);
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
        if(getEvents().size()>(1+rangeForEvents)){
            Event event = getEvents().get(1+rangeForEvents);
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

        if(getEvents().size()>(2+rangeForEvents)){
            Event event = getEvents().get(2+rangeForEvents);
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
        Log.d("EventOverview events size", getEvents().size()+"");
    }

    public void GoRightButtonPress(View view){
        Log.d("EventOverview", "RightButtonPress");
        if(rangeForEvents+3< getEvents().size()) {
            rangeForEvents+=3;
        }
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
        Log.d("EventOverview range", rangeForEvents+"");
        Log.d("EventOverview events size", getEvents().size()+"");
    }
    public static void addOneEvent(Event e){
        getEvents().add(e);
    }

    public void deleteButton3Pressed(View view){
        getEvents().remove(2+rangeForEvents);
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }

    public void deleteButton2Pressed(View view){
        getEvents().remove(1+rangeForEvents);
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }

    public void deleteButton1Pressed(View view){
        getEvents().remove(rangeForEvents);
        if(rangeForEvents>=3) {
            rangeForEvents -= 3;
        }
        setEventPictureFirst();
        setEventPictureSecond();
        setEventPictureThird();
    }
}
