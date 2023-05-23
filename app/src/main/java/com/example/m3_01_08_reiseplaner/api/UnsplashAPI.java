package com.example.m3_01_08_reiseplaner.api;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;

import kotlinx.coroutines.Dispatchers;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UnsplashAPI {
    static final String TAG = "UnsplashAPI";
    static private final String UNSPLASH_ACCESS_KEY = "rWkcWI2vg9J2_BPDL5Ulb9-bS7-1uttNKGSItMH-9gs";
    static private final String PLACEHOLDER = "https://images.unsplash.com/photo-1584974292709-5c2f0619971b?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=M3w0NTAyMDl8MHwxfHJhbmRvbXx8fHx8fHx8fDE2ODQ1MDUwOTZ8&ixlib=rb-4.0.3&q=80&w=1080";
    static private String pictureURL = "";


    /**
     * Returns a random picture URL fitting to that country
     * @param country
     * @param test set true while testing, since we have limited API Calls per hour (50)
     * @return
     */
    static public String getCountryPictureURL(String country, boolean test){
        pictureURL = PLACEHOLDER;

        if(test){
            return PLACEHOLDER;
        }

        return getImageURLFromUnSplash(country);
    }


    /**
     * Calls the Unsplash API and fetches the URL of a random picture that fits the country
     * @param country
     */
    private static String getImageURLFromUnSplash(String country) {
        // Create an AsyncTask to perform the network call on a background thread
        AsyncTask<String, Void, String> task = new AsyncTask<String, Void, String>() {
            @Override
            protected String doInBackground(String... strings) {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

                // Create a Retrofit instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.unsplash.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();

                // Create an instance of the UnsplashApi interface
                UnsplashAPIInterface unsplashApi = retrofit.create(UnsplashAPIInterface.class);

                try {
                    // Make the API call to fetch a random photo from the specified country
                    Call<JsonObject> call = unsplashApi.getRandomPhoto(strings[0], UNSPLASH_ACCESS_KEY);
                    Response<JsonObject> response = call.execute();

                    if (response.isSuccessful()) {
                        JsonObject jsonResponse = response.body();
                        if (jsonResponse != null) {
                            String imageUrl = jsonResponse.getAsJsonObject("urls").get("regular").getAsString();
                            Log.d(TAG, imageUrl);
                            return imageUrl;
                        } else {
                            Log.w(TAG, "Empty response from Unsplash API");
                        }
                    } else {
                        Log.w(TAG, "Error connecting to Unsplash API: " + response.code());
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Error connecting to Unsplash API", e);
                }

                return PLACEHOLDER;
            }
        };

        try {
            // Execute the AsyncTask to perform the network call
            return task.execute(country).get();
        } catch (Exception e) {
            Log.e(TAG, "Error executing AsyncTask", e);
            return PLACEHOLDER;
        }
    }



}
