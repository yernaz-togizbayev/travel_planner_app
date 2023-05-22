package com.example.m3_01_08_reiseplaner;

import android.graphics.drawable.Drawable;

public class Event {
    private String name = "Lunch";
    private String date = "24/4/23";
    private String time = "12:00";
    private String region = "Vienna";
    private String street = "Währinger Straße";
    private String postalCode = "4050";
    private String houseNumber = "29";

    private int icon = R.drawable.houseicon_small;

    public Event(String name, String date, String time, String region, String street, String postalCode, String houseNumber) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.region = region;
        this.street = street;
        this.postalCode = postalCode;
        this.houseNumber = houseNumber;
        icon = R.drawable.houseicon_small;
    }

    public Event() {
        houseNumber = "29";
        postalCode = "4050";
        street = "Währinger Straße";
        region = "Vienna";
        time = "12:00";
        date = "24/4/23";
        name = "Lunch";

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
