package com.kozirase.app;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TextAnalysisDao {

    @Insert
    void insert(TextAnalysis textAnalysis);

    @Update
    void update(TextAnalysis textAnalysis);

    @Delete
    void delete(TextAnalysis textAnalysis);

    @Query("DELETE FROM text_analysis_table")
    void deleteAllTextAnalysis();

    @Query("SELECT * FROM text_analysis_table")
    LiveData<List<TextAnalysis>> getAllTextAnalysis();


}
