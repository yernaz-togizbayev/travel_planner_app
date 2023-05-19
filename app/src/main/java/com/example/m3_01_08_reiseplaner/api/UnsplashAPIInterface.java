package com.example.m3_01_08_reiseplaner.api;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface UnsplashAPIInterface {
    @GET("/photos/random")
    Call<JsonObject> getRandomPhoto(
            @Query("query") String query,
            @Query("client_id") String accessKey
    );
}
