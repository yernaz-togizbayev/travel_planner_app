package com.example.m3_01_08_reiseplaner.enums;

/**
 * Shows what kind of Transportation is used
 */
public enum ETransportation {
    BUS("Bus"),
    PLANE("Plane"),
    SHIP("Ship"),
    TRAIN("train");

    private String name;

    ETransportation(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
