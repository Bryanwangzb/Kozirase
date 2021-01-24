package com.kozirase.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "events_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //Todo: event time
    private String dateTime;

    private String eventName;
    private String firstMember;
    private String secondMember;
    private String thirdMember;
    private String fourthMember;

    //private ArrayList<String> memberList = new ArrayList<>();


    // Todo: add to 4 members.


    public Event(String dateTime, String eventName, String firstMember, String secondMember, String thirdMember, String fourthMember) {
        this.dateTime = dateTime;
        this.eventName = eventName;
        this.firstMember = firstMember;
        this.secondMember = secondMember;
        this.thirdMember = thirdMember;
        this.fourthMember = fourthMember;
    }

    public void setId(int id){this.id = id;}

    public int getId(){
        return id;
    }

    public String getEventName() {
        return eventName;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getFirstMember() {
        return firstMember;
    }

    public String getSecondMember() {
        return secondMember;
    }

    public String getThirdMember() {
        return thirdMember;
    }

    public String getFourthMember() {
        return fourthMember;
    }
}