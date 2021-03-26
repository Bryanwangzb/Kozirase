package com.kozirase.app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class TextAnalysisActivity extends AppCompatActivity {

    private TextAnalysisViewModel textAnalysisViewModel;
    public static final int ADD_TEXT_ANALYSIS_REQUEST = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_analysis);

        FloatingActionButton buttonAddTextAnalysis = findViewById(R.id.btn_add_text_analysis);
        buttonAddTextAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TextAnalysisActivity.this,AddTextAnalysisActivity.class);
                startActivityForResult(intent,ADD_TEXT_ANALYSIS_REQUEST);

            }
        });

        RecyclerView recyclerView = findViewById(R.id.recycler_view_text_analysis);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        TextAnalysisAdapter adapter = new TextAnalysisAdapter();
        recyclerView.setAdapter(adapter);

        textAnalysisViewModel = new ViewModelProvider(this).get(TextAnalysisViewModel.class);
        textAnalysisViewModel.getAllTextAnalysis().observe(this, new Observer<List<TextAnalysis>>() {

            // this function is triggered when contents in Live data has changed.
            @Override
            public void onChanged(List<TextAnalysis> textAnalyses) {
                //update RecyclerView
                adapter.setTextAnalysis(textAnalyses);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT| ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                textAnalysisViewModel.delete(adapter.getTextAnalysis(viewHolder.getAdapterPosition()));
                Toast.makeText(TextAnalysisActivity.this,"文書分析が削除されました",Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==ADD_TEXT_ANALYSIS_REQUEST && resultCode == RESULT_OK){
            String member = data.getStringExtra(AddTextAnalysisActivity.EXTRA_MEMBER);
            String dialogue = data.getStringExtra(AddTextAnalysisActivity.EXTRA_DIALOGUE);
            String result = data.getStringExtra(AddTextAnalysisActivity.EXTRA_RESULT);

            TextAnalysis textAnalysis = new TextAnalysis(member,dialogue,result);
            textAnalysisViewModel.insert(textAnalysis);

            Toast.makeText(this,"分析結果を保存した",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"分析結果を保存できません",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.text_analysis_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.delete_all_text_analysis:
                textAnalysisViewModel.deleteAllTextAnalysis();
                Toast.makeText(this,"文書分析が全部削除されました",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
}