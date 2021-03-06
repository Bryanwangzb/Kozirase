package com.kozirase.app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TextAnalysis.class},version = 1,exportSchema = false)
public abstract class TextAnalysisDatabase extends RoomDatabase {

    private static TextAnalysisDatabase instance;

    public abstract TextAnalysisDao textAnalysisDao();

    public static  synchronized TextAnalysisDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TextAnalysisDatabase.class,"text_analysis_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)  // run callback and populate db task.
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        // the onCreate method only runs when creating the database, while the open runs every time when opening database
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private TextAnalysisDao textAnalysisDao;

        private PopulateDbAsyncTask(TextAnalysisDatabase db){
            textAnalysisDao = db.textAnalysisDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            textAnalysisDao.insert(new TextAnalysis("Tom","It's a nice day!\nIt's a nice day\nIt's a nice day!\nIt's a nice day!\nIt's a nice day!\n","\"excite\": \"0.07\"\n, \"pleasant\": \"0.55\"\n, \"calm\": \"0.02\"\n, \"nervous\": \"0.40\"\n, \"boring\": \"0.02\"\n, \"unpleasant\": \"0.04\"\n, \"surprise\": \"0.04\"\n, \"sleepy\": \"0.00\"\n, \"myakuari\": \"0.12\"\n"));
            textAnalysisDao.insert(new TextAnalysis("Jack","It's a normal day!\nIt's a normal day!\nIt's a normal day!\nIt's a normal day!\nIt's a normal day!\nIt's a normal day!\nIt's a normal day!\n","Well"));
            textAnalysisDao.insert(new TextAnalysis("Sam","It's a bad day!It's a bad day!It's a bad day!It's a bad day!It's a bad day!It's a bad day!","Sad"));
            return null;
        }
    }
}
