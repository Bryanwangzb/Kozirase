package com.kozirase.app;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Event.class},version=1,exportSchema = false)
public abstract class EventDatabase extends RoomDatabase {

    private static EventDatabase instance;

    public abstract EventDao eventDao();

    public static synchronized EventDatabase getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    EventDatabase.class,"event_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private EventDao eventDao;

        private PopulateDbAsyncTask(EventDatabase db){
            eventDao = db.eventDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            eventDao.insert(new Event("Title 1","Taro","Jiro","Tanaka","Jack"));
            eventDao.insert(new Event("Title 2","Mary","Tom","Ryan","Smith"));
            eventDao.insert(new Event("Title 3","Liu","Zhao","Xu","Hu"));

            return null;
        }


    }



}
