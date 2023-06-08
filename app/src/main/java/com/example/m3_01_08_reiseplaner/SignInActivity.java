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
 * The class which represents Sign In screen. It has a functionality to sign in to EasyTravel
 * application, where users will be asked to enter their email address and password, and then
 * navigate to the main menu screen if the sign in succeeded.
 */
public class SignInActivity extends AppCompatActivity {

    private static User currentUser;

    /**
     * Initialization of SignInActivity and setting up the view of a content.
     * @param savedInstanceState State of saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
    }

    /**
     * Handles the button press to go back from the sign in screen to the start screen.
     * @param view The button that was pressed by the user.
     */
    public void SignInGoBackPress(View view) {
        //change back please
        Intent intent = new Intent(SignInActivity.this, StartScreenActivity.class);
        startActivity(intent);
    }

    public void ForgotPasswordPress(View view) {
        Intent intent = new Intent(SignInActivity.this, PasswordResetActivity.class);
        startActivity(intent);
    }

    /**
     * This method handles the button press event for the sign-in button. It performs validation
     * check whether email address and password are match. In case email could not be found in the
     * list of users or found, but password is incorrect, popup message will appear to inform user
     * that email or password are incorrect. If user email and password entered correctly, popup
     * message will inform user that he successfully logged in and will be switched to main menu.
     * @param view The button that was pressed by the user.
     */
    public void SignInButtonPress(View view) {
        EditText emailEditText = findViewById(R.id.SigninEmailEditText);
        EditText passwordEditText = findViewById(R.id.SigninPasswordEditText);
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        User user = findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            currentUser = user;
            showSignInPopupMessage("SignIn was successful", Color.parseColor("#a4e8c0"));
            Intent intent = new Intent(SignInActivity.this, MainMenuActivity.class);
            startActivity(intent);
        }
        else {
            showSignInPopupMessage("Invalid email or password", Color.parseColor("#eea29e"));
        }
    }

    /**
     * Returns user currently logged in.
     * Can only be called when there is a user logged in already, else variable won't have a value.
     */
    public static User getCurrentUser() { return currentUser; }

    /**
     * Sets user currently logged in from externally
     * (for example when user information has been edited)
     * @param user new User currently logged in.
     */
    public static void setCurrentUser(User user){
        currentUser = user;
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
    private void showSignInPopupMessage(String textMessage, int backgroundColor) {
        Toast popupMessage = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);

        View popupMessageView = popupMessage.getView();
        popupMessageView.setBackgroundColor(backgroundColor);

        popupMessage.setGravity(Gravity.CENTER, 0, 0);
        popupMessage.show();
    }
}