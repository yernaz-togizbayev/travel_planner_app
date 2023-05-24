package com.example.m3_01_08_reiseplaner.inputValidation;

import com.example.m3_01_08_reiseplaner.exceptions.UnexpectedInputException;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateValidation {
    public static void checkIfDatesMakeSense(LocalDate startDate, LocalDate endDate) throws UnexpectedInputException {
        checkIfDatesAreInThePast(startDate, endDate);
        checkIfDatesAreInCorrectOrder(startDate, endDate);
    }

    private static void checkIfDatesAreInThePast(LocalDate startDate, LocalDate endDate) throws UnexpectedInputException {
        LocalDate currentDate = LocalDate.now();

        long daysBetween = currentDate.until(startDate, ChronoUnit.DAYS);

        if(daysBetween < 0){
            throw new UnexpectedInputException("You can't pick Dates in the past!");
        }

        daysBetween = currentDate.until(endDate, ChronoUnit.DAYS);

        if(daysBetween < 0){
            throw new UnexpectedInputException("You can't pick Dates in the past!");
        }
    }

    private static void checkIfDatesAreInCorrectOrder(LocalDate startDate, LocalDate endDate) throws UnexpectedInputException {
        long daysBetween = startDate.until(endDate, ChronoUnit.DAYS);

        if(daysBetween < 0){
            throw new UnexpectedInputException("Return Date can't be before you start the travel!");
        }
    }


}
