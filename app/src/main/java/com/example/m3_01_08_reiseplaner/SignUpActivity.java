package com.example.m3_01_08_reiseplaner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {
    private Spinner countryDropDown;
    private static List<User> userList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        countryDropDown = findViewById(R.id.CountryDropDown);
        setCountriesDropDownMenu();
    }

    public void SignUpButtonPress(View view) {
        EditText firstNameEditText = findViewById(R.id.FirstNameEditText);
        EditText lastNameEditText = findViewById(R.id.LastNameEditText);
        EditText dateOfBirthEditText = findViewById(R.id.DateOfBirthEditText);
        EditText streetEditText = findViewById(R.id.StreetEditText);
        EditText houseNrEditText = findViewById(R.id.HouseNrEditText);
        EditText cityEditText = findViewById(R.id.CityEditText);
        EditText zipCodeEditText = findViewById(R.id.ZipCodeEditText);
        String country = countryDropDown.getSelectedItem().toString();
        EditText emailEditText = findViewById(R.id.SignupEmailEditText);
        EditText passwordSignupEditText = findViewById(R.id.SignupPasswordEditText);

        User user = new User(
                firstNameEditText.getText().toString(),
                lastNameEditText.getText().toString(),
                dateOfBirthEditText.getText().toString(),
                streetEditText.getText().toString(),
                houseNrEditText.getText().toString(),
                cityEditText.getText().toString(),
                zipCodeEditText.getText().toString(),
                country,
                emailEditText.getText().toString(),
                passwordSignupEditText.getText().toString());

        userList.add(user);

        Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    public void SignUpGoBackPress(View view) {
        Intent intent = new Intent(SignUpActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }

    // Function to read countries.json file
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

    // Extract all countries from json file and add to spinner
    private void setCountriesDropDownMenu() {
        try {
            List<String> listOfCountries = new ArrayList<>();

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

            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, listOfCountries);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            countryDropDown.setAdapter(adapter);

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    public static List<User> getUserList() {
        return userList;
    }
}