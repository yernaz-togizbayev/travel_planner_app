package com.example.m3_01_08_reiseplaner;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The SignUpActivity class is responsible for handling user sign-up functionality.
 * It allows users to enter their personal information, validate the input values,
 * create a new user account, and navigate to the sign-in screen.
 */
public class SignUpActivity extends AppCompatActivity {
    //private Spinner countryDropDown; // drop-down menu for selecting the country
    private static final List<User> userList = new ArrayList<>(); // list of registered users
    private static final String INTENT_KEY_CALENDAR = "CalendarKey"; // key for passing calendar data
    private ActivityResultLauncher<Intent> calendarLauncher; // launcher for handling calendar activity

    private TextView countryDropDown;

    private List<String> listOfCountries = new ArrayList<>();

    Dialog countryDialog;

    /**
     * Initialization of SignUpActivity and setting up the view of a content
     * @param savedInstanceState State of saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        countryDropDown = findViewById(R.id.SearchCountryDropDownMenu);
        setCountriesDropDownMenu();

        initializeCalendarLauncher();
    }

    /**
     * This method handles the button press event for the sign-up button. It fetches the user input
     * values from various fields. It performs validation checks on the input values, such as
     * "empty name input", "empty surname input","empty email input", "email duplicates", "password
     * empty input", whether password and password confirmation are match and whether password
     * requirements are succeeded. In case if any of these validation checks fails, appropriate
     * error messages will appear. If all validations pass, a new User object with the provided
     * information will be created, added to the user list, then success message is displayed, and
     * the user is navigated to the SignIn screen.
     * @param view The button that was pressed by the user.
     */
    public void SignUpButtonPress(View view) {
        String firstName = ((EditText) findViewById(R.id.FirstNameEditText)).getText().toString();
        String lastName = ((EditText) findViewById(R.id.LastNameEditText)).getText().toString();
        String dateOfBirth = ((EditText) findViewById(R.id.DateOfBirthEditText)).getText().toString();
        String street = ((EditText) findViewById(R.id.StreetEditText)).getText().toString();
        String houseNr = ((EditText) findViewById(R.id.HouseNrEditText)).getText().toString();
        String city = ((EditText) findViewById(R.id.CityEditText)).getText().toString();
        String zipCode = ((EditText) findViewById(R.id.ZipCodeEditText)).getText().toString();
        String country = ((TextView) findViewById(R.id.SearchCountryDropDownMenu)).getText().toString();
        String email = ((EditText) findViewById(R.id.SignupEmailEditText)).getText().toString();
        String password = ((EditText) findViewById(R.id.SignupPasswordEditText)).getText().toString();
        String confirmPassword = ((EditText) findViewById(R.id.SignupConfirmPasswordEditText)).getText().toString();

        if (firstName.isEmpty()) {
            showSignUpPopupMessage(
                    "Please enter a name!",
                    Color.parseColor("#eea29e"));
        }
        else if (lastName.isEmpty()) {
            showSignUpPopupMessage(
                    "Please enter a surname!",
                    Color.parseColor("#eea29e"));
        }
        else if (email.isEmpty()) {
            showSignUpPopupMessage(
                    "Please enter an email!",
                    Color.parseColor("#eea29e"));
        }
        else if (password.isEmpty()) {
            showSignUpPopupMessage(
                    "Please enter a password!",
                    Color.parseColor("#eea29e"));
        }
        else if (isUserEmailPresent(email)) {
            showSignUpPopupMessage(
                    "User with this email has been already registered. Please use another email.",
                    Color.parseColor("#eea29e"));
        }
        else {
            if (isPasswordValid(password)) {
                if (password.equals(confirmPassword)) {
                    User user;
                    user = new User(
                            firstName,
                            lastName,
                            dateOfBirth,
                            street,
                            houseNr,
                            city,
                            zipCode,
                            country,
                            email,
                            password);

                    userList.add(user);
                    showSignUpPopupMessage("SignUp was successful", Color.parseColor("#a4e8c0"));
                    Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                    startActivity(intent);
                } else {
                    showSignUpPopupMessage(
                            "Passwords don't match. Please try again",
                            Color.parseColor("#eea29e"));
                }
            } else {
                showSignUpPopupMessage( "Invalid password. Password must:\n" +
                                "\t* contain at least 1 uppercase letter\n" +
                                "\t* contain at least 1 lowercase letter\n" +
                                "\t* contain at least 1 digit\n" +
                                "\t* be a minimum of 6 characters in length",
                        Color.parseColor("#eea29e"));
            }
        }
    }

    /**
     * Handles the button press to go back from the sign up screen to the start screen.
     * @param view The button that was pressed by the user.
     */
    public void SignUpGoBackPress(View view) {
        Intent intent = new Intent(SignUpActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }

    /**
     * Initialization of the calendarLauncher for handling results of CalendarActivity.
     */
    private void initializeCalendarLauncher() {
        calendarLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), this::handleCalendarResult);
    }

    /**
     * Handles the result from the CalendarActivity.
     * @param result Activity result which contains selected date.
     */
    private void handleCalendarResult(ActivityResult result) {
        if (result != null && result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            if (data != null) {
                LocalDate chosenDate = (LocalDate) data.getSerializableExtra(INTENT_KEY_CALENDAR);
                setDateOfBirth(chosenDate);
            }
        }
    }

    /**
     * Sets the date of birth based on the chosenDate.
     * @param chosenDate The chosen date of birth.
     */
    private void setDateOfBirth(LocalDate chosenDate) {
        // Format the chosen date as "dd.MM.yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = chosenDate.format(formatter);

        // Set the chosen date to the DateOfBirthTextView
        TextView dateOfBirthTextView = findViewById(R.id.DateOfBirthEditText);
        dateOfBirthTextView.setText(formattedDate);
    }

    /**
     * Handles pressing of button to open the CalendarActivity for selecting the date of birth.
     * @param view The icon that was clicked by the user.
     */
    public void dateOfBirthCalenderIconPress(View view) {
        Intent intent = new Intent(SignUpActivity.this, CalendarActivity.class);
        calendarLauncher.launch(intent);
    }


    /**
     * This method is called when the user presses the password requirements icon.
     * It displays a popup message with the password requirements to guide the user.
     * @param view The icon that was pressed by the user.
     */
    public void passwordRequirementsIconPress(View view) {
        showSignUpPopupMessage( "Password must:\n" +
                        "\t* contain at least 1 uppercase letter\n" +
                        "\t* contain at least 1 lowercase letter\n" +
                        "\t* contain at least 1 digit\n" +
                        "\t* be a minimum of 6 characters in length",
                Color.parseColor("#eea29e"));
    }

    /**
     * Checks if user with entered email already has been registered or not.
     * (if a user email is present in the user list)
     * @param email The email to check for.
     * @return True if the email is found in the user list, false otherwise.
     */
    private boolean isUserEmailPresent(String email) {
        for (User user : userList) {
            if (user.getEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Reads the contents of a JSON file from an input stream and returns it as a string.
     * @param inputStream The input stream which represents the JSON file.
     * @return The contents of the JSON file as a string.
     * @throws IOException if an error occurs while reading the JSON file.
     */
    @NonNull
    private String readJsonFile(InputStream inputStream) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader jsonReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));

        String line;
        while ((line = jsonReader.readLine()) != null) {
            stringBuilder.append(line);
        }

        jsonReader.close();
        return stringBuilder.toString();
    }

    /**
     * Extract all countries from JSON file and sets up drop-down menu alphabetically.
     */
    private void setCountriesDropDownMenu() {
        try {
            InputStream inputStream = getResources().openRawResource(R.raw.countries);
            String jsonCountries = readJsonFile(inputStream);

            JSONArray jsonArray = new JSONArray(jsonCountries);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject countryObj = jsonArray.getJSONObject(i);
                JSONObject name = countryObj.getJSONObject("name");
                String common = name.getString("common");
                listOfCountries.add(common);
            }

            Collections.sort(listOfCountries);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows a new dialog of the countries as dropdown menu.
     * This method is called when the country dropdown view is clicked.
     * @param view The view that was clicked.
     */
    public void showCountryDropdownDialog(View view) {
        countryDialog = new Dialog(SignUpActivity.this);
        countryDialog.setContentView(R.layout.searchable_countries_dropdown_menu);
        countryDialog.getWindow().setLayout(800, 1200);
        countryDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        countryDialog.show();

        EditText searchCountry = countryDialog.findViewById(R.id.SearchCountry);
        ListView listViewOfCountries = countryDialog.findViewById(R.id.ListOfCountries);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(SignUpActivity.this, android.R.layout.simple_list_item_1, listOfCountries);

        listViewOfCountries.setAdapter(adapter);
        searchCountry.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        listViewOfCountries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onCountryDropdownItemSelected(adapter.getItem(position));
            }
        });
    }

    /**
     * Handles the selection of a country from the dropdown.
     * This method is called when a country is selected in the dialog.
     * @param selectedItem The country selected from the dropdown.
     */
    private void onCountryDropdownItemSelected(String selectedItem) {
        countryDropDown.setText(selectedItem);
        countryDialog.dismiss();
    }

    /**
     * Gets a list of users.
     * @return The list of users.
     */
    public static List<User> getUserList() {
        return userList;
    }

    /**
     * Replaces the user that has information to be edited to NewUser
     * @param editedUser old User information
     * @param newUser new User information
     */
    public static void editUserList(User editedUser, User newUser){
        userList.set(userList.indexOf(editedUser), newUser);
    }

    /**
     * Function to check if a password is valid. It checks that the password
     * has at least 1 uppercase letter, 1 lowercase letter und 1 digit.
     * @param password The password to validate.
     * @return True if the password is valid, false otherwise.
     */
    private boolean isPasswordValid(String password) {
        boolean hasUppercaseLetter = false;
        boolean hasLowercaseLetter = false;
        boolean hasDigit = false;

        if (password.length() < 6) {
            return false;
        }

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercaseLetter = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercaseLetter = true;
            } else if (Character.isDigit(c)) {
                hasDigit = true;
            }
        }

        return hasUppercaseLetter && hasLowercaseLetter && hasDigit;
    }

    /**
     * Function to display a popup message with text and background color.
     * @param textMessage The text to display in the popup message.
     * @param backgroundColor The background color of the popup message.
     */
    private void showSignUpPopupMessage(String textMessage, int backgroundColor) {
        Toast popupMessage = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);

        View popupMessageView = popupMessage.getView();
        popupMessageView.setBackgroundColor(backgroundColor);

        popupMessage.setGravity(Gravity.CENTER, 0, 0);
        popupMessage.show();
    }

}