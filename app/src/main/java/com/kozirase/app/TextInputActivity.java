package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TextInputActivity extends AppCompatActivity {

    private Button btnSaveText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);

        initViews();

        btnSaveText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TextInputActivity.this, FinalScoreActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initViews() {
        btnSaveText = findViewById(R.id.button_to_score_page);
    }
}