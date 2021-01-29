package com.kozirase.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity(tableName = "events_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    //Todo: event time
    private int eventHour;
    private int eventMinute;

    private String eventName;
    private String firstMember;
    private String secondMember;
    private String thirdMember;
    private String fourthMember;

    //private ArrayList<String> memberList = new ArrayList<>();


    // Todo: add to 4 members.


    public Event(int eventHour,int eventMinute, String eventName, String firstMember, String secondMember, String thirdMember, String fourthMember) {
        this.eventHour = eventHour;
        this.eventMinute  = eventMinute;
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

    public int getEventHour() {
        return eventHour;
    }

    public int getEventMinute() {
        return eventMinute;
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