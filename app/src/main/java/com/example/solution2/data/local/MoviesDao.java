package com.example.solution2.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.solution2.data.local.model.MoviesItemEntity;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM movies")
    List<MoviesItemEntity> getAll();

    @Insert
    void append(List<MoviesItemEntity> list);

    @Query("DELETE FROM movies")
    void clear();
}
