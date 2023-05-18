package com.example.m3_01_08_reiseplaner;

public class User {
    private final String firstName;
    private final String lastName;
    private final String dateOfBirth;
    private final String street;
    private final String houseNumber;
    private final String city;
    private final String zip;
    private final String country;
    private final String email;
    private final String password;


    public User(String firstName,
                String lastName,
                String dateOfBirth,
                String street,
                String houseNumber,
                String city,
                String zip,
                String country,
                String email,
                String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.street = street;
        this.houseNumber = houseNumber;
        this.city = city;
        this.zip = zip;
        this.country = country;
        this.email = email;
        this.password = password;
    }

    // Get email of user
    public String getEmail() {
        return email;
    }

    // // Get password of user
    public String getPassword() {
        return password;
    }

}
