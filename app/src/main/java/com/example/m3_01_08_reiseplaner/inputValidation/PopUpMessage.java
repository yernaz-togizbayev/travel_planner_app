package com.example.m3_01_08_reiseplaner.inputValidation;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

public class PopUpMessage {
    /**
     * Function to display a popup message with text and background color.
     * @param textMessage The text to display in the popup message.
     * @param backgroundColor The background color of the popup message.
     */
    public static void showPopupMessage(String textMessage, int backgroundColor, Context context) {
        Toast popupMessage = Toast.makeText(context, textMessage, Toast.LENGTH_LONG);

        View popupMessageView = popupMessage.getView();
        popupMessageView.setBackgroundColor(backgroundColor);

        popupMessage.setGravity(Gravity.CENTER, 0, 0);
        popupMessage.show();
    }

    /**
     * Shows a Pop Up with a red warning Color
     * @param textMessage
     * @param context
     */
    public static void showWarnPopUpMessage(String textMessage, Context context){

        String red = "#eea29e";
       int backgroundColor = Color.parseColor(red);
       showPopupMessage(textMessage, backgroundColor, context);
    }

    /**
     * Shows a Pop Up with a red warning Color
     * @param textMessage
     * @param context
     */
    public static void showAcceptPopUpMessage(String textMessage, Context context){
        String green = "#a4e8c0";
        int backgroundColor = Color.parseColor(green);
        showPopupMessage(textMessage, backgroundColor, context);
    }
}
