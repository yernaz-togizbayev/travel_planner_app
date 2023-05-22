package com.example.m3_01_08_reiseplaner.staticDataStorer;

import com.example.m3_01_08_reiseplaner.travelDataStructures.PlannedTrip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StoredTravels {
    static private List<PlannedTrip> plannedTrips = new ArrayList<PlannedTrip>();

    /**
     * Stores a trip and then sorts the List after its Date
     * @param trip
     */
    static public void  addTrip(PlannedTrip trip){
        plannedTrips.add(trip);
        Collections.sort(plannedTrips);
    }

    static public List<PlannedTrip> getTrips(){
        return plannedTrips;
    }
}
