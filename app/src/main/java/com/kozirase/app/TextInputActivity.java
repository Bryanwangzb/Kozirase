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
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TextInputActivity extends AppCompatActivity {

    //Todo: update this activity with kozirase API.
    //Todo: expect dynamically.

    private TextView textViewFirstMemberResult, textViewSecondMemberResult;
    private Button btnGetApiFirstMember, btnGetApiSecondMember;
    private EditText editFirstMemberTextInput, editSecondMemberTextInput;
    private JsonMoodScoreApi jsonMoodScoreApi;
    private String inputFirstMemberContext, inputSecondMemberContext;
    private int memberNumber;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_input);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initView();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://kojipro.an.r.appspot.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        jsonMoodScoreApi = retrofit.create(JsonMoodScoreApi.class);

        btnGetApiFirstMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberNumber = 1;
                getMoods(memberNumber);
            }
        });

        btnGetApiSecondMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                memberNumber = 2;
                getMoods(memberNumber);
            }
        });

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

    @SuppressLint("ShowToast")
    private void getMoods(int memberNumber){
        Map<String,String> parameters = new HashMap<>();
        if(memberNumber==1){
            inputFirstMemberContext = editFirstMemberTextInput.getText().toString();
            parameters.put("text",inputFirstMemberContext);
        }else if(memberNumber==2){
            inputSecondMemberContext = editSecondMemberTextInput.getText().toString();
            parameters.put("text",inputSecondMemberContext);
        }else{
            Toast.makeText(this,"This is not a right member",Toast.LENGTH_LONG);
            parameters.put("text",inputSecondMemberContext);
        }

        Call<Mood> call = jsonMoodScoreApi.getMoods(parameters);

        call.enqueue(new Callback<Mood>() {
            @Override
            public void onResponse(Call<Mood> call, Response<Mood> response) {
                if(!response.isSuccessful()){
                    if(memberNumber==1){
                        textViewFirstMemberResult.setText("Code: " + response.code());
                        return;
                    }else if(memberNumber==2){
                        textViewSecondMemberResult.setText("Code: "+ response.code());
                        return;
                    }
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


                if(memberNumber==1){
                    textViewFirstMemberResult.setText("");
                    textViewFirstMemberResult.append(content);
                }else if(memberNumber==2){
                    textViewSecondMemberResult.setText("");
                    textViewSecondMemberResult.append(content);
                }
            }

            @Override
            public void onFailure(Call<Mood> call, Throwable t) {
                if(memberNumber==1){
                    textViewFirstMemberResult.setText(t.getMessage());
                }else if(memberNumber==2){
                    textViewSecondMemberResult.setText(t.getMessage());
                }
            }
        });


    }

    private void initView() {
        btnGetApiFirstMember = findViewById(R.id.btn_text_analyst_person_1);
        btnGetApiSecondMember = findViewById(R.id.btn_text_analyst_person_2);
        editFirstMemberTextInput = findViewById(R.id.putText_Person1);
        editSecondMemberTextInput = findViewById(R.id.putText_Person2);
        textViewFirstMemberResult = findViewById(R.id.txt_person_1_analyst_result);
        textViewSecondMemberResult = findViewById(R.id.txt_person_2_analyst_result);
    }

}