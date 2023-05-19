package com.example.m3_01_08_reiseplaner.api;

import android.util.Log;

import com.google.gson.JsonObject;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashAPI {
    static final String TAG = "UnsplashAPI";
    static private final String unsplashAccessKey = "rWkcWI2vg9J2_BPDL5Ulb9-bS7-1uttNKGSItMH-9gs";
    static private final String placeHolder = "https://images.unsplash.com/photo-1584974292709-5c2f0619971b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NTAyMDl8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ1MDUwOTZ8&ixlib=rb-4.0.3&q=80&w=1080";
    static private String pictureURL = "";


    /**
     * Returns a random picture URL fitting to that country
     * @param country
     * @param test set true while testing, since we have limited API Calls per hour (50)
     * @return
     */
    static public String getCountryPictureURL(String country, boolean test){
        pictureURL = placeHolder;

        if(test){
            return placeHolder;
        }

        getImageURLFromUnSplash(country);
        return pictureURL;
    }


    /**
     * Calls the Unsplash API and fetches the URL of a random picture that fits the country
     * @param country
     */
    static private void getImageURLFromUnSplash(String country){
        pictureURL =placeHolder;
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        // Create a Retrofit instance
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.unsplash.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        // Create an instance of the UnsplashApi interface
        UnsplashAPIInterface unsplashApi = retrofit.create(UnsplashAPIInterface.class);


        // Make the API call to fetch a random photo from the specified country
        Call<JsonObject> call = unsplashApi.getRandomPhoto(country, unsplashAccessKey);
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject jsonResponse = response.body();
                    if (jsonResponse != null) {
                        String imageUrl = jsonResponse.getAsJsonObject("urls").get("regular").getAsString();

                        // Pass the image URL to the callback function
                        pictureURL = imageUrl;
                        Log.d(TAG, pictureURL);
                    }
                } else {
                    Log.w(TAG, "Error Connecting with UnsplashedAPI");
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Log.w(TAG, "ERROR: Internet Connection");
            }
        });

    }



}
