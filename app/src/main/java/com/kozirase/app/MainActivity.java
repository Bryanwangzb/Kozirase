package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {

    private Button btnApiTest,btnHeartRateProcess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        btnApiTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ApiConnectionActivity.class);
                startActivity(intent);
            }
        });
        //Todo: heart rate process activity on developing.
        btnHeartRateProcess.setEnabled(true);
        btnHeartRateProcess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HeartRateProcessActivity.class);
                startActivity(intent);
            }
        });


    }

    private void initViews() {
        // TODO: init views here
        btnApiTest = findViewById(R.id.button_to_api_test);
        btnHeartRateProcess = findViewById(R.id.button_to_heart_rate_process);
    }

}