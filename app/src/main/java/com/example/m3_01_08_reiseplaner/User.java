package com.example.m3_01_08_reiseplaner;

public class User {
    private String firstName;
    private String lastName;
    private String dateOfBirth;
    private String street;
    private String houseNumber;
    private String city;
    private String zip;
    private String country;
    private String email;
    private String password;
    private String confirmPassword;


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
