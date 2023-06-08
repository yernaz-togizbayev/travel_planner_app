package com.example.m3_01_08_reiseplaner.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.m3_01_08_reiseplaner.R;

public class ConfirmDeletionDialog {
    private Dialog dialog;
    private IConfirmDeletionListener listener;


    public interface IConfirmDeletionListener{
        void onDelete();
        void onCancel();
    }

    public ConfirmDeletionDialog(Context context, String deletionMessage){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.delete_confirmation);

        TextView deletionTextView = dialog.findViewById(R.id.deletionText);
        deletionTextView.setText(deletionMessage);

        Button cancelButton = dialog.findViewById(R.id.cancelButton);
        Button deleteButton = dialog.findViewById(R.id.deleteConfirmationButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onCancel();
                }
                dialog.dismiss();
            }
        });


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onDelete();
                }
                dialog.dismiss();
            }
        });
    }

    public void setConfirmDeletionListener(IConfirmDeletionListener confirmListener){
        this.listener = confirmListener;
    }

    public void show(){
        dialog.show();
    }
}
