package com.example.m3_01_08_reiseplaner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
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