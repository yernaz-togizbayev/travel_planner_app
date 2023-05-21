package com.example.m3_01_08_reiseplaner.travelDataStructures;

import com.example.m3_01_08_reiseplaner.enums.ETransportation;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TravelRecommendation {
    private ETransportation transportation;
    private String location;
    private LocalTime depatureTime;
    private LocalTime returnTime;
    private String price;
    private int drawableID;

    private TravelInformation information;

    private String travelTime;


    public TravelRecommendation(ETransportation transportation, String location, LocalTime depatureTime, LocalTime returnTime, String price, int drawableID, TravelInformation information, String travelTime) {
        this.transportation = transportation;
        this.location = location;
        this.depatureTime = depatureTime;
        this.returnTime = returnTime;
        this.price = price;
        this.drawableID = drawableID;
        this.information = information;
        this.travelTime = travelTime;
    }

    public  TravelRecommendation(Transport transport, TravelInformation information, LocalTime depatureTime, LocalTime returnTime, String price, String travelTime){
        this.transportation = transport.getTransportation();
        this.location = information.getDestinationCity() + " " + transport.getTravelBase();
        this.drawableID = transport.getDrawableID();

        this.information = information;

        this.depatureTime = depatureTime;
        this.returnTime = returnTime;

        this.price = price;

        this.travelTime = travelTime;
    }

    public ETransportation getTransportation() {
        return transportation;
    }

    public String getLocation() {
        return location;
    }

    public LocalTime getDepatureTime() {
        return depatureTime;
    }

    public String getDepatureTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeString = depatureTime.format(formatter);
        return timeString;
    }

    public LocalTime getReturnTime() {
        return returnTime;
    }

    public String getReturnTimeAsString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String timeString = returnTime.format(formatter);
        return timeString;
    }

    public String getPrice() {
        return price;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public TravelInformation getInformation() {
        return information;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public String getStartDateAsString(){
        return information.getStartDateAsString();
    }

    public String getReturnDateAsString(){
        return information.getBackTravelDateAsString();
    }

    @Override
    public String toString() {
        return "TravelRecommendation{" +
                "transportation=" + transportation +
                ", location='" + location + '\'' +
                ", depatureTime=" + depatureTime +
                ", returnTime=" + returnTime +
                ", price='" + price + '\'' +
                ", drawableID=" + drawableID +
                ", information=" + information +
                ", travelTime='" + travelTime + '\'' +
                '}';
    }
}
