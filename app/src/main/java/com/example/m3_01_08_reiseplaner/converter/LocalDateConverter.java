package com.example.m3_01_08_reiseplaner.converter;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LocalDateConverter {

    private static final String DATE_FORMAT = "dd.MM.yyyy";

    /**
     * Takes a String in Date Format and Converts it to a LocalDate
     * @param dateString
     * @return
     * @throws DateTimeException
     */
    public static LocalDate convertStringToLocalDate(String dateString) throws  DateTimeException{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        LocalDate date = LocalDate.parse(dateString, formatter);

        return date;
    }

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
