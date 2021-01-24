package com.kozirase.app;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.ArrayList;

@Database(entities = {Event.class}, version = 2, exportSchema = false)
public abstract class EventDatabase extends RoomDatabase {

    private static EventDatabase instance;

    public abstract EventDao eventDao();


    public static synchronized EventDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class, "events_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private EventDao eventDao;

        private PopulateDbAsyncTask(EventDatabase db) {
            eventDao = db.eventDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //eventDao.insert(new Event("Title Test4", ""));

            eventDao.insert(new Event("11-19 18:00","Title Test","Memeber 1","Member 2","Member 3","Member 4"));
//            eventDao.insert(new Event("Title Test2", ""));
//            eventDao.insert(new Event("Title Test3", ""));
            return null;
        }
    }

}