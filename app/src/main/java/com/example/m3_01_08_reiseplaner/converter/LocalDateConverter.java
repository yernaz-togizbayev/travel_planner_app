package com.example.m3_01_08_reiseplaner.converter;

import java.time.LocalDate;

public class LocalDateConverter {

    /**
     * Converts a LocalDate into a String in Format "dd.MM.yyyy"
     * @param date
     * @return
     */
    static public String localDateToString(LocalDate date){
        StringBuilder dateString = new StringBuilder();

        int day = date.getDayOfMonth();
        appendMonthAndDays(dateString, day);
        dateString.append(".");

        int month = date.getMonthValue();
        appendMonthAndDays(dateString, month);
        dateString.append(".");

        int year = date.getYear();
        dateString.append(year);

        return dateString.toString();
    }

    /**
     * Adds a zero to the beginning of the day or month if the value is below 10
     * @param builder
     * @param monthOrDayValue
     */
    private static void appendMonthAndDays(StringBuilder builder, int monthOrDayValue){
        int twoCharTreshold = 10;

        if(monthOrDayValue < twoCharTreshold){
            builder.append("0");
        }
        builder.append(monthOrDayValue);
    }
}
