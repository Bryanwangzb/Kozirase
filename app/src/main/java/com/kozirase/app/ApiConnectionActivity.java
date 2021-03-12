package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnectionActivity extends AppCompatActivity {
    private TextView textViewResult,textViewTitle;
    private Button btnGetApi;
    private EditText editTextInput;
    // private JsonPlaceHolderApi jsonPlaceHolderApi;
    private JsonMoodScoreApi jsonMoodScoreApi;
    private String inputContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_connection);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kojipro.an.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonMoodScoreApi = retrofit.create(JsonMoodScoreApi.class);

        btnGetApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMoods();

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getMoods() {
        Map<String, String> parameters = new HashMap<>();
        inputContext = editTextInput.getText().toString();
        parameters.put("text", inputContext);
        Call<Mood> call = jsonMoodScoreApi.getMoods(parameters);

        call.enqueue(new Callback<Mood>() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(Call<Mood> call, Response<Mood> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Mood mood = response.body();


                String content = "";
                content += "Excite: " + mood.getExcite() + "\n";
                content += "Pleasant: " + mood.getPleasant() + "\n";
                content += "Calm: " + mood.getCalm() + "\n";
                content += "Nervous: " + mood.getNervous() + "\n";
                content += "Boring: " + mood.getBoring() + "\n";
                content += "Unpleasant: " + mood.getUnpleasant() + "\n";
                content += "Surprise: " + mood.getSurprise() + "\n";
                content += "Sleepy: " + mood.getSleepy() + "\n";
                content += "Myakuari: " + mood.getMyakuari() + "\n\n";
                textViewResult.setText("");
                textViewResult.append(content);
            }
            @Override
            public void onFailure(Call<Mood> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }


    private void initView() {

        textViewResult = findViewById(R.id.text_view_result);
        btnGetApi = findViewById(R.id.btn_api_get);
        editTextInput = findViewById(R.id.edit_text_input);
    }

}