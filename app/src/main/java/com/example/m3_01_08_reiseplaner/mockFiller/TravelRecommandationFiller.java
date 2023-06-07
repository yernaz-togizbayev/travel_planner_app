package com.example.m3_01_08_reiseplaner.mockFiller;

import com.example.m3_01_08_reiseplaner.R;
import com.example.m3_01_08_reiseplaner.enums.ETransportation;
import com.example.m3_01_08_reiseplaner.enums.ETravelPreference;
import com.example.m3_01_08_reiseplaner.travelDataStructures.Transport;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelInformation;
import com.example.m3_01_08_reiseplaner.travelDataStructures.TravelRecommendation;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TravelRecommandationFiller {

    private static Transport train = new Transport(ETransportation.TRAIN, R.drawable.oebb_logo, "Train Station" );
    private static Transport bus = new Transport(ETransportation.BUS, R.drawable.flixbus_logo, "Bus Station");
    private static Transport plane = new Transport(ETransportation.PLANE, R.drawable.austrian_logo, "Airport");
    private static Transport ship = new Transport(ETransportation.SHIP, R.drawable.aida_logo, "Harbor");


    /**
     * Generates a Mock of a List of travel Recommendations
     * influenced by the travel Preference
     * @param information
     * @return
     */
    public static List<TravelRecommendation> initialiseRecommendations(TravelInformation information){
        ETravelPreference preference = information.getPreference();
        if(preference.equals(ETravelPreference.ECOLOGICALLY)){
            return generateEcologicalList(information);
        }
        if(preference.equals(ETravelPreference.FAST)){
            return generateFastList(information);
        }

        return generateCheapList(information);
    }

    /**
     * Generates TravelRecommendation List with ecological preference
     * @param information
     * @return
     */
    private static List<TravelRecommendation> generateEcologicalList(TravelInformation information){

        List<TravelRecommendation> recommendations = new ArrayList<TravelRecommendation>();

        LocalTime travelOneDepatureTime = LocalTime.of(8,0);
        LocalTime travelOneReturnTime = LocalTime.of(4, 0);
        String travelOnePrice = "600.00€";
        String travelOneDuration = "6 hours";

        TravelRecommendation travelOne = new TravelRecommendation(train, information, travelOneDepatureTime, travelOneReturnTime, travelOnePrice, travelOneDuration);


        LocalTime travelTwoDepatureTime = LocalTime.of(12,30);
        LocalTime travelTwoReturnTime = LocalTime.of(18, 45);
        String travelTwoPrice = "300.00€";
        String travelTwoDuration = "20 hours";

        TravelRecommendation travelTwo = new TravelRecommendation(bus, information, travelTwoDepatureTime, travelTwoReturnTime, travelTwoPrice, travelTwoDuration);


        LocalTime travelThreeDepatureTime = LocalTime.of(3,15);
        LocalTime travelThreeReturnTime = LocalTime.of(4, 30);
        String travelThreePrice = "400.00€";
        String travelThreeDuration = "50 hours";

        TravelRecommendation travelThree = new TravelRecommendation(ship, information, travelThreeDepatureTime, travelThreeReturnTime, travelThreePrice, travelThreeDuration);


        recommendations.add(travelThree);
        recommendations.add(travelOne);
        recommendations.add(travelTwo);


        return recommendations;
    }


    /**
     * Generates TravelRecommendation List with fast preference
     * @param information
     * @return
     */
    private static List<TravelRecommendation> generateFastList(TravelInformation information){
        List<TravelRecommendation> recommendations = new ArrayList<TravelRecommendation>();

        LocalTime travelOneDepatureTime = LocalTime.of(10,0);
        LocalTime travelOneReturnTime = LocalTime.of(13, 0);
        String travelOnePrice = "400.00€";
        String travelOneDuration = "2 hours";

        TravelRecommendation travelOne = new TravelRecommendation(plane, information, travelOneDepatureTime, travelOneReturnTime, travelOnePrice, travelOneDuration);


        LocalTime travelTwoDepatureTime = LocalTime.of(5,20);
        LocalTime travelTwoReturnTime = LocalTime.of(8, 15);
        String travelTwoPrice = "300.00€";
        String travelTwoDuration = "3 hours";

        TravelRecommendation travelTwo = new TravelRecommendation(plane, information, travelTwoDepatureTime, travelTwoReturnTime, travelTwoPrice, travelTwoDuration);


        LocalTime travelThreeDepatureTime = LocalTime.of(2,15);
        LocalTime travelThreeReturnTime = LocalTime.of(4, 30);
        String travelThreePrice = "400.00€";
        String travelThreeDuration = "5 hours";

        TravelRecommendation travelThree = new TravelRecommendation(train, information, travelThreeDepatureTime, travelThreeReturnTime, travelThreePrice, travelThreeDuration);


        recommendations.add(travelOne);
        recommendations.add(travelTwo);
        recommendations.add(travelThree);

        return recommendations;
    }


    /**
     * Generates TravelRecommendation List with cheap preference
     * @param information
     * @return
     */
    private static List<TravelRecommendation> generateCheapList(TravelInformation information){
        List<TravelRecommendation> recommendations = new ArrayList<TravelRecommendation>();

        LocalTime travelOneDepatureTime = LocalTime.of(3,0);
        LocalTime travelOneReturnTime = LocalTime.of(4, 0);
        String travelOnePrice = "50.00€";
        String travelOneDuration = "30 hours";

        TravelRecommendation travelOne = new TravelRecommendation(plane, information, travelOneDepatureTime, travelOneReturnTime, travelOnePrice, travelOneDuration);


        LocalTime travelTwoDepatureTime = LocalTime.of(12,40);
        LocalTime travelTwoReturnTime = LocalTime.of(18, 45);
        String travelTwoPrice = "80.00€";
        String travelTwoDuration = "350 hours";

        TravelRecommendation travelTwo = new TravelRecommendation(train, information, travelTwoDepatureTime, travelTwoReturnTime, travelTwoPrice, travelTwoDuration);


        LocalTime travelThreeDepatureTime = LocalTime.of(13,15);
        LocalTime travelThreeReturnTime = LocalTime.of(14, 30);
        String travelThreePrice = "30.00€";
        String travelThreeDuration = "500 hours";

        TravelRecommendation travelThree = new TravelRecommendation(bus, information, travelThreeDepatureTime, travelThreeReturnTime, travelThreePrice, travelThreeDuration);


        recommendations.add(travelThree);
        recommendations.add(travelOne);
        recommendations.add(travelTwo);


        return recommendations;
    }

}
