package com.example.m3_01_08_reiseplaner.travelDataStructures;

import com.example.m3_01_08_reiseplaner.converter.LocalDateConverter;
import com.example.m3_01_08_reiseplaner.enums.ETravelPreference;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Stores Information about what the users wants from its travl
 */
public class TravelInformation implements Serializable {
    private String startCity;
    private String startCountry;
    private String destinationCity;
    private String destinationCountry;
    private LocalDate startDate;
    private LocalDate backTravelDate;
    private ETravelPreference preference;

    public TravelInformation(String startCity, String startCountry, String destinationCity, String destinationCountry, LocalDate startDate, LocalDate backTravelDate, ETravelPreference preference) {
        this.startCity = startCity;
        this.startCountry = startCountry;
        this.destinationCity = destinationCity;
        this.destinationCountry = destinationCountry;
        this.startDate = startDate;
        this.backTravelDate = backTravelDate;
        this.preference = preference;
    }

    public String getStartCity() {
        return startCity;
    }

    public String getStartCountry() {
        return startCountry;
    }

    public String getDestinationCity() {
        return destinationCity;
    }

    public String getDestinationCountry() {
        return destinationCountry;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public String getStartDateAsString(){
        return LocalDateConverter.localDateToString(startDate);
    }

    public LocalDate getBackTravelDate() {
        return backTravelDate;
    }

    public String getBackTravelDateAsString(){
        return LocalDateConverter.localDateToString(backTravelDate);
    }

    public ETravelPreference getPreference() {
        return preference;
    }

    @Override
    public String toString() {
        return "TravelInformation{" +
                "startCity='" + startCity + '\'' +
                ", startCountry='" + startCountry + '\'' +
                ", destinationCity='" + destinationCity + '\'' +
                ", destinationCountry='" + destinationCountry + '\'' +
                ", startDate=" + startDate +
                ", backTravelDate=" + backTravelDate +
                ", preference=" + preference +
                '}';
    }
}
