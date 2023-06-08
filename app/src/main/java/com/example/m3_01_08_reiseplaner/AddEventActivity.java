package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.m3_01_08_reiseplaner.converter.LocalDateConverter;
import com.example.m3_01_08_reiseplaner.listeners.AddDateListeners;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AddEventActivity extends AppCompatActivity {
    static List<Event> events = new ArrayList<>();
    public static int currentIcon = R.drawable.museumicon;

    Event currentevent = new Event();

    private static final int REQUEST_CODE_CALENDAR_DATE= 1001;

    private static final String INTENT_KEY_CALENDER = "CalendarKey";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addevent);
        ImageView im = findViewById(R.id.preferedIcon);
        im.setImageResource(currentIcon);
        addListenerToDateField();
    }

    /**
     * Adds listeners to the Date Field
     */
    private void addListenerToDateField(){
        EditText dateEditText = findViewById(R.id.DateEditText);

        AddDateListeners.addEventListenersToEditDateText(dateEditText);
    }

    public void addEventButtonPress(View view){
        Event event = setEvent();
        if (event == null) return;
        events.add(event);

        Log.d("Event Added", event.getName());
        UpdatedEventOverviewActivity.addOneEvent(event);

        Intent intent = new Intent(AddEventActivity.this, UpdatedEventOverviewActivity.class);
        startActivity(intent);
    }

    @Nullable
    private Event setEvent() {
        Event event = new Event();
        TextView eventName = findViewById(R.id.EventNameEditText);

        TextView eventDate = findViewById(R.id.DateEditText);
        TextView eventRegion = findViewById(R.id.RegionEditText);
        TextView eventStreet = findViewById(R.id.StreetEditText);
        TextView eventTime = findViewById(R.id.TimeEditText);
        TextView eventPostalCode = findViewById(R.id.PostalCodeEditText);
        TextView eventHouseNumber = findViewById(R.id.HouseNumberEditText);

        if(eventDate != null && eventDate.getText() != null && eventDate.getText().length()>12) {
            showSignUpPopupMessageEvents("please enter a correct date in form DD/MM/YY or DD.MM.YYYY", Color.parseColor("#eea29e"));
            return null;
        }

        /*if( eventTime != null && eventTime.getText() != null && eventTime.getText().length()!=5) {
            showSignUpPopupMessageEvents("please enter a correct time in form 12:00 ", Color.parseColor("#eea29e"));
            return;
        }*/

        if(eventName != null && eventName.getText() != null && !eventName.getText().toString().isEmpty())
            event.setName(eventName.getText().toString());

        if(eventDate != null && eventDate.getText() != null && !eventDate.getText().toString().isEmpty())
            event.setDate(eventDate.getText().toString());

        if(eventRegion != null && eventRegion.getText() != null && !eventRegion.getText().toString().isEmpty())
            event.setRegion(eventRegion.getText().toString());

        if(eventStreet != null && eventStreet.getText() != null && !eventStreet.getText().toString().isEmpty())
            event.setStreet(eventStreet.getText().toString());

        if(eventTime.getText() != null && !eventTime.getText().toString().isEmpty())
            event.setTime(eventTime.getText().toString());

        if(eventPostalCode.getText() != null && !eventPostalCode.getText().toString().isEmpty())
            event.setPostalCode(eventPostalCode.getText().toString());

        if(eventHouseNumber.getText() != null && !eventHouseNumber.getText().toString().isEmpty())
            event.setHouseNumber(eventHouseNumber.getText().toString());

        event.setIcon(currentIcon);
        return event;
    }

    public void chooseIconButtonPress(View view){

        Event event = new Event();
        TextView eventName = findViewById(R.id.EventNameEditText);

        TextView eventDate = findViewById(R.id.DateEditText);
        TextView eventRegion = findViewById(R.id.RegionEditText);
        TextView eventStreet = findViewById(R.id.StreetEditText);
        TextView eventTime = findViewById(R.id.TimeEditText);
        TextView eventPostalCode = findViewById(R.id.PostalCodeEditText);
        TextView eventHouseNumber = findViewById(R.id.HouseNumberEditText);

        if(eventName != null && eventName.getText() != null && !eventName.getText().toString().isEmpty())
            event.setName(eventName.getText().toString());

        if(eventDate != null && eventDate.getText() != null && !eventDate.getText().toString().isEmpty())
            event.setDate(eventDate.getText().toString());

        if(eventRegion != null && eventRegion.getText() != null && !eventRegion.getText().toString().isEmpty())
            event.setRegion(eventRegion.getText().toString());

        if(eventStreet != null && eventStreet.getText() != null && !eventStreet.getText().toString().isEmpty())
            event.setStreet(eventStreet.getText().toString());

        if(eventTime.getText() != null && !eventTime.getText().toString().isEmpty())
            event.setTime(eventTime.getText().toString());

        if(eventPostalCode.getText() != null && !eventPostalCode.getText().toString().isEmpty())
            event.setPostalCode(eventPostalCode.getText().toString());

        if(eventHouseNumber.getText() != null && !eventHouseNumber.getText().toString().isEmpty())
            event.setHouseNumber(eventHouseNumber.getText().toString());

        currentevent = event;

        Intent intent = new Intent(AddEventActivity.this, ChooseIconActivity.class);
        startActivity(intent);

        ImageView im = findViewById(R.id.preferedIcon);
        im.setImageResource(currentIcon);
    }

    public void saveEvent(){
        TextView eventName = findViewById(R.id.EventNameEditText);
        TextView eventDate = findViewById(R.id.DateEditText);
        TextView eventRegion = findViewById(R.id.RegionEditText);
        TextView eventStreet = findViewById(R.id.StreetEditText);
        TextView eventTime = findViewById(R.id.TimeEditText);
        TextView eventPostalCode = findViewById(R.id.PostalCodeEditText);
        TextView eventHouseNumber = findViewById(R.id.HouseNumberEditText);

        if(currentevent.getName() != "Lunch")
            eventName.setText(currentevent.getName());
        if(currentevent.getDate() != "24/4/23")
            eventDate.setText(currentevent.getDate());
        if(currentevent.getRegion() != "Vienna")
            eventRegion.setText(currentevent.getRegion());
        if(currentevent.getStreet() != "Währinger Straße")
            eventStreet.setText(currentevent.getStreet());
        if(currentevent.getPostalCode() != "4050")
            eventPostalCode.setText(currentevent.getPostalCode());
        if(currentevent.getHouseNumber() != "29")
            eventHouseNumber.setText(currentevent.getHouseNumber());
        if(currentevent.getTime() != "12:00")
            eventTime.setText(currentevent.getTime());
    }

    public void goBackAddEvent(View view){
        Intent intent = new Intent(AddEventActivity.this, UpdatedEventOverviewActivity.class);
        startActivity(intent);
    }

    public static void setCurrentIcon(int id){
        currentIcon = id;
    }

    public List<Event> getEvents(){
        return events;
    }

    private void showSignUpPopupMessageEvents(String textMessage, int backgroundColor) {
        Toast popupMessage = Toast.makeText(this, textMessage, Toast.LENGTH_LONG);

        View popupMessageView = popupMessage.getView();
        popupMessageView.setBackgroundColor(backgroundColor);

        popupMessage.setGravity(Gravity.CENTER, 0, 0);
        popupMessage.show();
    }

    ActivityResultLauncher<Intent> setDepatureDateResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                setDepartureDateText(result.getData());
            }
        }
    });

    private void setDepartureDateText(Intent data){
        if(data == null){
            return;
        }
        LocalDate departureDate = (LocalDate) data.getSerializableExtra(INTENT_KEY_CALENDER);
        EditText departureDateText = findViewById(R.id.DateEditText);
        String departureDateOutput = LocalDateConverter.localDateToString(departureDate);
        departureDateText.setText(departureDateOutput);
    }

    public void onEventDateButtonPress(View view){
        Intent departureDateCalendarIntent = new Intent(this, CalendarActivity.class);
        setDepatureDateResult.launch(departureDateCalendarIntent);
    }

    ActivityResultLauncher<Intent> setReturnDateResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result != null && result.getResultCode() == RESULT_OK){
                setReturnDateText(result.getData());
            }
        }
    });

    private void setReturnDateText(Intent data){
        if(data == null){
            return;
        }
        LocalDate departureDate = (LocalDate) data.getSerializableExtra(INTENT_KEY_CALENDER);
        EditText departureDateText = findViewById(R.id.DateEditText);
        String departureDateOutput = LocalDateConverter.localDateToString(departureDate);
        departureDateText.setText(departureDateOutput);
    }

    public void onReturnDateButtonPress(View view){
        Intent departureDateCalendarIntent = new Intent(this, CalendarActivity.class);
        setReturnDateResult.launch(departureDateCalendarIntent);
    }



}
