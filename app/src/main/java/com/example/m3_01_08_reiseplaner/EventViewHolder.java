package com.example.m3_01_08_reiseplaner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.utils.widget.ImageFilterView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_01_08_reiseplaner.recyclerView.PlannedTripsRecycleViewAdapter;
import com.example.m3_01_08_reiseplaner.staticDataStorer.StoredTravels;
import com.example.m3_01_08_reiseplaner.travelDataStructures.PlannedTrip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventViewHolder extends RecyclerView.Adapter<EventViewHolder.EventsDataHolder> {

    private static List<Event> events = new ArrayList<>();

    private Context context;

    @NonNull
    @Override
    public EventViewHolder.EventsDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.event_card, parent, false);
        return new EventViewHolder.EventsDataHolder(view, context, events);
    }
    public EventViewHolder(Context context, List<Event> event){
        this.context = context;
        this.events = event;
    }
    @Override
    public void onBindViewHolder(@NonNull EventViewHolder.EventsDataHolder holder, int position) {
        Event currentEvent = events.get(position);

        float density = context.getResources().getDisplayMetrics().density;
        int widthInDp = 157;    // Desired width in DP
        int heightInDp = 150;   // Desired height in DP

        int widthInPixels = (int) (widthInDp * density);
        int heightInPixels = (int) (heightInDp * density);

        holder.eventName.setText(currentEvent.getName());
        holder.eventRegion.setText(currentEvent.getRegion());
        holder.eventDate.setText(currentEvent.getDate());
        holder.eventTime.setText(currentEvent.getTime());

        holder.icon.setImageResource(currentEvent.getIcon());

        //holder.icon.setImageResource();
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public static class EventsDataHolder extends RecyclerView.ViewHolder {
        View view;
        ImageView icon;
        TextView eventName;
        TextView eventRegion;
        TextView eventDate;
        TextView eventTime;

        ImageButton delete;

        ImageView circle;

        Button dot;
        ;

        public EventsDataHolder(@NonNull View itemView, Context context, List<Event> events) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.iconEventOverview5);
            eventName = (TextView) itemView.findViewById(R.id.nameEventOverview2);
            eventRegion = (TextView) itemView.findViewById(R.id.regionEventOverview5);
            eventDate = (TextView) itemView.findViewById(R.id.dateEventOverview5);
            eventTime = (TextView) itemView.findViewById(R.id.timeEventOverview5);
            delete = (ImageButton) itemView.findViewById(R.id.editButtonEventOverview5);
            circle = (ImageView) itemView.findViewById(R.id.dateImage5);
            dot = (Button) itemView.findViewById(R.id.eventOverviewButton2);
            view = itemView;

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();

                    Event chosenEvent = events.get(position);
                    events.remove(chosenEvent);

                    Intent intent = new Intent(context, UpdatedEventOverviewActivity.class);
                    context.startActivity(intent);
                }
            });
        }



    }
}
