package com.example.m3_01_08_reiseplaner.travelDataStructures;

import com.example.m3_01_08_reiseplaner.enums.ETransportation;

/**
 * Class that stores Data about a way of transportation.
 * Is a helper for Mocking
 */
public class Transport {
    private ETransportation transportation;
    private int drawableID;

    private String travelBase;

    public Transport(ETransportation transportation, int drawableID, String travelBase) {
        this.transportation = transportation;
        this.drawableID = drawableID;
        this.travelBase = travelBase;
    }

    public ETransportation getTransportation() {
        return transportation;
    }

    public int getDrawableID() {
        return drawableID;
    }

    public String getTravelBase(){
        return this.travelBase;
    }
}
