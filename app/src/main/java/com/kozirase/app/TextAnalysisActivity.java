package com.kozirase.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class TextAnalysisActivity extends AppCompatActivity {

    private TextAnalysisViewModel textAnalysisViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_analysis);

        textAnalysisViewModel = new ViewModelProvider(this).get(TextAnalysisViewModel.class);
        textAnalysisViewModel.getAllTextAnalysis().observe(this, new Observer<List<TextAnalysis>>() {

            // this function is triggered when contents in Live data has changed.
            @Override
            public void onChanged(List<TextAnalysis> textAnalyses) {
                //update RecyclerView
                Toast.makeText(TextAnalysisActivity.this,"onChanged",Toast.LENGTH_SHORT).show();
            }
        });

    }


}