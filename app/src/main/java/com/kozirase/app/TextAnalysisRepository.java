package com.kozirase.app;

import android.app.Application;
import android.os.AsyncTask;

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

    // these method are APIs for ViewModel
    public void insert(TextAnalysis textAnalysis){
        new InsertTextAnalysisAsyncTask(textAnalysisDao).execute(textAnalysis);
    }

    public void update(TextAnalysis textAnalysis){
        new UpdateTextAnalysisAsyncTask(textAnalysisDao).execute(textAnalysis);
    }

    public void delete(TextAnalysis textAnalysis){
        new DeleteTextAnalysisAsyncTask(textAnalysisDao).execute(textAnalysis);
    }

    public void deleteAllTextAnalysis(){
        new DeleteAllTextAnalysisAsyncTask(textAnalysisDao).execute();
    }

    public LiveData<List<TextAnalysis>> getAllTextAnalysis(){
        return allTextAnalysis;
    }

    private static class InsertTextAnalysisAsyncTask extends AsyncTask<TextAnalysis,Void,Void>{
        private TextAnalysisDao textAnalysisDao;

        // Constructor
        private InsertTextAnalysisAsyncTask(TextAnalysisDao textAnalysisDao){
            this.textAnalysisDao = textAnalysisDao;
        }

        @Override
        protected Void doInBackground(TextAnalysis... textAnalyses) {
            textAnalysisDao.insert(textAnalyses[0]);
            return null;
        }
    }

    private static class UpdateTextAnalysisAsyncTask extends AsyncTask<TextAnalysis,Void,Void>{
        private TextAnalysisDao textAnalysisDao;

        private UpdateTextAnalysisAsyncTask(TextAnalysisDao textAnalysisDao){
            this.textAnalysisDao = textAnalysisDao;
        }

        @Override
        protected Void doInBackground(TextAnalysis... textAnalyses) {
            textAnalysisDao.update(textAnalyses[0]);
            return null;
        }
    }

    private static class DeleteTextAnalysisAsyncTask extends AsyncTask<TextAnalysis,Void,Void>{
        private TextAnalysisDao textAnalysisDao;

        private DeleteTextAnalysisAsyncTask(TextAnalysisDao textAnalysisDao){
            this.textAnalysisDao = textAnalysisDao;
        }

        @Override
        protected Void doInBackground(TextAnalysis... textAnalyses) {
            textAnalysisDao.delete(textAnalyses[0]);
            return null;
        }
    }

    private static class DeleteAllTextAnalysisAsyncTask extends AsyncTask<Void,Void,Void>{
        private TextAnalysisDao textAnalysisDao;

        private DeleteAllTextAnalysisAsyncTask(TextAnalysisDao textAnalysisDao){
            this.textAnalysisDao = textAnalysisDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            textAnalysisDao.deleteAllTextAnalysis();
            return null;
        }
    }





}
