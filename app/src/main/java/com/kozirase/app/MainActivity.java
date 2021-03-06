package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnApiTest,btnHeartRateProcess,btnTextTest,btnLogout,btnTextAnalysis;

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

        btnHeartRateProcess.setEnabled(true);
        btnHeartRateProcess.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,HeartRateProcessActivity.class);
                startActivity(intent);

            }
        });
        btnTextTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TextInputActivity.class);
                startActivity(intent);
            }
        });

        btnTextAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TextAnalysisActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        // TODO: init views here
        btnApiTest = findViewById(R.id.button_to_api_test);
        btnHeartRateProcess = findViewById(R.id.button_to_heart_rate_process);
        btnTextTest = findViewById(R.id.text_test_button);
        btnLogout = findViewById(R.id.button_to_login_page);
        btnTextAnalysis = findViewById(R.id.text_analysis_button);
    }

}