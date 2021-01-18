package com.kozirase.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "events_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventName;
    private String firstMember;


    public Event(String eventName, String firstMember) {
        this.eventName = eventName;
        this.firstMember = firstMember;

    }

    public void setId(int id){this.id = id;}

    public int getId(){
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getFirstMember() {
        return firstMember;
    }




}