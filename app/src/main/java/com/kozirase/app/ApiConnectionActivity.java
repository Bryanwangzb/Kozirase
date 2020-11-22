package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    private TextView textViewResult;
    private Button btnGetApi;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    private JsonMoodScoreApi jsonMoodScoreApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_connection);

        initView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kojipro.an.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        jsonMoodScoreApi = retrofit.create(JsonMoodScoreApi.class);

        btnGetApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //getPosts();
                getMoods();

            }
        });

    }

    private void getMoods() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("text", "うれしい");
        Call<Mood> call = jsonMoodScoreApi.getMoods(parameters);

        call.enqueue(new Callback<Mood>() {
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
                content += "Sleepy: " + mood.getSleepy() + "\n\n";

                textViewResult.append(content);
            }

            @Override
            public void onFailure(Call<Mood> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void getPosts() {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("userId", "1");
        parameters.put("_sort", "id");
        parameters.put("_order", "desc");


        //  Call<List<Post>> call = jsonPlaceHolderApi.getPosts(new Integer[]{2,3,6},null, null);
        Call<List<Post>> call = jsonPlaceHolderApi.getPosts(parameters);


        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code:" + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += "ID: " + post.getId() + "\n";
                    content += "User ID:" + post.getUserId() + "\n";
                    content += "Title: " + post.getTitle() + "\n";
                    content += "Text:" + post.getText() + "\n\n";

                    textViewResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }

    private void initView() {

        textViewResult = findViewById(R.id.text_view_result);
        btnGetApi = findViewById(R.id.btn_api_get);
    }

}