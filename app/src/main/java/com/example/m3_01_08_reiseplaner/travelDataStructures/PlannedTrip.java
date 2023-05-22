package com.example.m3_01_08_reiseplaner.travelDataStructures;

import com.example.m3_01_08_reiseplaner.Event;
import com.example.m3_01_08_reiseplaner.api.UnsplashAPI;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class PlannedTrip implements Comparable<PlannedTrip> {
    String tripDestinationCountry;
    String tripDestinationCity;

    String pictureUrl;
    LocalDate tripDate;

    LocalDate tripEndDate;

    List<Event> events = new ArrayList<Event>();

    TravelInformation information;


    public PlannedTrip(TravelInformation information){
        tripDestinationCountry = information.getDestinationCountry();
        tripDestinationCity = information.getDestinationCity();
        tripDate = information.getStartDate();
        tripEndDate = information.getBackTravelDate();
        this.information = information;


        pictureUrl = UnsplashAPI.getCountryPictureURL(tripDestinationCountry, true);
    }

    /**
     * Adding a Event
     * @param event
     */
    public void addEvent(Event event){
        events.add(event);
    }

    /**
     * Gets a String that represents how long it is till the trip or if it is currently active or if it is over
     * @return
     */
    public String getDaysTillTravelStart(){
        LocalDate currentDate = LocalDate.now();

        long daysBetween = currentDate.until(tripDate, ChronoUnit.DAYS);

        if(daysBetween > 0){
            String daysTillTrip = "Trip in " + daysBetween + " days";
            return daysTillTrip;
        }

        if(daysBetween == 0){
            String todayTrip = "The Trip starts today";
            return todayTrip;
        }

        long daysBetweenEnd = currentDate.until(tripEndDate, ChronoUnit.DAYS);

        if(daysBetweenEnd < 0){
            return "Trip is over";
        }

        return "Your current Trip";
    }

    public String getTripDestinationCountry() {
        return tripDestinationCountry;
    }

    public String getTripDestinationCity() {
        return tripDestinationCity;
    }

    public LocalDate getTripDate() {
        return tripDate;
    }

    public List<Event> getEvents() {
        return events;
    }

    public TravelInformation getInformation() {
        return information;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    @Override
    public int compareTo(PlannedTrip otherTrip) {
        LocalDate currentDate = LocalDate.now();

        long daysBetweenThis = currentDate.until(this.tripDate, ChronoUnit.DAYS);
        long daysBetweenOther = currentDate.until(otherTrip.tripDate, ChronoUnit.DAYS);

        return Long.compare(daysBetweenThis, daysBetweenOther);
    }
}
