package com.example.m3_01_08_reiseplaner.recyclerView;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.m3_01_08_reiseplaner.MainMenuActivity;
import com.example.m3_01_08_reiseplaner.R;
import com.example.m3_01_08_reiseplaner.enums.ETransportation;
import com.example.m3_01_08_reiseplaner.inputValidation.PopUpMessage;
import com.example.m3_01_08_reiseplaner.staticDataStorer.StoredTravels;
import com.example.m3_01_08_reiseplaner.travelDataStructures.PlannedTrip;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelRecommendation;

import java.util.ArrayList;
import java.util.List;

public class TravelRecommendationRecycleViewAdapter extends RecyclerView.Adapter<TravelRecommendationRecycleViewAdapter.CardDataHolder> {


    private final static String TAG = "TravelRecommendationRecycleViewAdapter";

    private Context context;
    private List<TravelRecommendation> recommendations = new ArrayList<TravelRecommendation>();

    public TravelRecommendationRecycleViewAdapter(Context context, List<TravelRecommendation> recommendations){
        this.context = context;
        this.recommendations = recommendations;
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
    public CardDataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.travel_recommendation_card, parent, false);
        return new CardDataHolder(view, context, recommendations);
    }


    /**
     * Sets a single card int the view with its data
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull CardDataHolder holder, int position) {
        TravelRecommendation currentRecommendation = recommendations.get(position);

        holder.logoView.setImageResource(currentRecommendation.getDrawableID());
        holder.destinationView.setText(currentRecommendation.getLocation());

        holder.depatureDateView.setText(currentRecommendation.getStartDateAsString());
        holder.depatureTimeView.setText(currentRecommendation.getDepatureTimeAsString());
        holder.arrivalDateView.setText(currentRecommendation.getReturnDateAsString());
        holder.arrivalTimeView.setText(currentRecommendation.getReturnTimeAsString());
        holder.costView.setText(currentRecommendation.getPrice());
        holder.travelDurationView.setText(currentRecommendation.getTravelTime());

        setSymbol(currentRecommendation.getTransportation(), holder);


    }


    private void setSymbol(ETransportation transportType, CardDataHolder holder){
        if(transportType.equals(ETransportation.BUS)){
                holder.symbolView.setImageResource(R.drawable.ic_bus);
                return;
        }

        if(transportType.equals(ETransportation.TRAIN)){
                holder.symbolView.setImageResource(R.drawable.ic_train);
                return;
        }

        if(transportType.equals(ETransportation.SHIP)){
            holder.symbolView.setImageResource(R.drawable.ic_ship);
            return;
        }

        holder.symbolView.setImageResource(R.drawable.ic_plane);


    }

    @Override
    public int getItemCount() {
        return recommendations.size();
    }



    /**
     * Acts like a struct and stores the Data of a card
     * The class has to be static and the variables are public,
     * so that they are easy accessable like a struct in C++
     */
    public static class CardDataHolder extends RecyclerView.ViewHolder{

        ImageView logoView;
        ImageView symbolView;

        TextView destinationView;

        TextView depatureDateView;
        TextView depatureTimeView;

        TextView arrivalDateView;
        TextView arrivalTimeView;

        TextView costView;
        TextView travelDurationView;






        Button bookTravelButton;

        public CardDataHolder(@NonNull View travelRecommendationView, Context context, List<TravelRecommendation> recommendations) {
            super(travelRecommendationView);
            logoView = travelRecommendationView.findViewById(R.id.logoImageView);
            symbolView = travelRecommendationView.findViewById(R.id.symbolImageView);
            destinationView = travelRecommendationView.findViewById(R.id.destinationTextView);

            depatureDateView = travelRecommendationView.findViewById(R.id.depatureDateTextView);
            depatureTimeView = travelRecommendationView.findViewById(R.id.depatureTimeTextView);

            arrivalDateView = travelRecommendationView.findViewById(R.id.arrivalDateTextView);
            arrivalTimeView = travelRecommendationView.findViewById(R.id.arrivalTimeTextView);

            costView = travelRecommendationView.findViewById(R.id.costTextView);
            travelDurationView = travelRecommendationView.findViewById(R.id.travelDurationTextView);

            bookTravelButton = travelRecommendationView.findViewById(R.id.bookTravelButton);

            bookTravelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if(position == RecyclerView.NO_POSITION){
                        Log.w(TAG, "NO POSITION");
                        return;
                    }



                    TravelRecommendation chosenRecommendation = recommendations.get(position);

                    String bookedMessage = "Travel to " + chosenRecommendation.getInformation().getDestinationCountry() +
                            " was successfully booked!";
                    PopUpMessage.showAcceptPopUpMessage(bookedMessage, context);

                    Log.d(TAG, chosenRecommendation.toString());

                    PlannedTrip chosenTrip = new PlannedTrip(chosenRecommendation);

                    StoredTravels.addTrip(chosenTrip);

                    Intent intent = new Intent(context, MainMenuActivity.class);
                    context.startActivity(intent);
                }
            });


        }
    }
}
