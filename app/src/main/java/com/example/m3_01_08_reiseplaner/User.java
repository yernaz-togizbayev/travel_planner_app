package com.example.m3_01_08_reiseplaner;

/**
 * Representation of a user with various personal information, such as name, surname,
 * date of birth, street, house number, city, zip code, country, email and password.
 */
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
    private String password;

    /**
     * Constructor  of a new User object with the provided personal information.
     * @param firstName User's first name.
     * @param lastName User's last name.
     * @param dateOfBirth User's date of birth.
     * @param street User's street address.
     * @param houseNumber User's house number.
     * @param city User's city.
     * @param zip User's zip code.
     * @param country User's country.
     * @param email User's email address.
     * @param password User's password.
     */
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

    /**
     * Gets email address of user.
     * @return User's email address.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets password of user.
     * @return User's password.
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }
}
