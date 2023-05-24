package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    /**
     * Sets up user information to be shown.
     * First gets Views into variables, then fills them with information of user currently
     * logged in.
     * This information is not complete:
     * First and last name gets bundled together.
     * Address only shows Street and Number.
     * Password doesn't actually have the user's password inside it,
     * but a series of zeroes that matches the length of user's password.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        TextView usernameTextView = findViewById(R.id.usernameTextView);
        TextView emailTextView = findViewById(R.id.emailTextView);
        TextView passwordTextView = findViewById(R.id.passwordTextView);
        TextView dobTextView = findViewById(R.id.dobTextView);
        TextView addressTextView = findViewById(R.id.addressTextView);

        User currentUser = SignInActivity.getCurrentUser();

        String[] username = currentUser.getName();
        String usernameFull = username[0] + " " + username[1];
        usernameTextView.setText(usernameFull);

        emailTextView.setText(currentUser.getEmail());
        dobTextView.setText(currentUser.getDateOfBirth());

        String[] address = currentUser.getAddress();
        String addressFull = address[0] + " " + address[1];
        addressTextView.setText(addressFull);

        String pw = String.format("%0" + currentUser.getPassword().length() + "d", 0);
        passwordTextView.setText(pw);
    }

    /**
     * Brings user back to main menu.
     */
    public void SettingsGoBackPress(View view) {
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }

    public void EditUserButtonPress(View view) {
        Intent intent = new Intent(this, EditUserActivity.class);
        startActivity(intent);
    }
}
