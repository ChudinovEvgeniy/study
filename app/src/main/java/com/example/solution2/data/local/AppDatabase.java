package com.example.solution2.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.solution2.data.local.model.MoviesItemEntity;

@Database(entities = {MoviesItemEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
