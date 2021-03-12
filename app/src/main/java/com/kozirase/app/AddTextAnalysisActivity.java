package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddTextAnalysisActivity extends AppCompatActivity {

    private EditText editTextMember;
    private EditText editTextDialogue;
    private TextView textViewTextAnalysisResult;
    private Button btnGetApi;
    private JsonMoodScoreApi jsonMoodScoreApi;
    private String textAnalysisContext;
    public static final String EXTRA_MEMBER =
            "com.kozirase.app.EXTRA_MEMBER";
    public static final String EXTRA_DIALOGUE =
            "com.kozirase.app.EXTRA_DIALOGUE";
    public static final String EXTRA_RESULT =
            "com.kozirase.app.EXTRA_RESULT";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text_analysis);

        initView();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kojipro.an.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonMoodScoreApi = retrofit.create(JsonMoodScoreApi.class);
        btnGetApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMoods();
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("会話分析追加");

    }

    private void saveTextAnalysis() {
        String member = editTextMember.getText().toString();
        String dialogue = editTextDialogue.getText().toString();
        String result = textViewTextAnalysisResult.getText().toString();

        if (member.trim().isEmpty() || dialogue.trim().isEmpty() || result.isEmpty()) {
            Toast.makeText(this, "Please insert a member, ", Toast.LENGTH_LONG);
            return;
        }

        // send data to activity which start this one
        Intent data = new Intent();
        data.putExtra(EXTRA_MEMBER,member);
        data.putExtra(EXTRA_DIALOGUE,dialogue);
        data.putExtra(EXTRA_RESULT,result);

        setResult(RESULT_OK,data);
        finish();

    }

    //get save menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_text_analysis_menu, menu);
        return true; // means we will dismiss the menu
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_text_analysis:
                // Todo: Need add a check whether the result is get.
                saveTextAnalysis();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void getMoods() {
        Map<String, String> parameters = new HashMap<>();
        textAnalysisContext = editTextDialogue.getText().toString();
        parameters.put("text", textAnalysisContext);

        Call<Mood> call = jsonMoodScoreApi.getMoods(parameters);

        call.enqueue(new Callback<Mood>() {
            @Override
            public void onResponse(Call<Mood> call, @NotNull Response<Mood> response) {
                if (!response.isSuccessful()) {
                    textViewTextAnalysisResult.setText("Code: " + response.code());
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

                textViewTextAnalysisResult.setText("");
                textViewTextAnalysisResult.append(content);


            }

            @Override
            public void onFailure(Call<Mood> call, Throwable t) {
                textViewTextAnalysisResult.setText(t.getMessage());
            }
        });

    }

    private void initView(){
        editTextMember = findViewById(R.id.edit_text_analysis_member_name);
        editTextDialogue = findViewById(R.id.edit_text_analysis_dialogue);
        textViewTextAnalysisResult = findViewById(R.id.text_text_analysis_result);
        btnGetApi = findViewById(R.id.btn_text_analyst);

    }
}