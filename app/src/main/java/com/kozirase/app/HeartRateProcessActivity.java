package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

public class HeartRateProcessActivity extends AppCompatActivity {
    List<String> timeList;
    List<Integer> heartRatesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate_process);

        //HeartRate heartRate = new HeartRate("07/28/20 15:57:27",new HeartRate.Value(70,2));
        //String json = gson.toJson(heartRate);

        String jsonFileString = Utils.getJsonFromAssets(getApplicationContext(), "heart_rate-2020-07029.json");
        //Log.i("data", jsonFileString);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<HeartRate>>() {
        }.getType();

        List<HeartRate> heartRates = gson.fromJson(jsonFileString, listUserType);
        System.out.println("The item's amount is:" + heartRates.size());

//        for (int i = 0; i < heartRates.size(); i++) {
//            Log.i("data", "> Item" + i + "\n" +
//                    "Time: " + heartRates.get(i).getDateTime() + "\n" +
//                    "HeartRate: " + heartRates.get(i).getValue().getBpm() + "\n");
//        }

//        String json = "{\n" +
//                "  \"dateTime\" : \"07/28/20 15:56:42\",\n" +
//                "  \"value\" : {\n" +
//                "    \"bpm\" : 70,\n" +
//                "    \"confidence\" : 0\n" +
//                "  }\n" +
//                "}";
//
//        HeartRate heartRate = gson.fromJson(json, HeartRate.class);
//        // For testing gson function.
//        System.out.println("*********************************");
//        System.out.println(heartRate.getDateTime());
//        System.out.println(heartRate.getValue().getBpm());
//        System.out.println(heartRate.getValue().getConfidence());


    }
}