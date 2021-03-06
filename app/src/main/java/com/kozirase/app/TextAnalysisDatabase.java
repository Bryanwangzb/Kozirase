package com.kozirase.app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TextAnalysis.class},version = 1)
public abstract class TextAnalysisDatabase extends RoomDatabase {

    private static TextAnalysisDatabase instance;

    public abstract TextAnalysisDao textAnalysisDao();

    public static  synchronized TextAnalysisDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TextAnalysisDatabase.class,"text_analysis_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
