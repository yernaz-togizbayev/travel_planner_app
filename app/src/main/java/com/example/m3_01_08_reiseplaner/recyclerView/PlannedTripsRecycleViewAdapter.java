package com.example.m3_01_08_reiseplaner.recyclerView;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_01_08_reiseplaner.EventOverviewActivity;
import com.example.m3_01_08_reiseplaner.R;
import com.example.m3_01_08_reiseplaner.travelDataStructures.PlannedTrip;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class PlannedTripsRecycleViewAdapter  extends RecyclerView.Adapter<PlannedTripsRecycleViewAdapter.CardDataHolder>{


    private final static String TAG = "PlannedTripsRecycleViewAdapter";

    private Context context;
    private List<PlannedTrip> plannedTrips = new ArrayList<PlannedTrip>();

    public PlannedTripsRecycleViewAdapter(Context context, List<PlannedTrip> plannedTravels){
        this.context = context;
        this.plannedTrips = plannedTravels;
    }


    /**
     * Connects the dynamic view with an activity
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return
     */
    @NonNull
    @Override
    public PlannedTripsRecycleViewAdapter.CardDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.travel_destination_card, parent, false);
        return new PlannedTripsRecycleViewAdapter.CardDataHolder(view, context, plannedTrips);
    }


    /**
     * Sets a single card int the view with its data
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull PlannedTripsRecycleViewAdapter.CardDataHolder holder, int position) {
        PlannedTrip currentTravel = plannedTrips.get(position);

        float density = context.getResources().getDisplayMetrics().density;
        int widthInDp = 157;    // Desired width in DP
        int heightInDp = 150;   // Desired height in DP

        int widthInPixels = (int) (widthInDp * density);
        int heightInPixels = (int) (heightInDp * density);

        Picasso.get().load(currentTravel.getPictureUrl()).resize(widthInPixels,heightInPixels).placeholder(R.drawable.loading).error(R.drawable.no_pictures_found).into(holder.countryImageView);
        holder.countryTextView.setText(currentTravel.getTripDestinationCountry());
        holder.cityTextView.setText(currentTravel.getTripDestinationCity());
        holder.daysTillTravelTextView.setText(currentTravel.getDaysTillTravelStart());
        holder.nextEventTextView.setText(currentTravel.getFirstEvent());

    }

    @Override
    public int getItemCount() {
        return plannedTrips.size();
    }

    /**
     * Acts like a struct and stores the Data of a card
     * The class has to be static and the variables are public,
     * so that they are easy accessable like a struct in C++
     */
    public static class CardDataHolder extends RecyclerView.ViewHolder{

        ImageView countryImageView;


        TextView countryTextView;

        TextView cityTextView;
        TextView daysTillTravelTextView;

        TextView nextEventTextView;


        Button viewEventsButton;
        ImageButton deleteButton;

        public CardDataHolder(@NonNull View plannedTravelView, Context context, List<PlannedTrip> plannedTrips) {
            super(plannedTravelView);
            countryImageView = plannedTravelView.findViewById(R.id.countryImageView);
            countryTextView = plannedTravelView.findViewById(R.id.countryTextView);
            cityTextView = plannedTravelView.findViewById(R.id.cityTextView);

            daysTillTravelTextView = plannedTravelView.findViewById(R.id.dayCounterView);
            nextEventTextView = plannedTravelView.findViewById(R.id.eventTextView);

            viewEventsButton = plannedTravelView.findViewById(R.id.tripDetailsbutton);
            deleteButton = plannedTravelView.findViewById(R.id.deleteButton);


            viewEventsButton.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position == RecyclerView.NO_POSITION){
                        Log.w(TAG, "NO POSITION");
                        return;
                    }

                    PlannedTrip chosenTrip = plannedTrips.get(position);


                    EventOverviewActivity.setEvents(EventOverviewActivity.getEventMap().get(chosenTrip.getId()));
                    EventOverviewActivity.setId(chosenTrip.getId());
                    EventOverviewActivity.setDestination(chosenTrip.getTripDestinationCity());

                    Intent intent = new Intent(context, EventOverviewActivity.class);
                    context.startActivity(intent);
                }
            });




        }
    }
}
