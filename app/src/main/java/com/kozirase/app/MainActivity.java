package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Button btnApiTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        Gson gson = new Gson();

        /*HeartRate heartRate = new HeartRate("07/28/20 15:57:27",new HeartRate.Value(70,2));
        String json = gson.toJson(heartRate);*/
        String json = "{\n" +
                "  \"dateTime\" : \"07/28/20 15:56:42\",\n" +
                "  \"value\" : {\n" +
                "    \"bpm\" : 70,\n" +
                "    \"confidence\" : 0\n" +
                "  }\n" +
                "}";

        HeartRate heartRate = gson.fromJson(json, HeartRate.class);

        btnApiTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApiConnectionActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initViews() {
        // TODO: init views here
        btnApiTest = findViewById(R.id.button_to_api_test);
    }

}