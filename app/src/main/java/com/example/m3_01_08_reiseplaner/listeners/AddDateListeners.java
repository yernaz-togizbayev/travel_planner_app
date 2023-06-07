package com.example.m3_01_08_reiseplaner.listeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class AddDateListeners {

    /**
     * Adds listener to an EditText for a date, so that a point gets added automatically
     * after dd and mm.
     * @param dateTextWithListeners
     */
    public static void addEventListenersToEditDateText(EditText dateTextWithListeners){
        dateTextWithListeners.addTextChangedListener(new TextWatcher() {
            private boolean isFormatting;
            private boolean deletingPoint;
            private int pointStart;
            private boolean deletingBackward;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                deletingBackward = count > after;
                if (deletingBackward && s.charAt(start) == '.') {
                    deletingPoint = true;
                    pointStart = start;
                    return;
                }

                deletingPoint = false;

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean biggerCount = before < count;
                boolean checkPosition = (count - start == 2 || count - start == 5);
                isFormatting = biggerCount && checkPosition;
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isFormatting) {
                    isFormatting = false;
                    return;
                }

                if (deletingPoint) {
                    deletingPoint = false;
                    s.delete(pointStart - 1, pointStart);

                    return;
                }

                if (deletingBackward) {
                    deletingBackward = false;

                    return;
                }

                if (s.length() == 2 || s.length() == 5) {
                    s.append('.');
                }
            }
        });
    }
}
