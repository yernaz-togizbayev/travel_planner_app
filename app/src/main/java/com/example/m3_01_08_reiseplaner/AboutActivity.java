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

public class AboutActivity extends AppCompatActivity {

    private static final String[] policy = {
            "Privacy Policy",
            "Terms of Service",
    };

    private static final String[] text = {
                   "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eleifend odio dolor, eget ullamcorper augue aliquet vitae. Aenean orci eros, vehicula in viverra quis, tempor ac odio. Aenean vitae sollicitudin purus. Praesent sodales interdum massa, eget feugiat nulla. In eleifend mi eget enim dictum lacinia. Integer ante dui, tempor vitae pellentesque aliquam, lobortis quis dui. Phasellus vestibulum fringilla quam, in lobortis sem vulputate et.\n" +
                   "\n" +
                   "Donec nec arcu ullamcorper, pulvinar ligula in, feugiat turpis. Ut maximus semper enim, at porta nunc tristique id. Fusce sit amet elementum eros. In sit amet venenatis urna. Mauris commodo tristique tortor vitae scelerisque. Integer volutpat dapibus massa ultrices fermentum. Morbi euismod rutrum leo, sed scelerisque quam aliquam luctus.\n" +
                   "\n" +
                   "Nulla non felis quis turpis fringilla hendrerit non a magna. Nam lectus nibh, viverra in tristique tempus, feugiat vitae enim. Sed lacus mi, varius iaculis placerat sit amet, varius quis augue. Praesent nibh nisl, molestie sed accumsan a, viverra at lacus. Phasellus nec iaculis metus. Curabitur sodales massa et consequat mattis. Maecenas feugiat, dui vitae fermentum pulvinar, orci elit faucibus nisi, ut tincidunt sem ex id ex. Integer semper libero vitae justo pulvinar posuere. Sed ipsum lorem, bibendum vel erat eget, rhoncus porttitor est. Aliquam erat volutpat. Praesent ac sapien tincidunt ipsum blandit auctor. Ut finibus suscipit justo eget efficitur. Sed elementum sollicitudin ultricies. Duis commodo, enim a pulvinar eleifend, sem sem fringilla sapien, id auctor purus est sed enim.\n" +
                   "\n" +
                   "Aenean mattis fermentum quam. Aenean volutpat gravida augue. Ut a tincidunt dui. Vivamus volutpat gravida velit. Ut laoreet at velit ut faucibus. Curabitur blandit turpis orci, nec pellentesque nibh volutpat et. Proin elementum egestas eleifend. Vivamus quis felis vestibulum, lobortis dui id, blandit augue. Vestibulum et convallis felis. Vivamus nec purus non massa suscipit efficitur. Pellentesque varius imperdiet velit, a elementum eros pharetra vel.\n" +
                   "\n" +
                   "Phasellus massa elit, auctor et sapien non, porttitor dictum nulla. Nam id porttitor mi. Integer commodo, sapien at ultricies porta, lorem lectus tempus est, sit amet sagittis ex lectus non magna. Praesent aliquet euismod sapien, vel gravida neque cursus a. Sed vitae porta massa, sit amet semper felis. Donec tincidunt, quam ac hendrerit condimentum, neque augue congue ligula, eu aliquam nisl elit iaculis nulla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur porttitor quam ac ullamcorper hendrerit. Nam eleifend ex vel augue venenatis, ornare consectetur odio sodales.",

                    "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque eleifend odio dolor, eget ullamcorper augue aliquet vitae. Aenean orci eros, vehicula in viverra quis, tempor ac odio. Aenean vitae sollicitudin purus. Praesent sodales interdum massa, eget feugiat nulla. In eleifend mi eget enim dictum lacinia. Integer ante dui, tempor vitae pellentesque aliquam, lobortis quis dui. Phasellus vestibulum fringilla quam, in lobortis sem vulputate et.\n" +
                    "\n" +
                    "Donec nec arcu ullamcorper, pulvinar ligula in, feugiat turpis. Ut maximus semper enim, at porta nunc tristique id. Fusce sit amet elementum eros. In sit amet venenatis urna. Mauris commodo tristique tortor vitae scelerisque. Integer volutpat dapibus massa ultrices fermentum. Morbi euismod rutrum leo, sed scelerisque quam aliquam luctus.\n" +
                    "\n" +
                    "Nulla non felis quis turpis fringilla hendrerit non a magna. Nam lectus nibh, viverra in tristique tempus, feugiat vitae enim. Sed lacus mi, varius iaculis placerat sit amet, varius quis augue. Praesent nibh nisl, molestie sed accumsan a, viverra at lacus. Phasellus nec iaculis metus. Curabitur sodales massa et consequat mattis. Maecenas feugiat, dui vitae fermentum pulvinar, orci elit faucibus nisi, ut tincidunt sem ex id ex. Integer semper libero vitae justo pulvinar posuere. Sed ipsum lorem, bibendum vel erat eget, rhoncus porttitor est. Aliquam erat volutpat. Praesent ac sapien tincidunt ipsum blandit auctor. Ut finibus suscipit justo eget efficitur. Sed elementum sollicitudin ultricies. Duis commodo, enim a pulvinar eleifend, sem sem fringilla sapien, id auctor purus est sed enim.\n" +
                    "\n" +
                    "Aenean mattis fermentum quam. Aenean volutpat gravida augue. Ut a tincidunt dui. Vivamus volutpat gravida velit. Ut laoreet at velit ut faucibus. Curabitur blandit turpis orci, nec pellentesque nibh volutpat et. Proin elementum egestas eleifend. Vivamus quis felis vestibulum, lobortis dui id, blandit augue. Vestibulum et convallis felis. Vivamus nec purus non massa suscipit efficitur. Pellentesque varius imperdiet velit, a elementum eros pharetra vel.\n" +
                    "\n" +
                    "Phasellus massa elit, auctor et sapien non, porttitor dictum nulla. Nam id porttitor mi. Integer commodo, sapien at ultricies porta, lorem lectus tempus est, sit amet sagittis ex lectus non magna. Praesent aliquet euismod sapien, vel gravida neque cursus a. Sed vitae porta massa, sit amet semper felis. Donec tincidunt, quam ac hendrerit condimentum, neque augue congue ligula, eu aliquam nisl elit iaculis nulla. Interdum et malesuada fames ac ante ipsum primis in faucibus. Curabitur porttitor quam ac ullamcorper hendrerit. Nam eleifend ex vel augue venenatis, ornare consectetur odio sodales."
    };

    /**
     * This is basically the same onCreate method as FAQActivity's one.
     * If any questions arise, consult FAQActivity.java
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView versionText = findViewById(R.id.versionText);
        versionText.setText(MainMenuActivity.version);

        ExpandableListView expList = findViewById(R.id.aboutExpandableList);

        List<Map<String, String>> parentData = new ArrayList<>();
        List<List<Map<String, String>>> childData = new ArrayList<>();

        for (int i = 0; i < policy.length; ++i) {
            Map<String, String> parentMap = new HashMap<>();
            parentData.add(parentMap);
            parentMap.put("q", policy[i]);

            List<Map<String, String>> childList = new ArrayList<>();
            Map<String, String> tmp = new HashMap<>();
            childList.add(tmp);
            tmp.put("a", text[i]);

            childData.add(childList);
        }

        // Set up the adapter
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
    public void aboutGoBackPress(View view){
        Intent infoScreen = new Intent(this, InfoActivity.class);
        startActivity(infoScreen);
    }
}
