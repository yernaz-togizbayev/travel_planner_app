package com.example.m3_01_08_reiseplaner;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_01_08_reiseplaner.recyclerView.PlannedTripsRecycleViewAdapter;

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

public class UpdatedEventOverviewActivity extends AppCompatActivity {
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
        UpdatedEventOverviewActivity.eventMap = eventMap;
    }

    public static String getDestination() {
        return destination;
    }

    public static void setDestination(String destination) {
        UpdatedEventOverviewActivity.destination = destination;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        UpdatedEventOverviewActivity.id = id;
    }

    public static List<Event> getEvents() {
        return events;
    }

    public static void setEvents(List<Event> events) {
        UpdatedEventOverviewActivity.events = events;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_updated_event_overview);
        if(getEvents() == null)
            setEvents(new ArrayList<>());

        setUpEventRecyclerView();

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
        Intent intent = new Intent(UpdatedEventOverviewActivity.this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void AddEventButtonPress(View view){
        Intent intent = new Intent(UpdatedEventOverviewActivity.this, AddEventActivity.class);
        startActivity(intent);
    }

    public static void addOneEvent (Event event){
        events.add(event);
    }

    private void setUpEventRecyclerView(){
        RecyclerView eventView = findViewById(R.id.eventsOverviewRecyclerView);
        EventViewHolder adapter = new EventViewHolder(this, events);
        eventView.setAdapter(adapter);
        eventView.setLayoutManager(new LinearLayoutManager(this));
    }
}
