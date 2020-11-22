package com.kozirase.app;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface JsonMoodScoreApi {
    @GET("getscore")
    Call<Mood> getMoods(@Query("text") String textContent);


}
