package com.example.m3_01_08_reiseplaner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordResetActivity extends AppCompatActivity {
    private EditText passwordResetEmailEditText;
    private EditText newPasswordEditText;
    private EditText confirmNewPasswordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        passwordResetEmailEditText = findViewById(R.id.PasswordResetEmailEditText);
        newPasswordEditText = findViewById(R.id.NewPasswordEditText);
        confirmNewPasswordEditText = findViewById(R.id.ConfirmNewPasswordEditText);
    }

    public void resetPasswordButtonPress(View view) {
        String email = passwordResetEmailEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String confirmNewPassword = confirmNewPasswordEditText.getText().toString();

        User user = findUserByEmail(email);

        if (user != null) {
            if (!newPassword.isEmpty() && newPassword.equals(confirmNewPassword)) {
                user.setPassword(newPassword);
                showPasswordResetPopupMessage(
                        "Password successfully reset",
                        Color.parseColor("#a4e8c0"));
                finish();
            } else {
                showPasswordResetPopupMessage(
                        "Passwords do not match. Please try again",
                        Color.parseColor("#eea29e"));
            }
        } else {
            showPasswordResetPopupMessage(
                    "User not found",
                    Color.parseColor("#eea29e"));
        }
    }

    /**
     * Finds a user with the specified email in the user list.
     * @param email The email to search for.
     * @return User object if found in list of users, null otherwise.
     */
    @Nullable
    private User findUserByEmail(String email) {
        for (User user : SignUpActivity.getUserList()) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Function to display a popup message with text and background color.
     * @param textMessage The text to display in the popup message.
     * @param backgroundColor The background color of the popup message.
     */
    private void showPasswordResetPopupMessage(String textMessage, int backgroundColor) {
        Toast popupMessage = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);

        View popupMessageView = popupMessage.getView();
        popupMessageView.setBackgroundColor(backgroundColor);

        popupMessage.setGravity(Gravity.CENTER, 0, 0);
        popupMessage.show();
    }
}