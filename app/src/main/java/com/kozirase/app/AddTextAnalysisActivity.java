package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class AddTextAnalysisActivity extends AppCompatActivity {

    private EditText editTextMember;
    private EditText editTextDialogue;
    private TextView textViewTextAnalysisResult;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_text_analysis);

        editTextMember = findViewById(R.id.edit_text_analysis_member_name);
        editTextDialogue = findViewById(R.id.edit_text_analysis_dialogue);
        textViewTextAnalysisResult = findViewById(R.id.text_text_analysis_result);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Text Analysis");

    }

    private void saveTextAnalysis(){
        String member = editTextMember.getText().toString();
        String dialogue = editTextDialogue.getText().toString();
        String result = textViewTextAnalysisResult.toString();

        if(member.trim().isEmpty()||dialogue.trim().isEmpty()||result.isEmpty()){
            Toast.makeText(this,"Please insert a member, ",Toast.LENGTH_LONG);
            return;
        }



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
}