package com.example.solution2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MoviesDao {
    @Query("SELECT * FROM moviesDb")
    List<MoviesItemEntity> getAll();

    @Insert
    void append(List<MoviesItemEntity> list);

    @Query("DELETE FROM moviesDb")
    void clear();
}
