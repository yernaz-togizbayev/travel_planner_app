package com.example.m3_01_08_reiseplaner.enums;

public enum ETravelPreference {
    CHEAP ("Cheap"),
    ECOLOGICALLY("Ecologically"),
    FAST ("Fast");

    private String name;

    private ETravelPreference(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
