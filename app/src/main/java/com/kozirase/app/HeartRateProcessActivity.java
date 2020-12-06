package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.gson.Gson;

public class HeartRateProcessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_process);

        //HeartRate heartRate = new HeartRate("07/28/20 15:57:27",new HeartRate.Value(70,2));
        //String json = gson.toJson(heartRate);

        Gson gson = new Gson();

        String json = "{\n" +
                "  \"dateTime\" : \"07/28/20 15:56:42\",\n" +
                "  \"value\" : {\n" +
                "    \"bpm\" : 70,\n" +
                "    \"confidence\" : 0\n" +
                "  }\n" +
                "}";

        HeartRate heartRate = gson.fromJson(json, HeartRate.class);
        // For testing gson function.
        System.out.println("*********************************");
        System.out.println(heartRate.getDateTime());
        System.out.println(heartRate.getValue().getBpm());
        System.out.println(heartRate.getValue().getConfidence());

    }
}