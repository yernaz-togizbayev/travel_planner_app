package com.example.m3_01_08_reiseplaner.inputValidation;

import com.example.m3_01_08_reiseplaner.exceptions.UnexpectedInputException;

import java.util.List;

public class InputValidation {
    public static void checkIfInputsAreEmpty(List<String> inputs) throws UnexpectedInputException{
        for(String input: inputs){
            if(input.isEmpty()){
                throw new UnexpectedInputException("Please fill out all the Forms!");
            }
        }
    }
}
