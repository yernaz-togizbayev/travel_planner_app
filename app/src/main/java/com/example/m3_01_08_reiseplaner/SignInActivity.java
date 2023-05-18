package com.example.m3_01_08_reiseplaner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        // Initial user 1
        User MaxMustermann = new User (
                "Max",
                "Mustermann",
                "01.01.2000",
                "Wahringer Strasse",
                "29",
                "Vienna",
                "1090",
                "Austria",
                "max.mustermann@gmail.com",
                "Max123"
        );

        // Initial user 2
        User ErikaMustermann = new User (
                "Erika",
                "Mustermann",
                "01.01.2000",
                "Wahringer Strasse",
                "29",
                "Vienna",
                "1090",
                "Austria",
                "erika.mustermann@gmail.com",
                "Erika123"
        );

        // Initial user 3
        User JohnDoe = new User (
                "John",
                "Doe",
                "01.01.2000",
                "Miami Beach",
                "5",
                "Florida",
                "33109",
                "United States",
                "john.doe@gmail.com",
                "John123"
        );

        // Add all 3 users to a list of users
        SignUpActivity.getUserList().add(MaxMustermann);
        SignUpActivity.getUserList().add(ErikaMustermann);
        SignUpActivity.getUserList().add(JohnDoe);
    }

    public void SignInGoBackPress(View view) {
        Intent intent = new Intent(SignInActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }

    public void SignInButtonPress(View view) {
        EditText emailEditText = findViewById(R.id.SigninEmailEditText);
        EditText passwordEditText = findViewById(R.id.SigninPasswordEditText);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        User user = findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            Toast.makeText(this, "Sign in successful", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Invalid email or password", Toast.LENGTH_LONG).show();
        }
    }

    @Nullable
    private User findUserByEmail(String email) {
        for (User user : SignUpActivity.getUserList()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}