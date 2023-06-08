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

/**
 * The PasswordResetActivity class is responsible for handling the password reset functionality.
 * It allows users to reset their password by providing their email, new password, and confirming
 * the new password. If the user's email is found in the user list and the new password meets the
 * requirements, the password is reset. Otherwise, appropriate error messages are displayed.
 */
public class PasswordResetActivity extends AppCompatActivity {
    private EditText passwordResetEmailEditText;
    private EditText newPasswordEditText;
    private EditText confirmNewPasswordEditText;

    /**
     * Initialization of PasswordResetActivity and setting up the view of a content.
     * @param savedInstanceState State of saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        passwordResetEmailEditText = findViewById(R.id.PasswordResetEmailEditText);
        newPasswordEditText = findViewById(R.id.NewPasswordEditText);
        confirmNewPasswordEditText = findViewById(R.id.ConfirmNewPasswordEditText);
    }

    /**
     * Handles the button press event for resetting the password. It first checks whether user
     * with this email exist. In case not exist, it will show popup message that email is  invalid,
     * otherwise it checks for password validation and match of new password and confirmation of a
     * new password. If check succeeded, the new password will be set up and screen will be switched
     * back to sign-in screen, otherwise appropriate message will be displayed.
     * @param view The button that was pressed by the user.
     */
    public void resetPasswordButtonPress(View view) {
        String email = passwordResetEmailEditText.getText().toString();
        String newPassword = newPasswordEditText.getText().toString();
        String confirmNewPassword = confirmNewPasswordEditText.getText().toString();

        User user = findUserByEmail(email);

        if (user != null) {
            if (isPasswordValid(newPassword)) {
                if (newPassword.equals(confirmNewPassword)) {
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
                showPasswordResetPopupMessage( "Invalid password. Password must:\n" +
                                "\t* contain at least 1 uppercase letter\n" +
                                "\t* contain at least 1 lowercase letter\n" +
                                "\t* contain at least 1 digit\n" +
                                "\t* be a minimum of 6 characters in length",
                        Color.parseColor("#eea29e"));
            }
        } else {
            showPasswordResetPopupMessage(
                    "User not found",
                    Color.parseColor("#eea29e"));
        }
    }

    /**
     * Handles the button press to go back from the password reset screen to the sign in screen.
     * @param view The button that was pressed by the user.
     */
    public void PasswordResetGoBackPress(View view) {
        Intent intent = new Intent(PasswordResetActivity.this, SignInActivity.class);
        startActivity(intent);
    }

    /**
     * This method is called when the user presses the password requirements icon.
     * It displays a popup message with the password requirements to guide the user.
     * @param view The icon that was pressed by the user.
     */
    public void passwordRequirementsIconPress(View view) {
        showPasswordResetPopupMessage( "Password must:\n" +
                        "\t* contain at least 1 uppercase letter\n" +
                        "\t* contain at least 1 lowercase letter\n" +
                        "\t* contain at least 1 digit\n" +
                        "\t* be a minimum of 6 characters in length",
                Color.parseColor("#eea29e"));
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