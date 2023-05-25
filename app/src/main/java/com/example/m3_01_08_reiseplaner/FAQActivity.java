package com.example.m3_01_08_reiseplaner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FAQActivity extends AppCompatActivity {

    private static final String[] questions = {
            "How can I change my Account settings?",
            "How can I book a travel?",
            "How can I delete a saved Travel?",
            "How can I add events?",
            "How can I delete events?"
    };

    private static final String[] answers = {
            "From Main Menu, go to Settings, press “EDIT PROFILE”, change your personal information accordingly and don't forget save your changes!",
            "In the Main Menu, click on \"New Travel\", choose both your city and country, as well as the desired destination, " +
                    "pick your departure- and return dates from the calendar (or type it in manually), " +
                    "choose your priority, whether you want \"eco\", \"fast\" or \"cheap\" travel and then press \"Search for Travel\".",
            "In the Main Menu, you can see the overview of all your planned travels. Here, just press on the \"Trash\"-symbol for the travel you would like to delete.",
            "In the Main Menu, press \"Details\" for the travel you would like to add events to. " +
                    "There, you will be forwarded to a screen on which you should be able to see an \"Add Event\"-button. Press it, add an \"Event Name\", " +
                    "choose an icon for your event, fill in the rest of the form, and finally, save your event.",
            "If you want to delete an event, go to the \"Details\" for your planned travel, there, you should see all your chosen events for your trip. Press on the \"Trash\"-symbol to delete an event."
    };

    /**
     * Sets up the Screen and sets the version text at the bottom of it.
     *
     * Apart from that also sets up the ExpandableList.
     * This includes a List for the texts of the big bold text (parentData)
     * and another one for the small texts that drop down when bold text is pressed (childData)
     * childData is a list of lists, because there can be several texts dropping down
     *
     * The relevant data, which is saved in the strings above, gets funneled into the lists.
     * These then get put into an adapter, which feeds the data into the ExpandableList View.
     * There is also a custom faq_question_text layout file, which ensures that the text dropping down is formatted correctly.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        TextView versionText = findViewById(R.id.versionText);
        versionText.setText(MainMenuActivity.version);


        ExpandableListView expList = findViewById(R.id.faqExpandableList);

        List<Map<String, String>> parentData = new ArrayList<>();
        List<List<Map<String, String>>> childData = new ArrayList<>();

        for (int i = 0; i < questions.length; ++i) {
            Map<String, String> parentMap = new HashMap<>();
            parentData.add(parentMap);
            parentMap.put("q", questions[i]);

            List<Map<String, String>> childList = new ArrayList<>();
            Map<String, String> tmp = new HashMap<>();
            childList.add(tmp);
            tmp.put("a", answers[i]);

            childData.add(childList);
        }

        ExpandableListAdapter Adapter = new SimpleExpandableListAdapter(
                this,
                parentData,
                android.R.layout.simple_expandable_list_item_1,
                new String[]{"q"},
                new int[]{android.R.id.text1},
                childData,
                R.layout.faq_question_text,
                new String[]{"a"},
                new int[]{R.id.text}
        );
        expList.setAdapter(Adapter);
    }

    /**
     * Handles the button press for going back to the Info-Screen.
     */
    public void faqGoBackPress(View view){
        Intent infoScreen = new Intent(this, InfoActivity.class);
        startActivity(infoScreen);
    }
}
