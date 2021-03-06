package com.kozirase.app;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TextAnalysisRepository {
    private TextAnalysisDao textAnalysisDao;
    private LiveData<List<TextAnalysis>> allTextAnalysis;

    public TextAnalysisRepository(Application application){
        TextAnalysisDatabase database = TextAnalysisDatabase.getInstance(application);
        textAnalysisDao = database.textAnalysisDao();
        allTextAnalysis = textAnalysisDao.getAllTextAnalysis();
    }

    public void insert(TextAnalysis textAnalysis){

    }

    public void update(TextAnalysis textAnalysis){

    }

    public void delete(TextAnalysis textAnalysis){

    }

    public void deleteAllTextAnalysis(){

    }

    public LiveData<List<TextAnalysis>> getAllTextAnalysis(){
        return allTextAnalysis;
    }




}
