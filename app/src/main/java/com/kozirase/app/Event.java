package com.kozirase.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "event_table")
public class Event {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String eventName;
    private String firstMember;
    private String secondMember;
    private String thirdMember;
    private String fourthMember;


    public Event(String eventName, String firstMember, String secondMember, String thirdMember, String fourthMember) {
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
