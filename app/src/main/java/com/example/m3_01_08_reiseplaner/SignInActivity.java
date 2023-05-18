package com.example.m3_01_08_reiseplaner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
            showSignInPopupMessage("SignIn was successful", Color.parseColor("#a4e8c0"));
            Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }
        else {
            showSignInPopupMessage("Invalid email or password", Color.parseColor("#eea29e"));
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

    private void showSignInPopupMessage(String textMessage, int backgroundColor) {
        Toast popupMessage = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);

        View popupMessageView = popupMessage.getView();
        popupMessageView.setBackgroundColor(backgroundColor);

        popupMessage.setGravity(Gravity.CENTER, 0, 0);
        popupMessage.show();
    }
}