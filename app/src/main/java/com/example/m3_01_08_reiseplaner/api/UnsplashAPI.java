package com.example.m3_01_08_reiseplaner.api;

import android.util.Log;

import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(() -> {
            try {
                OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

                // Create a Retrofit instance
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.unsplash.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build();

                // Create an instance of the UnsplashApi interface
                UnsplashAPIInterface unsplashApi = retrofit.create(UnsplashAPIInterface.class);

                // Make the API call to fetch a random photo from the specified country using coroutines
                try {
                    JsonObject response = unsplashApi.getRandomPhoto(country, UNSPLASH_ACCESS_KEY).execute().body();
                    if (response != null) {
                        String imageUrl = response.getAsJsonObject("urls").get("regular").getAsString();
                        Log.d(TAG, imageUrl);
                        return imageUrl;
                    } else {
                        Log.w(TAG, "Empty response from Unsplash API");
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error connecting to Unsplash API", e);
                }

                return PLACEHOLDER;

            } catch (Exception e) {
                System.out.println("Connection failed!");
                e.printStackTrace();
                return null;
            }

        });

        try {

            //Get the result from the thread
            String result = future.get();
            if (result != null) {
                Log.d(TAG, "Result: " + result);
                return result;
            } else {
                Log.w(TAG, "API Call failed cause Result is null.");
                return PLACEHOLDER;
            }
        } catch (Exception e) {
            Log.w(TAG, "Exception caught while making API Call to Unsplashed");
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
        return PLACEHOLDER;
    }



}
