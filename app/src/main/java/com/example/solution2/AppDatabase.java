package com.example.solution2;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {MoviesItemEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract MoviesDao moviesDao();
}
