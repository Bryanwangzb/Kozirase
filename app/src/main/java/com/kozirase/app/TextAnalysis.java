package com.kozirase.app;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "text_analysis_table")
public class TextAnalysis {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String MemberName;

    private String chatContents;

    private String analysisResult;

    public TextAnalysis(String memberName, String chatContents, String analysisResult) {
        this.MemberName = memberName;
        this.chatContents = chatContents;
        this.analysisResult = analysisResult;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getMemberName() {
        return MemberName;
    }

    public String getChatContents() {
        return chatContents;
    }

    public String getAnalysisResult() {
        return analysisResult;
    }
}
