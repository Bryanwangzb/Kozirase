package com.kozirase.app;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.w3c.dom.Text;

import java.util.List;


public class TextAnalysisViewModel extends AndroidViewModel {
    private TextAnalysisRepository repository;
    private LiveData<List<TextAnalysis>> allTextAnalysis;

    public TextAnalysisViewModel(@NonNull Application application) {
        super(application);
        repository = new TextAnalysisRepository(application);
        allTextAnalysis = repository.getAllTextAnalysis();
    }

    public void insert(TextAnalysis textAnalysis){
        repository.insert(textAnalysis);
    }

    public void update(TextAnalysis textAnalysis){
        repository.update(textAnalysis);
    }

    public void delete(TextAnalysis textAnalysis){
        repository.delete(textAnalysis);
    }

    public void deleteAllTextAnalysis(){
        repository.deleteAllTextAnalysis();
    }

    public LiveData<List<TextAnalysis>> getAllTextAnalysis(){
        return allTextAnalysis;
    }
}
