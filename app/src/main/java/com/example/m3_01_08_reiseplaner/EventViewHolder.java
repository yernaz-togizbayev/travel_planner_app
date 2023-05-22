package com.example.m3_01_08_reiseplaner;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {
    View view;
    ImageView icon;
    TextView eventName;
    TextView eventRegion;
    TextView eventDate;
    TextView eventTime;
    ;


    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        icon = (ImageView) itemView.findViewById(R.id.iconEventOverview);
        eventName = (TextView) itemView.findViewById(R.id.NameEventOverview);
        eventRegion = (TextView) itemView.findViewById(R.id.regionEventOverview);
        eventDate = (TextView) itemView.findViewById(R.id.dateEventOverview);
        eventTime = (TextView) itemView.findViewById(R.id.timeEventOverview);
        view = itemView;
    }
}
