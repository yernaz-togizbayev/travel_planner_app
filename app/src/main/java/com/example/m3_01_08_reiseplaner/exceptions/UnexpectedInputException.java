package com.example.m3_01_08_reiseplaner.exceptions;

public class UnexpectedInputException extends Exception{
    public UnexpectedInputException() {
    }

    public UnexpectedInputException(String message) {
        super(message);
    }
}
