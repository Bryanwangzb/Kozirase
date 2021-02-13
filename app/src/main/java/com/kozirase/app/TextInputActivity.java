package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextInputActivity extends AppCompatActivity {

    //Todo: update this activity with kozirase API.
    //Todo: expect dynamically.

    private Button btnGetApiFirstMember, btnGetApiSecondMember;
    private EditText editTextInputFirstMember, editTextInputSecondMember;
    private JsonMoodScoreApi jsonMoodScoreApi;
    private String inputContextFirstMember, inputContextSecondMember;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kojipro.an.r.appsot.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        btnGetApiFirstMember = findViewById(R.id.btn_text_analyst_person_1);
        btnGetApiSecondMember = findViewById(R.id.btn_text_analyst_person_2);
        editTextInputFirstMember = findViewById(R.id.putText_Person_1);
        editTextInputSecondMember = findViewById(R.id.putText_Person_2);
    }

}